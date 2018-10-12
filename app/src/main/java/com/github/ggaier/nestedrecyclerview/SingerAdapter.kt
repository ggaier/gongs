package com.github.ggaier.nestedrecyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by wenbo, 2018/10/12
 */
class SingersAdapter: ListAdapter<Singer, RecyclerView.ViewHolder>(SingerDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented")
    }


}

class SingerDiffCallback : DiffUtil.ItemCallback<Singer>(){

    override fun areItemsTheSame(oldItem: Singer, newItem: Singer): Boolean {
        TODO("not implemented")
    }

    override fun areContentsTheSame(oldItem: Singer, newItem: Singer): Boolean {
        TODO("not implemented")
    }

}