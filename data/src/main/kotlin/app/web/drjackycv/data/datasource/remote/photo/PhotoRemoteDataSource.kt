package app.web.drjackycv.data.datasource.remote.photo

import app.web.drjackycv.data.datasource.remote.base.BaseRemoteDataSource
import app.web.drjackycv.data.network.photo.PhotoApi
import app.web.drjackycv.domain.entity.photo.Photo
import io.reactivex.Single
import javax.inject.Inject

class PhotoRemoteDataSource @Inject constructor(
    private val photoApi: PhotoApi
) : BaseRemoteDataSource() {

    fun getPhotosListByTags(tags: String, page: Int): Single<List<Photo>> =
        modifySingleList(
            photoApi.getPhotosListByTag(
                tags = tags,
                page = page
            )
        )

}