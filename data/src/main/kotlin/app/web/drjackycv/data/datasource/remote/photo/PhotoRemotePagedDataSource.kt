package app.web.drjackycv.data.datasource.remote.photo

import android.annotation.SuppressLint
import androidx.paging.PageKeyedDataSource
import app.web.drjackycv.domain.entity.photo.Photo
import javax.inject.Inject

@SuppressLint("CheckResult")
class PhotoRemotePagedDataSource @Inject constructor(
    private val tags: String,
    private val photoRemoteDataSource: PhotoRemoteDataSource
) : PageKeyedDataSource<Int, Photo>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {
        photoRemoteDataSource.getPhotosListByTags(
            tags = tags
        )
            .subscribe(
                { response ->
                    if (response.photos.total.toInt() == 0) {
                        callback.onResult(
                            response.photos.list,
                            0,
                            0,
                            null,
                            null
                        )
                    } else {
                        callback.onResult(
                            response.photos.list,
                            response.photos.page * response.photos.perPage,
                            response.photos.total.toInt(),
                            null,
                            response.photos.page + 1
                        )
                    }
                },
                { throwable ->

                }
            )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        photoRemoteDataSource.getPhotosListByTags(
            tags = tags,
            page = params.key
        )
            .subscribe({ response ->
                var nextPage: Int? = null

                if (response.photos.page < response.photos.pages)
                    nextPage = response.photos.page + 1

                callback.onResult(response.photos.list, nextPage)

            },
                { throwable ->

                }
            )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        photoRemoteDataSource.getPhotosListByTags(
            tags = tags,
            page = params.key
        )
            .subscribe({ response ->
                var previousPage: Int? = null

                if (response.photos.page > 0)
                    previousPage = response.photos.page - 1

                callback.onResult(response.photos.list, previousPage)
            },
                { throwable ->

                }
            )
    }

}