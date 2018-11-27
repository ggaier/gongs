package com.github.ggaier.gongs.ui.mycollection

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.os.Parcelable
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

    private val recyclerViewPool = RecyclerView.RecycledViewPool()
    private val savedStates = HashMap<String, Parcelable?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        return ViewHolder(
            ListItemArtistCollectionBinding.inflate(
                LayoutInflater.from(
                    context
                ), parent, false
            )
        ).apply {
            binding.albums.setRecycledViewPool(recyclerViewPool)
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
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
//        Timber.d("onBindViewHolder with payloads: $payloads")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        val artist = getItem(holder.adapterPosition)
        savedStates[artist.id] = holder.binding.albums.layoutManager?.onSaveInstanceState().also {
//            Timber.d("onViewRecycled: ${artist.name}, state: ${it?.toString()}")
            Timber.d("onViewRecycled: ${artist.name}, layoutManager: ${holder.binding.albums.layoutManager}")
        }
    }

    inner class ViewHolder(val binding: ListItemArtistCollectionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Artist) {
            binding.singer = item
            val viewModel = (binding.root.context as AppCompatActivity).obtainViewModel(ArtistViewModel::class.java)
            binding.viewModel = viewModel
            with(binding.albums) {
                viewModel.loadReleases(item.id, adapter?.itemCount ?: 0 > 0)
                layoutManager?.onRestoreInstanceState(savedStates[item.id].apply {
                    Timber.d("restore state: ${item.name}, state: $this")
                })
            }
        }
    }

    private class SingerDiffCallback : DiffUtil.ItemCallback<Artist>() {

        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
            oldItem.id == newItem.id
    }
}