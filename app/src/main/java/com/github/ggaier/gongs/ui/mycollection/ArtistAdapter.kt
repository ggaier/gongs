package com.github.ggaier.gongs.ui.mycollection

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ggaier.gongs.databinding.ListItemArtistCollectionBinding
import com.github.ggaier.gongs.util.obtainViewModel
import com.github.ggaier.gongs.vo.Artist
import org.jetbrains.anko.dip
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
            val context = binding.root.context
            var showUI = false
            if (binding.albums.adapter == null) {
                binding.albums.addItemDecoration(DividerItemDecoration(
                    context,
                    DividerItemDecoration.HORIZONTAL
                ).also {
                    val shapeDrawable = ShapeDrawable()
                    shapeDrawable.paint.color = Color.TRANSPARENT
                    shapeDrawable.intrinsicWidth = context.dip(16)
                    it.setDrawable(shapeDrawable)
                })
                binding.albums.adapter = AlbumAdapter()
                showUI = true
            }
            viewModel.loadReleases(item.id, showUI)
        }

    }

    private class SingerDiffCallback : DiffUtil.ItemCallback<Artist>() {

        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
            oldItem.id == newItem.id
    }
}