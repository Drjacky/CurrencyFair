package app.web.drjackycv.currencyfair.di.module.presentation

import app.web.drjackycv.presentation.ui.home.PhotoFragment
import app.web.drjackycv.presentation.ui.photodetail.PhotoDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun photo(): PhotoFragment

    @ContributesAndroidInjector
    abstract fun photoDetail(): PhotoDetailFragment

}