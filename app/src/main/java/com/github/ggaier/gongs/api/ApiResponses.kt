package com.github.ggaier.gongs.api

import com.github.ggaier.gongs.vo.Album
import com.google.gson.annotations.SerializedName

/**
 * Created by wenbo, 2018/10/25
 */
open class ApiResponse(
    @SerializedName("release-offset")
    val releaseOffset: Int = 0,
    @SerializedName("release-count")
    val count: Int = 0
)

data class ReleasesResponse(val releases: List<Album>?): ApiResponse()