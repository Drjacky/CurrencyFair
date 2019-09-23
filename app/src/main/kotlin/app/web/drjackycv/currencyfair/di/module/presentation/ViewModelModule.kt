package app.web.drjackycv.currencyfair.di.module.presentation

import androidx.lifecycle.ViewModelProvider
import app.web.drjackycv.currencyfair.di.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun factory(factory: ViewModelFactory): ViewModelProvider.Factory

}