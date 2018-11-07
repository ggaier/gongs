package com.github.ggaier.gongs.ui.mycollection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ggaier.gongs.databinding.ListItemArtistCollectionBinding
import com.github.ggaier.gongs.util.obtainViewModel
import com.github.ggaier.gongs.vo.Artist
import timber.log.Timber

/**
 * Created by wenbo, 2018/10/12
 */
class ArtistAdapter : ListAdapter<Artist, ArtistAdapter.ViewHolder>(SingerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItemArtistCollectionBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Timber.d("onBindViewHolder: $position, ${getItem(position)}")
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemArtistCollectionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Artist) {
            binding.singer = item
            val viewModel = (binding.root.context as AppCompatActivity).obtainViewModel(ArtistViewModel::class.java)
            if (binding.viewModel == null) {
                binding.viewModel = viewModel
            }
            if (binding.albums.adapter == null) {
                binding.albums.adapter = AlbumAdapter()
            }
            viewModel.getReleases(item.id)
        }

    }

    private class SingerDiffCallback : DiffUtil.ItemCallback<Artist>() {

        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
            oldItem.id == newItem.id
    }
}