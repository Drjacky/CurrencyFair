package app.web.drjackycv.domain.repository.photo

import app.web.drjackycv.domain.entity.base.Listing
import app.web.drjackycv.domain.entity.photo.Photo

interface PhotoRepository {

    fun getRemotePhotosListByTag(tags: String): Listing<Photo>

    fun getPhotoUrl(
        farm: Int,
        server: String,
        id: String,
        secret: String,
        size: String
    ): String

}