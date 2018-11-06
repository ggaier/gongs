package com.github.ggaier.gongs.di

import com.github.ggaier.gongs.api.MusicBrainzApi
import com.github.ggaier.gongs.data.CollectionRepository
import com.github.ggaier.gongs.data.local.CollectionLocalSource
import com.github.ggaier.gongs.data.remote.CollectionRemoteSource
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by wenbo, 2018/10/25
 * Dependency Injector
 */
object DI {

    private val musicBrainzApi by lazy {
        Retrofit.Builder().baseUrl("https://musicbrainz.org/ws/2/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MusicBrainzApi::class.java)
    }

    val collectionRepository by lazy {
        CollectionRepository(CollectionLocalSource(), CollectionRemoteSource(musicBrainzApi))
    }

}