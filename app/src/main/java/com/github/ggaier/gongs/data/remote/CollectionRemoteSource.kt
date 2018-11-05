package com.github.ggaier.gongs.data.remote

import com.github.ggaier.gongs.api.MusicBrainzApi
import com.github.ggaier.gongs.api.ReleasesResponse
import com.github.ggaier.gongs.data.CollectionSource
import com.github.ggaier.gongs.vo.ArtistCollection

/**
 * Created by wenbo, 2018/11/5
 */
class CollectionRemoteSource(private val musicBrainzApi: MusicBrainzApi) : CollectionSource {

    override suspend fun getMyArtistCollection(mbid: String): ArtistCollection {
        return musicBrainzApi.getArtistCollection(mbid).await()
    }

    override suspend fun getReleasesByArtist(mbid: String): ReleasesResponse {
        return musicBrainzApi.getReleases(mbid).await()
    }

}