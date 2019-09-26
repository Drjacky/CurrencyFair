package app.web.drjackycv.data.network.photo

import app.web.drjackycv.data.entity.photo.PhotoResponse
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET(".")
    fun getPhotosListByTag(
        @Query("method") method: String = "flickr.photos.search",
        @Query("tags") tags: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20
    ): Single<Result<List<PhotoResponse>>>

}