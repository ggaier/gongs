package com.github.ggaier.gongs.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.ggaier.gongs.ui.mycollection.ArtistsAdapter
import com.github.ggaier.gongs.vo.Artist
import timber.log.Timber

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
fun setItems(listView: RecyclerView, items: List<Artist>) {
    with(listView.adapter as ArtistsAdapter) {
        // WB_TODO: 2018/11/7 confused here
        if (items.isNotEmpty()) {
            Timber.d("items: ${items.size}, ${Thread.currentThread().name}")
            submitList(items)
        }
    }
}