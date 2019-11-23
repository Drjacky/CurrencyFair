package app.web.drjackycv.data.datasource.remote.base

import app.web.drjackycv.domain.entity.base.ResponseObject
import com.squareup.moshi.Moshi
import dagger.Lazy
import retrofit2.Call
import java.io.IOException
import javax.inject.Inject

open class BaseRemoteDataSource {

    @Inject
    protected lateinit var moshi: Lazy<Moshi>

    fun <RO : ResponseObject<DO>, DO : Any> syncRequest(
        request: Call<RO>,
        onSuccess: (DO?) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val response = request.execute()
            onSuccess(response.body()?.toDomain())
        } catch (exception: IOException) {
            onError(exception.message ?: "unknown error")
        }
    }

}