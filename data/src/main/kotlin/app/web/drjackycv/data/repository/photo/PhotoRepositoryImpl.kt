package app.web.drjackycv.data.repository.photo

import androidx.annotation.MainThread
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import app.web.drjackycv.data.datasource.remote.photo.PhotoRemoteDataSource
import app.web.drjackycv.data.datasource.remote.photo.PhotoRemoteDataSourceFactory
import app.web.drjackycv.domain.entity.base.Listing
import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.domain.repository.photo.PhotoRepository
import java.util.concurrent.Executor
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoRemoteDataSource: PhotoRemoteDataSource,
    private val networkExecutor: Executor
) : PhotoRepository {

    @MainThread
    override fun getRemotePhotosListByTag(
        tags: String
    ): Listing<Photo> {
        val factory = photoRemoteDataSourceFactory(tags)
        val config = pagedListConfig()
        val livePagedList = LivePagedListBuilder(factory, config)
            .setFetchExecutor(networkExecutor)
            .build()

        return Listing(
            pagedList = livePagedList,
            networkState = switchMap(factory.source) { it.network },
            retry = { factory.source.value?.retryAllFailed() },
            refresh = { factory.source.value?.invalidate() },
            refreshState = switchMap(factory.source) { it.initial }
        )

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

    private fun photoRemoteDataSourceFactory(tags: String) =
        PhotoRemoteDataSourceFactory(tags, photoRemoteDataSource, networkExecutor)

    private fun pagedListConfig(pageSize: Int = 25): PagedList.Config {
        return PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(pageSize * 2)
            .setPageSize(pageSize)
            .build()
    }

}