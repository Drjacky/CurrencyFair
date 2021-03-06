package app.web.drjackycv.currencyfair.di.module.data

import app.web.drjackycv.data.datasource.remote.photo.PhotoRemoteDataSource
import app.web.drjackycv.data.network.base.BaseHttpClient
import app.web.drjackycv.data.network.base.BaseRetrofit
import app.web.drjackycv.data.network.photo.PhotoApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun moshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun okHttpClient(baseHttpClient: BaseHttpClient): OkHttpClient =
        baseHttpClient.okHttpClient

    @Provides
    @Singleton
    fun retrofit(baseRetrofit: BaseRetrofit): Retrofit =
        baseRetrofit.retrofit

    @Provides
    @Singleton
    fun provideApiSource(api: PhotoApi): PhotoRemoteDataSource = PhotoRemoteDataSource(api)

}