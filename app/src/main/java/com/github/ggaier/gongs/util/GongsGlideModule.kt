package com.github.ggaier.gongs.util

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule


/**
 * Created by wenbo, 2018/11/8
 */
@GlideModule
class GongsGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        //do nothing
    }

}