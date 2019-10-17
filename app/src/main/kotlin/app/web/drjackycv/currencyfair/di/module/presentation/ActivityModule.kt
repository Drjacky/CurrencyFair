package app.web.drjackycv.currencyfair.di.module.presentation

import app.web.drjackycv.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun main(): MainActivity

}