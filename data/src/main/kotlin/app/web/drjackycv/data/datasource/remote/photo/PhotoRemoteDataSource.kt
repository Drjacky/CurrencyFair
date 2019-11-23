package app.web.drjackycv.data.datasource.remote.photo

import app.web.drjackycv.data.datasource.remote.base.BaseRemoteDataSource
import app.web.drjackycv.data.network.photo.PhotoApi
import app.web.drjackycv.domain.entity.photo.PhotosParent
import app.web.drjackycv.domain.usecase.photo.GetPhotoUrlUseCaseParams
import javax.inject.Inject

class PhotoRemoteDataSource @Inject constructor(
    private val photoApi: PhotoApi
) : BaseRemoteDataSource() {

    fun getPhotosListByTags(
        tags: String,
        page: Int = 0,
        perPage: Int,
        onPrepared: () -> Unit,
        onSuccess: (PhotosParent?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = photoApi.getPhotosListByTag(
            tags = tags,
            page = page,
            perPage = perPage
        )
        onPrepared()
        syncRequest(
            request, onSuccess, onError
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