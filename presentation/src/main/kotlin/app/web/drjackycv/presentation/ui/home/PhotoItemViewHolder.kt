package app.web.drjackycv.presentation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class PhotoItemViewHolder(
    itemView: View,
    private val getPhotoUrlUseCase: GetPhotoUrlUseCase,
    private val onItemClick: (PhotoUI) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(photo: Photo?) {
        photo?.let {
            itemView.itemPhotoImv.load(getPhotoUrl(photo))
            val photoUI: PhotoUI = PhotoMapper().mapToUI(photo)
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

    companion object {
        fun create(
            parent: ViewGroup,
            getPhotoUrlUseCase: GetPhotoUrlUseCase,
            onItemClick: (PhotoUI) -> Unit
        ): PhotoItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo, parent, false)
            return PhotoItemViewHolder(view, getPhotoUrlUseCase, onItemClick)
        }
    }

}