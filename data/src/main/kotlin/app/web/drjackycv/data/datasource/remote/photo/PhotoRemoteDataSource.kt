package app.web.drjackycv.data.datasource.remote.photo

import app.web.drjackycv.data.datasource.remote.base.BaseRemoteDataSource
import app.web.drjackycv.data.network.photo.PhotoApi
import app.web.drjackycv.domain.entity.photo.PhotosParent
import app.web.drjackycv.domain.usecase.photo.GetPhotoUrlUseCaseParams
import io.reactivex.Single
import javax.inject.Inject

class PhotoRemoteDataSource @Inject constructor(
    private val photoApi: PhotoApi
) : BaseRemoteDataSource() {

    fun getPhotosListByTags(
        tags: String,
        page: Int = 0
    ): Single<PhotosParent> {
        return modifySingle(
            photoApi.getPhotosListByTag(
                tags = tags,
                page = page
            )
        )
    }

    fun getPhotoUrl(
        farm: Int,
        server: String,
        id: String,
        secret: String,
        size: String = GetPhotoUrlUseCaseParams.PhotoSizes.LARGE_SQUARE.size
    ): String {
        return String.format(
            "http://farm%d.static.flickr.com/%s/%s_%s_%s.jpg",
            farm,
            server,
            id,
            secret,
            size
        )
    }


}