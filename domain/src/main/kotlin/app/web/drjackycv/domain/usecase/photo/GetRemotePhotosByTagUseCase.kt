package app.web.drjackycv.domain.usecase.photo

import app.web.drjackycv.domain.entity.base.Listing
import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.domain.repository.photo.PhotoRepository
import app.web.drjackycv.domain.usecase.base.GeneralUseCase
import javax.inject.Inject

class GetRemotePhotosByTagUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) : GeneralUseCase<Listing<Photo>, GetRemotePhotosByTagParams> {

    override fun invoke(params: GetRemotePhotosByTagParams): Listing<Photo> =
        photoRepository.getRemotePhotosListByTag(
            tags = params.tags
        )

}

inline class GetRemotePhotosByTagParams(
    val tags: String
)