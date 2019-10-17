package app.web.drjackycv.data.repository.photo

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import app.web.drjackycv.data.datasource.remote.photo.PhotoRemoteDataSource
import app.web.drjackycv.data.datasource.remote.photo.PhotoRemoteDataSourceFactory
import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.domain.repository.photo.PhotoRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoRemoteDataSource: PhotoRemoteDataSource
) : PhotoRepository {

    override fun getRemotePhotosListByTag(
        tags: String
    ): Flowable<PagedList<Photo>> {
        val dataFactory = PhotoRemoteDataSourceFactory(tags, photoRemoteDataSource)
        val config = PagedList.Config.Builder()
            .setPageSize(25)
            .setInitialLoadSizeHint(25)
            .setEnablePlaceholders(false)
            .build()

        return RxPagedListBuilder(dataFactory, config).buildFlowable(BackpressureStrategy.BUFFER)
    }

    override fun getPhotoUrl(
        farm: Int,
        server: String,
        id: String,
        secret: String,
        size: String
    ): String = photoRemoteDataSource.getPhotoUrl(
        farm = farm,
        server = server,
        id = id,
        secret = secret,
        size = size
    )

}