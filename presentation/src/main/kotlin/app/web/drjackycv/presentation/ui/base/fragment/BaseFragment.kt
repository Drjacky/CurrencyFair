package app.web.drjackycv.presentation.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.extension.hideKeyboard
import dagger.Lazy
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    @setparam:LayoutRes
    abstract var fragmentLayout: Int

    protected val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(fragmentLayout, container, false)

    override fun onDestroy() {
        hideKeyboard()
        dispose()
        super.onDestroy()
    }

    private fun dispose() {
        compositeDisposable.clear()
    }

    protected fun pushStack(fragment: Fragment) {
        activity?.let {
            if (it.isFinishing.not()) {
                fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(
                        R.anim.enter,
                        R.anim.exit,
                        R.anim.enter,
                        R.anim.exit
                    )
                    //?.addToBackStack(null)
                    ?.addToBackStack(fragment::class.java.simpleName)
                    ?.add(R.id.parentContainer, fragment)
                    ?.commit()
            }
        }
    }

}