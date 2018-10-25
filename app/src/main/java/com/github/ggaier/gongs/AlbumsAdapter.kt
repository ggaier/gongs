package com.github.ggaier.gongs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ggaier.gongs.databinding.ListItemAlbumBinding
import com.github.ggaier.gongs.model.Album

/**
 * Created by wenbo, 2018/10/12
 */
class AlbumsAdapter : ListAdapter<Album, AlbumsAdapter.ViewHolder>(AlbumDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemAlbumBinding.inflate(LayoutInflater.from(parent.context),
                parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemAlbumBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Album) {
            binding.album = item
        }
    }

    private class AlbumDiffCallback : DiffUtil.ItemCallback<Album>(){
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.cover == newItem.cover && oldItem.rating == newItem.rating
        }
    }
}