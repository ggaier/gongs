package com.github.ggaier.gongs.ui.view

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by wenbo, 2018/11/26
 */
abstract class StateListAdapter<I, VH : RecyclerView.ViewHolder>(diffCallback: DiffUtil.ItemCallback<I>) :
    ListAdapter<I, VH>(diffCallback) {

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)

    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {

    }
}
