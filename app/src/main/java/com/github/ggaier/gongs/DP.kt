package com.github.ggaier.gongs

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import retrofit2.Retrofit

/**
 * Created by wenbo, 2018/10/25
 * Dependencies provider.
 */
object DP {

    val apiInstance = Retrofit.Builder().baseUrl("https://musicbrainz.org/ws/2/")
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()



}