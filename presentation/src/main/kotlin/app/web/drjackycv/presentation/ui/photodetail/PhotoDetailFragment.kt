package app.web.drjackycv.presentation.ui.photodetail

import android.os.Bundle
import android.view.View
import app.web.drjackycv.domain.usecase.photo.GetPhotoUrlUseCase
import app.web.drjackycv.domain.usecase.photo.GetPhotoUrlUseCaseParams
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.entity.photo.PhotoUI
import app.web.drjackycv.presentation.extension.getParcelableParam
import app.web.drjackycv.presentation.extension.load
import app.web.drjackycv.presentation.extension.setParcelableParam
import app.web.drjackycv.presentation.ui.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_photo_detail.*
import javax.inject.Inject

class PhotoDetailFragment : BaseFragment() {

    @Inject
    lateinit var getPhotoUrlUseCase: GetPhotoUrlUseCase

    override var fragmentLayout: Int = R.layout.fragment_photo_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photoUI: PhotoUI = getParcelableParam("PhotoDetailFragment needs a PhotoUI to inflate")

        setupUI(photoUI)
    }

    private fun setupUI(photoUI: PhotoUI) {
        fragmentPhotoDetailImv.load(
            getPhotoUrlUseCase(
                GetPhotoUrlUseCaseParams(
                    farm = photoUI.farm,
                    server = photoUI.server,
                    id = photoUI.id,
                    secret = photoUI.secret,
                    size = GetPhotoUrlUseCaseParams.PhotoSizes.LARGE.size
                )
            )
        )
    }

    companion object {
        fun getFragment(photoUI: PhotoUI) = PhotoDetailFragment().apply {
            setParcelableParam(photoUI)
        }
    }

}