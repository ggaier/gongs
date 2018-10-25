package com.github.ggaier.gongs.model

/**
 * Created by wenbo, 2018/10/12
 */
data class Artist(val name: String, val albums: List<Album>): ApiResponse()

data class Album(val name: String, val cover: String, val released: String, val rating: String, val songs: List<Song>)

data class Song(val name: String)

