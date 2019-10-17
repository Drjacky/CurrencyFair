package app.web.drjackycv.presentation.entity.photo

import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.presentation.entity.base.Mapper

class PhotoMapper : Mapper<Photo, PhotoUI> {

    override fun mapToUI(obj: Photo): PhotoUI = with(obj) {
        PhotoUI(
            id = id,
            owner = owner,
            secret = secret,
            server = server,
            farm = farm,
            title = title,
            isPublic = isPublic,
            isFriend = isFriend,
            isFamily = isFamily
        )
    }

    override fun mapToDomain(obj: PhotoUI): Photo = with(obj) {
        Photo(
            id = id,
            owner = owner,
            secret = secret,
            server = server,
            farm = farm,
            title = title,
            isPublic = isPublic,
            isFriend = isFriend,
            isFamily = isFamily
        )
    }

}