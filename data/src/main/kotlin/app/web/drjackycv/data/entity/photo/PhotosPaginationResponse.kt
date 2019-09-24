package app.web.drjackycv.data.entity.photo

import app.web.drjackycv.domain.entity.base.PaginationObject
import app.web.drjackycv.domain.entity.base.ResponseObject
import app.web.drjackycv.domain.entity.photo.Photo
import com.squareup.moshi.Json

class PhotosPaginationResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "pages") val pages: Int,
    @Json(name = "perpage") val perpage: Int,
    @Json(name = "total") val total: String,
    @Json(name = "photo") val data: List<PhotoResponse>
) : ResponseObject<PaginationObject<Photo>> {

    override fun toDomain(): PaginationObject<Photo> = PaginationObject(
        page = page,
        pages = pages,
        perpage = perpage,
        total = total,
        list = data.map { it.toDomain() }
    )

}