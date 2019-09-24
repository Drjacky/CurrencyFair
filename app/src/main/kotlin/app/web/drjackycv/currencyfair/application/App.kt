package app.web.drjackycv.currencyfair.application

import app.web.drjackycv.currencyfair.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

private const val SCHEMA_VERSION = 1L

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        initRealm()
        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    private fun initRealm() {
        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(SCHEMA_VERSION)
                .build()
        )
    }

}