package com.github.ggaier.gongs.ui.mycollection

import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.ViewModel
import com.github.ggaier.gongs.data.CollectionRepository
import com.github.ggaier.gongs.util.launchSilent
import com.github.ggaier.gongs.vo.Album
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/**
 * Created by wenbo, 2018/11/5
 */
class ArtistViewModel(private val repo: CollectionRepository) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val albums = ObservableArrayMap<String, List<Album>>()
    val loading = ObservableArrayMap<String, Boolean>()

    fun getReleases(mbid: String) = launchSilent {
        Timber.d("reference: ${this@ArtistViewModel}")
        loading[mbid] = true
        albums[mbid] = repo.getReleasesByArtist(mbid).releases
        loading[mbid] = false
    }


}