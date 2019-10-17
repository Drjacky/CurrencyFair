package app.web.drjackycv.domain.usecase.photo

import app.web.drjackycv.domain.repository.photo.PhotoRepository
import app.web.drjackycv.domain.usecase.base.GeneralUseCase
import javax.inject.Inject

class GetPhotoUrlUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) : GeneralUseCase<String, GetPhotoUrlUseCaseParams> {

    override fun invoke(params: GetPhotoUrlUseCaseParams): String =
        photoRepository.getPhotoUrl(
            farm = params.farm,
            server = params.server,
            id = params.id,
            secret = params.secret,
            size = params.size
        )
}

class GetPhotoUrlUseCaseParams(
    val farm: Int,
    val server: String,
    val id: String,
    val secret: String,
    val size: String = PhotoSizes.LARGE_SQUARE.size
) {
    enum class PhotoSizes(val size: String) {
        SQUARE("s"),
        LARGE_SQUARE("q"),
        THUMBNAIL("t"),
        SMALL("m"),
        SMALL_320("n"),
        MEDIUM_640("z"),
        MEDIUM_800("c"),
        LARGE("b"),
        LARGE_1600("h"),
        LARGE_2048("k")
    }

}
