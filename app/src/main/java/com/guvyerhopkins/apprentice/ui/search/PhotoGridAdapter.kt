package com.guvyerhopkins.apprentice.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.guvyerhopkins.apprentice.R
import com.guvyerhopkins.apprentice.network.Photo

class PhotoGridAdapter :
    ListAdapter<Photo, PhotoGridAdapter.PhotoGridViewHolder>(DiffCallback) {

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
        holderGrid.bind(photo)
    }

    inner class PhotoGridViewHolder(
        private var view:
        View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(photo: Photo) {
            view.findViewById<ImageView>(R.id.grid_item_iv).load(photo.src.original)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.src.original == newItem.src.original
        }
    }
}

