package app.web.drjackycv.currencyfair.di.module.data

import app.web.drjackycv.data.datasource.remote.photo.PhotoRemoteDataSource
import app.web.drjackycv.data.repository.photo.PhotoRepositoryImpl
import app.web.drjackycv.domain.repository.photo.PhotoRepository
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors

@Module
class RepositoryModule {

    @Suppress("PrivatePropertyName")
    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    @Provides
    fun photo(photoRemoteDataSource: PhotoRemoteDataSource): PhotoRepository =
        PhotoRepositoryImpl(photoRemoteDataSource, NETWORK_IO)

}