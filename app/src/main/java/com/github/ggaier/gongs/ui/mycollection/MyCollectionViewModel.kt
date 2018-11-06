package com.github.ggaier.gongs.ui.mycollection

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ggaier.gongs.data.CollectionRepository
import com.github.ggaier.gongs.util.launchSilent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by wenbo, 2018/11/5
 */
class MyCollectionViewModel(private val context: Application, private val repo: CollectionRepository) :
    ViewModel(), CoroutineScope{

    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private lateinit var collectionMbid: MutableLiveData<String>

    fun getCollection() = launchSilent {
        if(!::collectionMbid.isInitialized){
            collectionMbid = MutableLiveData()
        }
        repo.getMyArtistCollection("31c35274-ffb8-4280-86e2-caede042e474 ").artists
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}