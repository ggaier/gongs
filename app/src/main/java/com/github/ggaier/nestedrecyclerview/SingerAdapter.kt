package com.github.ggaier.nestedrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ggaier.nestedrecyclerview.databinding.ListItemSingerBinding

/**
 * Created by wenbo, 2018/10/12
 */
class SingersAdapter : ListAdapter<Singer, SingersAdapter.ViewHolder>(SingerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(ListItemSingerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemSingerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Singer) {
            binding.singer = item
        }

    }

}

class SingerDiffCallback : DiffUtil.ItemCallback<Singer>() {

    override fun areItemsTheSame(oldItem: Singer, newItem: Singer): Boolean = oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Singer, newItem: Singer): Boolean =
            oldItem.name == newItem.name && oldItem.albums.size == newItem.albums.size
}