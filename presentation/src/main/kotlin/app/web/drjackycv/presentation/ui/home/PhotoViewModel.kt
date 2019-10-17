package app.web.drjackycv.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.domain.usecase.photo.GetRemotePhotosByTagParams
import app.web.drjackycv.domain.usecase.photo.GetRemotePhotosByTagUseCase
import app.web.drjackycv.presentation.ui.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class PhotoViewModel @Inject constructor(
    private val getRemotePhotosByTagUseCase: GetRemotePhotosByTagUseCase
) : BaseViewModel() {

    private val _ldPhotos = MutableLiveData<PagedList<Photo>>()
    val ldPhotos: LiveData<PagedList<Photo>> = _ldPhotos

    fun getPhotosList(tags: String) {
        getRemotePhotosByTagUseCase(
            GetRemotePhotosByTagParams(
                tags = tags
            )
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { photos ->
                    _ldPhotos.value = photos
                },
                { throwable ->
                    handleFailure(throwable) {
                        getPhotosList(
                            tags = tags
                        )
                    }
                }
            )
            .addTo(compositeDisposable)
    }

}