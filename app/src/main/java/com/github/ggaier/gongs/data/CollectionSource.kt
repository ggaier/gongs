package com.github.ggaier.gongs.data

import com.github.ggaier.gongs.vo.ArtistCollection

/**
 * Created by wenbo, 2018/11/5
 */
interface CollectionSource{

    suspend fun getCollection():List<ArtistCollection>

}