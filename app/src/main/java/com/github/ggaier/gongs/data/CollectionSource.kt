package com.github.ggaier.gongs.data

import com.github.ggaier.gongs.api.ReleasesResponse
import com.github.ggaier.gongs.vo.ArtistCollection

/**
 * Created by wenbo, 2018/11/5
 */
interface CollectionSource {

    @Throws(Exception::class)
    suspend fun getMyArtistCollection(mbid: String): ArtistCollection

    suspend fun getReleasesByArtist(mbid: String): ReleasesResponse

}