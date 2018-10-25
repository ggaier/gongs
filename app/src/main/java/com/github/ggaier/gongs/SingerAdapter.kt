package com.github.ggaier.gongs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ggaier.gongs.databinding.ListItemSingerBinding
import com.github.ggaier.gongs.model.Artist

/**
 * Created by wenbo, 2018/10/12
 */
class SingersAdapter : ListAdapter<Artist, SingersAdapter.ViewHolder>(SingerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(ListItemSingerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemSingerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Artist) {
            binding.singer = item
            if (binding.albums.adapter == null) {
                binding.albums.adapter = AlbumsAdapter()
            }
            (binding.albums.adapter as AlbumsAdapter).submitList(item.albums)
        }

    }

    private class SingerDiffCallback : DiffUtil.ItemCallback<Artist>() {

        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
                oldItem.name == newItem.name && oldItem.albums.size == newItem.albums.size
    }
}