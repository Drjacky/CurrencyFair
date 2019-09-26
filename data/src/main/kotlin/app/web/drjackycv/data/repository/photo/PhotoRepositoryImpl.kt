package app.web.drjackycv.data.repository.photo

import app.web.drjackycv.data.datasource.local.photo.PhotoLocalDataSource
import app.web.drjackycv.data.datasource.remote.photo.PhotoRemoteDataSource
import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.domain.repository.photo.PhotoRepository
import io.reactivex.Single
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoLocalDataSource: PhotoLocalDataSource,
    private val photoRemoteDataSource: PhotoRemoteDataSource
) : PhotoRepository {

    override fun getLocalPhotosListByTag(tags: String): Single<List<Photo>> =
        photoLocalDataSource.getPhotosListByTags(tags = tags)

    override fun getRemotePhotosListByTag(tags: String, page: Int): Single<List<Photo>> =
        photoRemoteDataSource.getPhotosListByTags(tags = tags, page = page)

}