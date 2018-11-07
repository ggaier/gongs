package com.github.ggaier.gongs.ui.mycollection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ggaier.gongs.databinding.ListItemArtistBinding
import com.github.ggaier.gongs.vo.Artist
import timber.log.Timber

/**
 * Created by wenbo, 2018/10/12
 */
class ArtistsAdapter : ListAdapter<Artist, ArtistsAdapter.ViewHolder>(SingerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItemArtistBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Timber.d("onBindViewHolder: $position, ${getItem(position)}")
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemArtistBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Artist) {
            binding.singer = item
            if (binding.albums.adapter == null) {
                binding.albums.adapter = AlbumsAdapter()
            }
        }

    }

    private class SingerDiffCallback : DiffUtil.ItemCallback<Artist>() {

        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
            oldItem.id == newItem.id
    }
}