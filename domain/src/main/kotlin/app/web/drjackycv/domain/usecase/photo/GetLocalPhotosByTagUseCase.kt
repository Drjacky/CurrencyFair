package app.web.drjackycv.domain.usecase.photo

import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.domain.repository.photo.PhotoRepository
import app.web.drjackycv.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetLocalPhotosByTagUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) : SingleUseCase<List<Photo>, GetRemotePhotosByTagParams> {

    override fun invoke(params: GetRemotePhotosByTagParams): Single<List<Photo>> =
        photoRepository.getLocalPhotosListByTag(params.tags)

}

class GetLocalPhotosByTagParams(
    val tags: String
)