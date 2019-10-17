package app.web.drjackycv.data.network.photo

import app.web.drjackycv.data.network.entity.photo.PhotosParentResponse
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET("?method=flickr.photos.search")
    fun getPhotosListByTag(
        @Query("tags") tags: String,
        @Query("page") page: Int = 0,
        @Query("per_page") perPage: Int = 20
    ): Single<Result<PhotosParentResponse>>

}