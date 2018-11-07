package com.github.ggaier.gongs.ui.mycollection

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
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

    private lateinit var collectionMbid: MutableLiveData<String>

    fun getCollection() = launchSilent {
        if (!::collectionMbid.isInitialized) {
            collectionMbid = MutableLiveData()
        }
        repo.getMyArtistCollection("31c35274-ffb8-4280-86e2-caede042e474").artists.run {
            Log.d(TAG, "result: $this")
            artists.addAll(this)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}