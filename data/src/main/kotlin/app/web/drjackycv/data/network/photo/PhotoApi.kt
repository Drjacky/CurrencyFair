package app.web.drjackycv.data.network.photo

import app.web.drjackycv.data.network.entity.photo.PhotosParentResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET("?method=flickr.photos.search")
    fun getPhotosListByTag(
        @Query("tags") tags: String,
        @Query("page") page: Int = 0,
        @Query("per_page") perPage: Int = 20
    ): Call<PhotosParentResponse>

}