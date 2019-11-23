package app.web.drjackycv.presentation.ui.home

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.web.drjackycv.domain.entity.base.NetworkState
import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.domain.usecase.photo.GetPhotoUrlUseCase
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.entity.photo.PhotoUI
import javax.inject.Inject

class PhotosAdapter @Inject constructor(
    private val getPhotoUrlUseCase: GetPhotoUrlUseCase,
    private val onItemClick: (PhotoUI) -> Unit,
    private val retryCallback: () -> Unit
) : PagedListAdapter<Photo, RecyclerView.ViewHolder>(PhotoDiffCallback()) {

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_photo -> PhotoItemViewHolder.create(
                parent,
                getPhotoUrlUseCase,
                onItemClick
            )
            R.layout.item_network_state -> NetworkStateItemViewHolder.create(
                parent,
                retryCallback
            )
            else -> throw IllegalArgumentException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_photo -> (holder as PhotoItemViewHolder).bind(getItem(position))
            R.layout.item_network_state -> (holder as NetworkStateItemViewHolder).bind(networkState)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_photo
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

}