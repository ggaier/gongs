package com.github.ggaier.gongs.api

import com.github.ggaier.gongs.vo.Artist
import com.github.ggaier.gongs.vo.ArtistCollection
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by wenbo, 2018/10/16
 */
interface MusicBrainzApi {

    companion object {
        private const val ACCEPT_JSON = "Accept:application/json"
    }

    /**
     * 查询艺术家的信息, 默认查询 releases.
     * @param mbid
     */
    @Headers(ACCEPT_JSON)
    @GET("artist/{mbid}")
    fun queryArtist(
        @Path("mbid") mbid: String,
        @Query("inc") inc: String = "releases"
    ): Deferred<Artist>

    /**
     * Get a list of collections for a given user.
     * @param the mbid of Collection
     */
    @Headers(ACCEPT_JSON)
    @GET("collection/{mbid}/artists")
    fun getArtistCollection(@Path("mbid") id: String): Deferred<ArtistCollection>

    /**
     * Get releases of an Artist by mbid
     * @return releases wrapper which contains the first page of releases
     */
    @Headers(ACCEPT_JSON)
    @GET("release")
    fun getReleases(@Query("artist") artist: String): Deferred<ReleasesResponse>

}