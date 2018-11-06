package com.github.ggaier.gongs.di

import com.burgstaller.okhttp.AuthenticationCacheInterceptor
import com.burgstaller.okhttp.CachingAuthenticatorDecorator
import com.burgstaller.okhttp.digest.CachingAuthenticator
import com.burgstaller.okhttp.digest.Credentials
import com.burgstaller.okhttp.digest.DigestAuthenticator
import com.github.ggaier.gongs.BuildConfig
import com.github.ggaier.gongs.api.MusicBrainzApi
import com.github.ggaier.gongs.data.CollectionRepository
import com.github.ggaier.gongs.data.local.CollectionLocalSource
import com.github.ggaier.gongs.data.remote.CollectionRemoteSource
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ConcurrentHashMap


/**
 * Created by wenbo, 2018/10/25
 * Dependency Injector
 */
object DI {

    private val musicBrainzApi by lazy {
        val authenticator = DigestAuthenticator(Credentials(BuildConfig.USER_NAME, BuildConfig.PASSWORD))
        val authCache: ConcurrentHashMap<String, CachingAuthenticator> = ConcurrentHashMap()
        Retrofit.Builder().baseUrl("https://musicbrainz.org/ws/2/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                }).authenticator(CachingAuthenticatorDecorator(authenticator, authCache))
                    .addInterceptor(AuthenticationCacheInterceptor(authCache))
                    .build()
            ).build()
            .create(MusicBrainzApi::class.java)
    }

    val collectionRepository by lazy {
        CollectionRepository(CollectionLocalSource(), CollectionRemoteSource(musicBrainzApi))
    }

}