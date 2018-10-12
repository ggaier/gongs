package com.github.ggaier.nestedrecyclerview

/**
 * Created by wenbo, 2018/10/12
 */
data class Singer(val name: String, val albums: List<Album>)

data class Album(val cover: String, val released: String, val rating: String, val songs: List<Song>)

data class Song(val name: String)

