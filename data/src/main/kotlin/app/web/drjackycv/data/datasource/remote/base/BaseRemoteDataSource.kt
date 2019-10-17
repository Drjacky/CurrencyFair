package app.web.drjackycv.data.datasource.remote.base

import android.content.res.Resources
import androidx.annotation.VisibleForTesting
import app.web.drjackycv.data.BuildConfig
import app.web.drjackycv.data.R
import app.web.drjackycv.data.entity.base.ErrorResponse
import app.web.drjackycv.data.network.constant.ApiCodes.INVALID_API_KEY
import app.web.drjackycv.domain.entity.base.Failure
import app.web.drjackycv.domain.entity.base.ResponseObject
import app.web.drjackycv.domain.entity.base.Success
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.Lazy
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.adapter.rxjava2.Result
import timber.log.Timber
import java.lang.reflect.ParameterizedType
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

open class BaseRemoteDataSource {

    @Inject
    protected lateinit var resources: Lazy<Resources>
    @Inject
    protected lateinit var moshi: Lazy<Moshi>

    private val timeout = 30L
    private val retry = 1

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun <RO : ResponseObject<DO>, DO : Any> modifySingle(
        single: Single<Result<RO>>,
        timeoutTime: Long = timeout,
        retryTimes: Int = retry,
        codeErrorHandler: ((Int) -> Failure)? = null,
        reasonErrorHandler: ((Failure) -> Failure)? = null
    ): Single<DO> =
        single
            .onErrorResumeNext {
                Single.error(getFailureUnknownError())
            }
            .flatMap { data ->
                Single.create<DO> { emitter ->

                    val checkCodeError = codeErrorHandler != null
                    val checkReasonError = reasonErrorHandler != null

                    if (data.response() == null) Timber.e("BaseRemoteDataSource: modifySingle ${data.error().toString()}")

                    data.response()?.let { response ->
                        val body: RO? = response.body()
                        val code = response.code()
                        val errorBody = response.errorBody()

                        if (emitter.isDisposed.not()) {
                            when {
                                response.isSuccessful && body != null -> emitter.onSuccess(
                                    getDomainObject(body)
                                )
                                response.isSuccessful && body == null -> emitter.onSuccess(
                                    getDomainObjectNoResponse(code)
                                )
                                code == INVALID_API_KEY -> emitter.tryOnError(
                                    Failure.Unauthorized
                                )
                                checkCodeError -> emitter.tryOnError(codeErrorHandler!!.invoke(code))
                                response.isSuccessful.not() -> {
                                    val failure = getFailureErrorWithErrorResponse(code, errorBody)
                                    if (checkReasonError) {
                                        emitter.tryOnError(reasonErrorHandler!!.invoke(failure))
                                    } else {
                                        emitter.tryOnError(failure)
                                    }
                                }
                                else -> emitter.tryOnError(getFailureUnknownError())
                            }
                        }

                    } ?: run {
                        if (emitter.isDisposed.not()) {
                            emitter.tryOnError(getFailureError(data.error()))
                        }
                    }

                }
            }.timeout(timeoutTime, TimeUnit.SECONDS, Single.create<DO> { emitter ->
                if (emitter.isDisposed.not()) {
                    emitter.tryOnError(getFailureTimeout())
                }
            }).retry { count, throwable ->
                count <= retryTimes && (throwable is Failure.Timeout || throwable is Failure.NoInternet)
            }

    @Suppress("UNCHECKED_CAST")
    private fun <RO : ResponseObject<DO>, DO : Any> getDomainObject(body: RO): DO =
        (body as ResponseObject<Any>).toDomain() as DO

    @Suppress("UNCHECKED_CAST")
    private fun <DO : Any> getDomainObjectNoResponse(code: Int): DO =
        Success(code) as DO

    private fun getFailureErrorWithErrorResponse(
        code: Int,
        errorBody: ResponseBody?
    ): Failure.Error {
        val errorResponse = parseErrorResponse(code, errorBody)
        val stat =
            if (errorResponse.stat.isNotEmpty()) errorResponse.stat else resources.get().getString(R.string.unknown_error)
        val message =
            if (errorResponse.message.isNotEmpty()) errorResponse.message else resources.get().getString(
                R.string.unknown_reason
            )
        return Failure.Error(stat, code, message)
    }

    private fun getFailureError(throwable: Throwable?): Failure {
        Timber.e("getFailureError ${throwable?.message}")

        return when (throwable) {
            is UnknownHostException -> Failure.NoInternet(resources.get().getString(R.string.no_internet))
            else -> {
                Failure.Error(
                    @Suppress("ConstantConditionIf")
                    if (BuildConfig.BUILD_TYPE == "release") {
                        resources.get().getString(R.string.unknown_error)
                    } else {
                        throwable?.message ?: resources.get().getString(R.string.unknown_error)
                    }
                )
            }
        }
    }

    protected fun getFailureUnknownError(): Failure.Error =
        Failure.Error(resources.get().getString(R.string.unknown_error))

    private fun getFailureTimeout(): Failure.Timeout =
        Failure.Timeout(resources.get().getString(R.string.timeout_message))

    private fun parseErrorResponse(code: Int, responseBody: ResponseBody?): ErrorResponse {
        val type: ParameterizedType = Types.newParameterizedType(ErrorResponse::class.java)
        val adapter: JsonAdapter<ErrorResponse> = moshi.get().adapter(type)
        return try {
            responseBody?.let { adapter.fromJson(it.string()) } ?: ErrorResponse(
                resources.get().getString(R.string.unknown_error),
                code,
                resources.get().getString(R.string.unknown_reason)
            )
        } catch (exception: Exception) {
            Timber.e("parseErrorResponse ${exception.message}")
            ErrorResponse(
                resources.get().getString(R.string.unknown_error),
                code,
                resources.get().getString(R.string.unknown_reason)
            )
        }
    }

}