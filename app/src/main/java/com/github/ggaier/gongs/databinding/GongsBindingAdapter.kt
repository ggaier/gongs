package com.github.ggaier.gongs.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Created by wenbo, 2018/10/12
 */
@BindingAdapter("imageUrl")
fun imageFromUrl(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view).load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
    }
}

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Any>) {
//    with(listView.adapter as ListAdapter<Any, RecyclerView.ViewHolder>) {
//        submitList(items)
//    }
}