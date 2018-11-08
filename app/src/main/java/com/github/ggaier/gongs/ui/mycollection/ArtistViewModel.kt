package com.github.ggaier.gongs.ui.mycollection

import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.github.ggaier.gongs.data.CollectionRepository
import com.github.ggaier.gongs.util.launchSilent
import com.github.ggaier.gongs.vo.Album
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
    val showError = ObservableBoolean()


    fun loadReleases(mbid: String, refresh: Boolean) = launchSilent {
        if (refresh) {
            loading[mbid] = true
            showError.set(false)
        }
        try {
            albums[mbid] = repo.getReleasesByArtist(mbid).releases
        } catch (e: Exception) {
            e.printStackTrace()
            if (e !is CancellationException) {
                if (refresh) {
                    showError.set(true)
                }
            }
        }
        if (refresh) {
            loading[mbid] = false
        }
    }


}