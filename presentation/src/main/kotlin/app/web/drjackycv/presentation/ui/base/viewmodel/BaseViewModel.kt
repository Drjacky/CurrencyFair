package app.web.drjackycv.presentation.ui.base.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.web.drjackycv.domain.entity.base.Failure
import app.web.drjackycv.presentation.BuildConfig
import app.web.drjackycv.presentation.R
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var resources: Resources

    private var mutableFailure: MutableLiveData<Failure> = MutableLiveData()
    val ldFailure: LiveData<Failure> = mutableFailure
    private var mutableLoading: MutableLiveData<Boolean> = MutableLiveData()
    val ldLoading: LiveData<Boolean> = mutableLoading

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun getFailure(throwable: Throwable): Failure {
        val failure = throwable as? Failure ?: Failure.Error(
            if (throwable.message?.isNotEmpty() == true) {
                @Suppress("ConstantConditionIf")
                if (BuildConfig.BUILD_TYPE == "release") {
                    resources.getString(R.string.unknown_error)
                } else {
                    throwable.message ?: resources.getString(R.string.unknown_error)
                }
            } else {
                resources.getString(R.string.unknown_error)
            }
        )

        return failure
    }

    protected fun handleFailure(throwable: Throwable) {
        val failure = getFailure(throwable)
        mutableFailure.value = failure
    }

    protected fun loading(visible: Boolean) {
        mutableLoading.value = visible
    }

}