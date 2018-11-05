package com.github.ggaier.gongs.vo

import com.google.gson.annotations.SerializedName


/**
 * Created by wenbo, 2018/11/5
 */
sealed class Collection(
    @SerializedName("editor")
    val editor: String = "",
    @SerializedName("entity-type")
    val entityType: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("type-id")
    val typeId: String = ""
)

data class ArtistCollection(
    @SerializedName("artists")
    val artists: List<Artist>,
    @SerializedName("artist-count")
    val artistCount: Int
) : Collection()