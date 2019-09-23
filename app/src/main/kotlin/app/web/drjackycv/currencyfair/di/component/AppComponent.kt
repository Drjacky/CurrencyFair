package app.web.drjackycv.currencyfair.di.component

import app.web.drjackycv.currencyfair.application.App
import app.web.drjackycv.currencyfair.di.module.data.DataModule
import app.web.drjackycv.currencyfair.di.module.presentation.ActivityModule
import app.web.drjackycv.currencyfair.di.module.presentation.AppModule
import app.web.drjackycv.currencyfair.di.module.presentation.FragmentModule
import app.web.drjackycv.currencyfair.di.module.presentation.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        FragmentModule::class,
        DataModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }

}