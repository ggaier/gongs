package com.github.ggaier.gongs.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.ggaier.gongs.R
import com.github.ggaier.gongs.ui.mycollection.AlbumAdapter
import com.github.ggaier.gongs.ui.mycollection.ArtistAdapter
import com.github.ggaier.gongs.util.GlideApp
import com.github.ggaier.gongs.vo.Album
import com.github.ggaier.gongs.vo.Artist
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import org.jetbrains.anko.dip
import timber.log.Timber

/**
 * Created by wenbo, 2018/10/12
 */
@BindingAdapter("imageUrl")
fun imageFromUrl(view: ImageView, mbid: String?) {
    if (!mbid.isNullOrEmpty()) {
        GlideApp.with(view)
            .load("http://coverartarchive.org/release/$mbid/front")
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_placeholder_image)
            .transform(RoundedCornersTransformation(view.context.dip(8), 0))
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
    with(listView.adapter as AlbumAdapter?) {
        if (!items.isNullOrEmpty()) {
            Timber.d("releases: ${items.size}")
            this?.submitList(items)
        }
    }
}