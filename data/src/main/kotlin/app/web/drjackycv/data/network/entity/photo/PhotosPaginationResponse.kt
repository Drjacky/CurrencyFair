package app.web.drjackycv.data.network.entity.photo

import app.web.drjackycv.domain.entity.base.ResponseObject
import app.web.drjackycv.domain.entity.photo.Photos
import com.squareup.moshi.Json

class PhotosPaginationResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "pages") val pages: Int,
    @Json(name = "perpage") val perPage: Int,
    @Json(name = "total") val total: String,
    @Json(name = "photo") val list: List<PhotoResponse>
) : ResponseObject<Photos> {

    override fun toDomain(): Photos =
        Photos(
            page = page,
            pages = pages,
            perPage = perPage,
            total = total,
            list = list.map { it.toDomain() }
        )

}