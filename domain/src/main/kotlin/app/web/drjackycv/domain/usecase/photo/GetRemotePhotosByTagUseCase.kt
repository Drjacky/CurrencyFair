package app.web.drjackycv.domain.usecase.photo

import androidx.paging.PagedList
import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.domain.repository.photo.PhotoRepository
import app.web.drjackycv.domain.usecase.base.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class GetRemotePhotosByTagUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) : FlowableUseCase<PagedList<Photo>, GetRemotePhotosByTagParams> {

    override fun invoke(params: GetRemotePhotosByTagParams): Flowable<PagedList<Photo>> =
        photoRepository.getRemotePhotosListByTag(
            tags = params.tags
        )

}

inline class GetRemotePhotosByTagParams(
    val tags: String
)