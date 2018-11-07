package com.github.ggaier.gongs.ui.mycollection

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.github.ggaier.gongs.data.CollectionRepository
import com.github.ggaier.gongs.util.launchSilent
import com.github.ggaier.gongs.vo.Album
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by wenbo, 2018/11/5
 */
class ArtistViewModel(private val repo: CollectionRepository):ViewModel(), CoroutineScope{

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val albums = ObservableArrayList<Album>()
    val loading = ObservableBoolean()

    fun getReleases(mbid: String) = launchSilent {
        loading.set(false)
        albums.addAll(repo.getReleasesByArtist(mbid).releases)
        loading.set(true)
    }


}