package com.github.ggaier.gongs.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.ggaier.gongs.ui.mycollection.AlbumAdapter
import com.github.ggaier.gongs.ui.mycollection.ArtistAdapter
import com.github.ggaier.gongs.vo.Album
import com.github.ggaier.gongs.vo.Artist
import timber.log.Timber

/**
 * Created by wenbo, 2018/10/12
 */
@BindingAdapter("imageUrl")
fun imageFromUrl(view: ImageView, mbid: String?) {
    if (!mbid.isNullOrEmpty()) {
        Glide.with(view).load("http://coverartarchive.org/release/$mbid/front")
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

/**
 * // WB_TODO: 2018/11/7 generic type
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Artist>) {
    with(listView.adapter as ArtistAdapter) {
        // WB_TODO: 2018/11/7 confused here
        if (items.isNotEmpty()) {
            Timber.d("items: ${items.size}, ${Thread.currentThread().name}")
            submitList(items)
        }
    }
}

@BindingAdapter("app:releases")
fun setReleases(listView: RecyclerView, items: List<Album>?) {
    with(listView.adapter as AlbumAdapter) {
        if (!items.isNullOrEmpty()) {
            Timber.d("releases: ${items.size}")
            submitList(items)
        }
    }
}