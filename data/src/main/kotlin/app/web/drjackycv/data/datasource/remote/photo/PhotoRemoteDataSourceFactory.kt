package app.web.drjackycv.data.datasource.remote.photo

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import app.web.drjackycv.domain.entity.photo.Photo
import java.util.concurrent.Executor

class PhotoRemoteDataSourceFactory(
    private val tags: String,
    private val photoRemoteDataSource: PhotoRemoteDataSource,
    private val retryExecutor: Executor
) : DataSource.Factory<Int, Photo>() {

    val source = MutableLiveData<PhotoRemotePagedDataSource>()

    override fun create(): DataSource<Int, Photo> {
        val source = PhotoRemotePagedDataSource(tags, photoRemoteDataSource, retryExecutor)
        this.source.postValue(source)
        return source
    }

}