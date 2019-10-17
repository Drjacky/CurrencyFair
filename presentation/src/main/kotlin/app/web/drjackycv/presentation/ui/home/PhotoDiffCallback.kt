package app.web.drjackycv.presentation.ui.home

import androidx.recyclerview.widget.DiffUtil
import app.web.drjackycv.domain.entity.photo.Photo

class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
        oldItem == newItem

    override fun getChangePayload(oldItem: Photo, newItem: Photo): Any? {
        // Return particular field for changed item.
        return super.getChangePayload(oldItem, newItem)
    }

}
