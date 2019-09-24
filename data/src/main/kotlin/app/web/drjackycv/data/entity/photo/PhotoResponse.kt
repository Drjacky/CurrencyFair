package app.web.drjackycv.data.entity.photo

import app.web.drjackycv.domain.entity.base.ResponseObject
import app.web.drjackycv.domain.entity.photo.Photo
import com.squareup.moshi.Json

class PhotoResponse(
    @Json(name = "id") val id: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "secret") val secret: String,
    @Json(name = "server") val server: String,
    @Json(name = "farm") val farm: Int,
    @Json(name = "title") val title: String,
    @Json(name = "ispublic") val ispublic: Int,
    @Json(name = "isfriend") val isfriend: Int,
    @Json(name = "isfamily") val isfamily: Int
) : ResponseObject<Photo> {

    override fun toDomain(): Photo =
        Photo(
            id = id,
            owner = owner,
            secret = secret,
            server = server,
            farm = farm,
            title = title,
            ispublic = ispublic,
            isfriend = isfriend,
            isfamily = isfamily
        )

}