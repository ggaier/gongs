package com.github.ggaier.gongs

import android.widget.ImageView
import androidx.databinding.BindingAdapter
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