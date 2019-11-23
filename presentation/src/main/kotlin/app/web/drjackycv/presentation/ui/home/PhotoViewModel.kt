package app.web.drjackycv.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.PagedList
import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.domain.usecase.photo.GetRemotePhotosByTagParams
import app.web.drjackycv.domain.usecase.photo.GetRemotePhotosByTagUseCase
import app.web.drjackycv.presentation.ui.base.viewmodel.BaseViewModel
import javax.inject.Inject

class PhotoViewModel @Inject constructor(
    private val getRemotePhotosByTagUseCase: GetRemotePhotosByTagUseCase
) : BaseViewModel() {

    private val _searchQuery = MutableLiveData<String>()
    private val _photos = map(_searchQuery) {
        getRemotePhotosByTagUseCase(
            GetRemotePhotosByTagParams(
                tags = it
            )
        )
    }
    val ldPhotos: LiveData<PagedList<Photo>> = switchMap(_photos) { it.pagedList }
    val ldNetworkState = switchMap(_photos) { it.networkState }
    val ldRefreshState = switchMap(_photos) { it.refreshState }

    fun getPhotosList(tags: String) {
        if (tags.isNotBlank())
            _searchQuery.value = tags
    }

    fun refresh() {
        _photos.value?.refresh?.invoke()
    }

    fun retry() {
        val listing = _photos.value
        listing?.retry?.invoke()
    }

}