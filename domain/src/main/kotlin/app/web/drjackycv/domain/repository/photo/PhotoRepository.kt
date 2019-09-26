package app.web.drjackycv.domain.repository.photo

import app.web.drjackycv.domain.entity.photo.Photo
import io.reactivex.Single

interface PhotoRepository {

    fun getLocalPhotosListByTag(tags: String): Single<List<Photo>>

    fun getRemotePhotosListByTag(tags: String, page: Int): Single<List<Photo>>

}