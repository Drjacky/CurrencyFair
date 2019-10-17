package app.web.drjackycv.currencyfair.di.module.data

import app.web.drjackycv.data.network.photo.PhotoApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun photo(retrofit: Retrofit): PhotoApi =
        retrofit.create(PhotoApi::class.java)

}