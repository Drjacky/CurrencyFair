package app.web.drjackycv.domain.repository.photo

import androidx.paging.PagedList
import app.web.drjackycv.domain.entity.photo.Photo
import io.reactivex.Flowable

interface PhotoRepository {

    fun getRemotePhotosListByTag(tags: String): Flowable<PagedList<Photo>>

    fun getPhotoUrl(
        farm: Int,
        server: String,
        id: String,
        secret: String,
        size: String
    ): String

}