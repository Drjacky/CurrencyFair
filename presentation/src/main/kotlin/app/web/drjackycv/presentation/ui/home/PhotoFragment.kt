package app.web.drjackycv.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.domain.usecase.photo.GetPhotoUrlUseCase
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.extension.hideKeyboard
import app.web.drjackycv.presentation.extension.observe
import app.web.drjackycv.presentation.extension.viewModel
import app.web.drjackycv.presentation.ui.base.fragment.BaseFragment
import app.web.drjackycv.presentation.ui.photodetail.PhotoDetailFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class PhotoFragment : BaseFragment() {

    @Inject
    lateinit var getPhotoUrlUseCase: GetPhotoUrlUseCase

    private lateinit var photoViewModel: PhotoViewModel
    private val photosAdapter: PhotosAdapter by lazy {
        PhotosAdapter(getPhotoUrlUseCase) { photo ->
            pushStack(PhotoDetailFragment.getFragment(photo))
        }
    }

    override var fragmentLayout: Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        photoViewModel = viewModel(viewModelFactory.get()) {
            observe(ldPhotos, ::addPhotos)
        }

        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = photosAdapter
        recyclerView.setHasFixedSize(true)

        searchBtn.setOnClickListener {
            hideKeyboard()
            if (searchTagsTxv.text.isNotBlank())
                photoViewModel.getPhotosList(searchTagsTxv.text.toString())
        }
    }

    private fun addPhotos(photos: PagedList<Photo>) {
        photosAdapter.submitList(photos)
    }

    companion object {
        fun getFragment() = PhotoFragment()
    }

}