package app.web.drjackycv.currencyfair.di.module.data

import app.web.drjackycv.data.repository.photo.PhotoRepositoryImpl
import app.web.drjackycv.domain.repository.photo.PhotoRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun photo(photoRepositoryImpl: PhotoRepositoryImpl): PhotoRepository

}