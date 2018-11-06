package com.github.ggaier.gongs.data.local

import com.github.ggaier.gongs.api.ReleasesResponse
import com.github.ggaier.gongs.data.CollectionSource
import com.github.ggaier.gongs.vo.ArtistCollection

/**
 * Created by wenbo, 2018/11/5
 */
class CollectionLocalSource(): CollectionSource{
    override suspend fun getMyArtistCollection(mbid: String): ArtistCollection {
        TODO("not implemented")
    }

    override suspend fun getReleasesByArtist(mbid: String): ReleasesResponse {
        TODO("not implemented")
    }

}