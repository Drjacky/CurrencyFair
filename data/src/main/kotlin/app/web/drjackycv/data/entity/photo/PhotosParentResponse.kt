package app.web.drjackycv.data.entity.photo

import app.web.drjackycv.domain.entity.base.ResponseObject
import app.web.drjackycv.domain.entity.photo.PhotoParent
import com.squareup.moshi.Json

class PhotosParentResponse(
    @Json(name = "photos") val photos: PhotosPaginationResponse,
    @Json(name = "stat") val stat: String
) : ResponseObject<PhotoParent> {

    override fun toDomain(): PhotoParent =
        PhotoParent(
            photos = photos.toDomain(),
            stat = stat
        )

}