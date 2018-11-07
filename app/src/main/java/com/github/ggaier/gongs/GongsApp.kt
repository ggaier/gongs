package com.github.ggaier.gongs

import android.app.Application
import timber.log.Timber

/**
 * Created by wenbo, 2018/11/7
 */
class GongsApp: Application(){

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}