package com.github.ggaier.gongs.vo

import com.google.gson.annotations.SerializedName

/**
 * Created by wenbo, 2018/10/12
 */
data class Artist(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("type") val type: String,
    @SerializedName("country") val country: String
)


data class Album(
    @SerializedName("quality") val quality: String,
    @SerializedName("title") val title: String,
    @SerializedName("text-representation") val textRepresentation: TextRepresentation,
    @SerializedName("release-events") val releaseEvents: List<ReleaseEvent>,
    @SerializedName("packaging-id") val packagingId: String,
    @SerializedName("status-id") val statusId: String,
    @SerializedName("id") val id: String,
    @SerializedName("country") val country: String,
    @SerializedName("date") val date: String,
    @SerializedName("status") val status: String,
    @SerializedName("barcode") val barcode: String,
    @SerializedName("packaging") val packaging: String,
    @SerializedName("disambiguation") val disambiguation: String
)

data class ReleaseEvent(
    @SerializedName("date") val date: String,
    @SerializedName("area") val area: Area
)

data class Area(
    @SerializedName("iso-3166-1-codes") val iso31661Codes: List<String>,
    @SerializedName("disambiguation") val disambiguation: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("sort-name") val sortName: String
)

data class TextRepresentation(
    @SerializedName("script") val script: String,
    @SerializedName("language") val language: String
)
