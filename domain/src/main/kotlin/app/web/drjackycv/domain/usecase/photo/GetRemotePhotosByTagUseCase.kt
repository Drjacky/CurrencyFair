package app.web.drjackycv.domain.usecase.photo

import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.domain.repository.photo.PhotoRepository
import app.web.drjackycv.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetRemotePhotosByTagUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) : SingleUseCase<List<Photo>, GetRemotePhotosByTagParams> {

    override fun invoke(params: GetRemotePhotosByTagParams): Single<List<Photo>> =
        photoRepository.getRemotePhotosListByTag(
            tags = params.tags,
            page = params.page
        )

}

class GetRemotePhotosByTagParams(
    val tags: String,
    val page: Int
)