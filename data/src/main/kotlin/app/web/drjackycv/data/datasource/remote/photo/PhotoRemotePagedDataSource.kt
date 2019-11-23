package app.web.drjackycv.data.datasource.remote.photo

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import app.web.drjackycv.domain.entity.base.NetworkState
import app.web.drjackycv.domain.entity.photo.Photo
import java.util.concurrent.Executor
import javax.inject.Inject

@SuppressLint("CheckResult")
class PhotoRemotePagedDataSource @Inject constructor(
    private val tags: String,
    private val photoRemoteDataSource: PhotoRemoteDataSource,
    private val retryExecutor: Executor
) : PageKeyedDataSource<Int, Photo>() {

    var retry: (() -> Any)? = null
    val network = MutableLiveData<NetworkState>()
    val initial = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {
        val currentPage = 1
        val nextPage = currentPage + 1

        photoRemoteDataSource.getPhotosListByTags(
            tags = tags,
            page = currentPage,
            perPage = params.requestedLoadSize,
            onPrepared = {
                postInitialState(NetworkState.LOADING)
            },
            onSuccess = { responseBody ->
                val items = responseBody?.photos?.list ?: emptyList()
                retry = null
                postInitialState(NetworkState.LOADED)
                callback.onResult(items, null, nextPage)
            },
            onError = { errorMessage ->
                retry = { loadInitial(params, callback) }
                postInitialState(NetworkState.error(errorMessage))
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val currentPage = params.key
        val nextPage = currentPage + 1

        photoRemoteDataSource.getPhotosListByTags(
            tags = tags,
            page = currentPage,
            perPage = params.requestedLoadSize,
            onPrepared = {
                postAfterState(NetworkState.LOADING)
            },
            onSuccess = { responseBody ->
                val items = responseBody?.photos?.list ?: emptyList()
                retry = null
                callback.onResult(items, nextPage)
                postAfterState(NetworkState.LOADED)
            },
            onError = { errorMessage ->
                retry = { loadAfter(params, callback) }
                postAfterState(NetworkState.error(errorMessage))
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {}

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let { retry ->
            retryExecutor.execute { retry() }
        }
    }

    private fun postInitialState(state: NetworkState) {
        network.postValue(state)
        initial.postValue(state)
    }

    private fun postAfterState(state: NetworkState) {
        network.postValue(state)
    }

}