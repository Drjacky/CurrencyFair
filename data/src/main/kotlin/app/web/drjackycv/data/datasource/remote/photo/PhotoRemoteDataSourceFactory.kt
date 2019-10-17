package app.web.drjackycv.data.datasource.remote.photo

import androidx.paging.DataSource
import app.web.drjackycv.domain.entity.photo.Photo

class PhotoRemoteDataSourceFactory(
    private val tags: String,
    private val photoRemoteDataSource: PhotoRemoteDataSource
) : DataSource.Factory<Int, Photo>() {

    override fun create(): DataSource<Int, Photo> {
        return PhotoRemotePagedDataSource(tags, photoRemoteDataSource)
    }
}