package com.github.ggaier.gongs.ui.mycollection

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.github.ggaier.gongs.data.CollectionRepository
import com.github.ggaier.gongs.util.launchSilent
import com.github.ggaier.gongs.vo.Artist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by wenbo, 2018/11/5
 */
class MyCollectionViewModel(private val context: Application, private val repo: CollectionRepository) :
    ViewModel(), CoroutineScope {

    private val TAG = "Gongs"

    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val artists = ObservableArrayList<Artist>()
    val loading = ObservableBoolean(false)

    fun getCollection(mbid: String) = launchSilent {
        loading.set(true)
        repo.getMyArtistCollection(mbid).artists.run {
            artists.addAll(this)
        }
        loading.set(false)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}