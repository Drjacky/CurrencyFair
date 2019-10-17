package app.web.drjackycv.presentation.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import app.web.drjackycv.presentation.ui.base.viewmodel.BaseViewModel

inline fun <reified T : BaseViewModel> Fragment.viewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit = {}
): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java] //TODO: Deprecated
    vm.body()

    return vm
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}