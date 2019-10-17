package app.web.drjackycv.presentation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.web.drjackycv.domain.entity.photo.Photo
import app.web.drjackycv.domain.usecase.photo.GetPhotoUrlUseCase
import app.web.drjackycv.domain.usecase.photo.GetPhotoUrlUseCaseParams
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.entity.photo.PhotoMapper
import app.web.drjackycv.presentation.entity.photo.PhotoUI
import app.web.drjackycv.presentation.extension.load
import app.web.drjackycv.presentation.extension.setOnReactiveClickListener
import kotlinx.android.synthetic.main.item_photo.view.*
import javax.inject.Inject

class PhotosAdapter @Inject constructor(
    var getPhotoUrlUseCase: GetPhotoUrlUseCase,
    private val onItemClick: (PhotoUI) -> Unit
) : PagedListAdapter<Photo, PhotosAdapter.DataHolder>(PhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DataHolder(inflater.inflate(R.layout.item_photo, null))
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val photo = getItem(position)
        photo?.let { holder.bind(photo) }
    }

    inner class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Photo) {
            itemView.itemPhotoImv.load(getPhotoUrl(photo))
            val photoResult = getItem((adapterPosition))
            photoResult?.let {
                val photoUI: PhotoUI = PhotoMapper().mapToUI(photoResult)

                itemView.setOnReactiveClickListener { onItemClick(photoUI) }
            }
        }

        private fun getPhotoUrl(photo: Photo): String = getPhotoUrlUseCase(
            GetPhotoUrlUseCaseParams(
                farm = photo.farm,
                server = photo.server,
                id = photo.id,
                secret = photo.secret
            )
        )

    }

}