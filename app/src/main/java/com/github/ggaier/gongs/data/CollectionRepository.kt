package com.github.ggaier.gongs.data

/**
 * Created by wenbo, 2018/11/5
 */
class CollectionRepository(private val localSource: CollectionSource,
                           private val remoteSource: CollectionSource): CollectionSource by remoteSource