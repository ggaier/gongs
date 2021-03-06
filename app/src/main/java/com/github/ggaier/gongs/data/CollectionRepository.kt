package com.github.ggaier.gongs.data

import android.util.LruCache
import com.github.ggaier.gongs.api.ReleasesResponse
import com.github.ggaier.gongs.vo.ArtistCollection

/**
 * Created by wenbo, 2018/11/5
 */
class CollectionRepository(
    private val localSource: CollectionSource,
    private val remoteSource: CollectionSource
) : CollectionSource by remoteSource {

    private val cache: LruCache<String, ArtistCollection> = LruCache(50)
    private val releasesCache = LruCache<String, ReleasesResponse>(50)

    override suspend fun getReleasesByArtist(mbid: String): ReleasesResponse {
        return if(releasesCache[mbid]!=null){
            releasesCache[mbid]
        }else{
            remoteSource.getReleasesByArtist(mbid).also {
                releasesCache.put(mbid, it)
            }
        }
    }

    override suspend fun getMyArtistCollection(mbid: String): ArtistCollection {
        return if (cache[mbid] != null) {
            cache[mbid]
        } else {
            remoteSource.getMyArtistCollection(mbid).also {
                cache.put(mbid, it)
            }
        }
    }

}