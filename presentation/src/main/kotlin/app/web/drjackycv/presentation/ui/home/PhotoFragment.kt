package app.web.drjackycv.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import app.web.drjackycv.domain.entity.base.NetworkState
import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.domain.usecase.photo.GetPhotoUrlUseCase
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.entity.photo.PhotoUI
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
        PhotosAdapter(
            getPhotoUrlUseCase,
            ::goToPhotoDetail,
            ::retry
        )
    }

    override var fragmentLayout: Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        photoViewModel = viewModel(viewModelFactory.get()) {
            observe(ldPhotos, ::addPhotos)
            observe(ldNetworkState, ::setupNetworkState)
            observe(ldRefreshState, ::setupSwipeRefresh)
        }

        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = photosAdapter
        recyclerView.setHasFixedSize(true)

        searchBtn.setOnClickListener {
            hideKeyboard()
            photoViewModel.getPhotosList(searchTagsTxv.text.toString())
        }
    }

    private fun setupSwipeRefresh(networkState: NetworkState) {
        swipeRefresh.isRefreshing = networkState == NetworkState.LOADING
        swipeRefresh.setOnRefreshListener {
            photoViewModel.refresh()
        }
    }

    private fun setupNetworkState(networkState: NetworkState) {
        photosAdapter.setNetworkState(networkState)
    }

    private fun addPhotos(photos: PagedList<Photo>) {
        photosAdapter.submitList(photos)
    }

    private fun goToPhotoDetail(photo: PhotoUI) {
        pushStack(PhotoDetailFragment.getFragment(photo))
    }

    private fun retry() {
        photoViewModel.retry()
    }

    companion object {
        fun getFragment() = PhotoFragment()
    }

}