package app.web.drjackycv.data.network.entity.photo

import app.web.drjackycv.domain.entity.base.ResponseObject
import app.web.drjackycv.domain.entity.photo.PhotosParent
import com.squareup.moshi.Json

class PhotosParentResponse(
    @Json(name = "photos") val photos: PhotosPaginationResponse,
    @Json(name = "stat") val stat: String
) : ResponseObject<PhotosParent> {

    override fun toDomain(): PhotosParent =
        PhotosParent(
            photos = photos.toDomain(),
            stat = stat
        )

}