package app.web.drjackycv.currencyfair.di.module.presentation

import androidx.lifecycle.ViewModelProvider
import app.web.drjackycv.currencyfair.di.viewmodel.ViewModelFactory
import app.web.drjackycv.currencyfair.di.viewmodel.ViewModelKey
import app.web.drjackycv.presentation.ui.base.viewmodel.BaseViewModel
import app.web.drjackycv.presentation.ui.home.PhotoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun factory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PhotoViewModel::class)
    abstract fun photo(vm: PhotoViewModel): BaseViewModel

}