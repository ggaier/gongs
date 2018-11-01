package com.github.ggaier.gongs.api

import com.github.ggaier.gongs.vo.Artist
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by wenbo, 2018/10/16
 */
interface MusicBrainzApi {

    // MBID:
    //    5441c29d-3602-4898-b1a1-b77fa23b8e50 david bowie
    //    83d91898-7763-47d7-b03b-b92132375c47 pink floyd
    //    79491354-3d83-40e3-9d8e-7592d58d790a deep purple
    //    9efff43b-3b29-4082-824e-bc82f646f93d the doors
    //    ca891d65-d9b0-4258-89f7-e6ba29d83767 iron maiden
    //    65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab metallica
    //    65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab metallica
    //    678d88b2-87b0-403b-b63d-5da7465aecc3 led zeppelin
    //    a74b1b7f-71a5-4011-9441-d0b5e4122711 radiohead

    /**
     * 查询艺术家的信息, 默认查询 releases.
     * @param mbid
     */
    @GET("artist/{mbid}")
    fun queryArtist(
        @Path("mbid") mbid: String,
        @Query("inc") inc: String = "releases"
    ): Deferred<Artist>

}