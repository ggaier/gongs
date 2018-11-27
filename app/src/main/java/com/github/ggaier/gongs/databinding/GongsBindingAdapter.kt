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

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Artist>) {
    if (items.isEmpty()) return
    if (listView.adapter == null) {
        listView.adapter = ArtistAdapter()
    }
    (listView.adapter as? ArtistAdapter)?.submitList(items)
}

@BindingAdapter("app:releases")
fun setReleases(listView: RecyclerView, items: List<Album>?) {
    with(listView.adapter as AlbumAdapter?) {
        Timber.d("releases: ${items?.size}")
        this?.submitList(items)
    }
}