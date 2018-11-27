package com.github.ggaier.gongs.ui.mycollection

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.github.ggaier.gongs.databinding.ListItemArtistCollectionBinding
import com.github.ggaier.gongs.ui.view.StateOwner
import com.github.ggaier.gongs.ui.view.ViewState
import com.github.ggaier.gongs.ui.view.ViewStateVault
import com.github.ggaier.gongs.util.obtainViewModel
import com.github.ggaier.gongs.vo.Artist
import org.jetbrains.anko.dip





/**
 * Created by wenbo, 2018/10/12
 */
class ArtistAdapter : ListAdapter<Artist, ArtistAdapter.ViewHolder>(SingerDiffCallback()) {

    private val recyclerViewPool = RecyclerView.RecycledViewPool()
    private val viewStateVault = ViewStateVault<ViewState>()

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
        val item = getItem(position)
        holder.bind(item)
        holder.restoreViewState(viewStateVault.withdrawState(item.id)?:return)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        viewStateVault.saveState(holder.getViewState())
    }

    class ViewHolder(val binding: ListItemArtistCollectionBinding) : RecyclerView.ViewHolder(binding.root),
        StateOwner<ViewState> {

        override fun getViewState(): ViewState {
            return ViewState(binding.singer?.id ?: "", binding.albums)
        }

        override fun restoreViewState(viewState: ViewState) {
            val lm = binding.albums.layoutManager
            if (lm is LinearLayoutManager) {
                lm.scrollToPositionWithOffset(
                    viewState.leftPosition,
                    viewState.leftOffset
                )
            }
        }

        fun bind(item: Artist) {
            binding.singer = item
            val viewModel = (binding.root.context as AppCompatActivity).obtainViewModel(ArtistViewModel::class.java)
            binding.viewModel = viewModel
        }
    }

    private class SingerDiffCallback : DiffUtil.ItemCallback<Artist>() {

        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
            oldItem.id == newItem.id
    }
}