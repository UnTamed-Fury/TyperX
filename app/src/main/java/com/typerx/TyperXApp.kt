package com.typerx

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import com.typerx.BuildConfig

@HiltAndroidApp
class TyperXApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Initialize logging in debug mode
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}