package com.guvyerhopkins.apprentice.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.guvyerhopkins.apprentice.R
import com.guvyerhopkins.apprentice.network.Photo

class PhotoGridAdapter(private val onImagePressed: (String, ImageView) -> Unit) :
    PagedListAdapter<Photo, PhotoGridAdapter.PhotoGridViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.PhotoGridViewHolder {
        return PhotoGridViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.grid_view_item, parent, false)
        )
    }

    override fun onBindViewHolder(holderGrid: PhotoGridViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            holderGrid.bind(photo)
        }
    }

    inner class PhotoGridViewHolder(
        private var view:
        View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(photo: Photo) {
            val imageView = view.findViewById<ImageView>(R.id.grid_item_iv)
            imageView.load(photo.src.small) {
                scale(Scale.FILL)
                crossfade(true)
                placeholder(R.drawable.ic_image_placeholder)
            }

            imageView.setOnClickListener { onImagePressed.invoke(photo.src.large, imageView) }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }
}