package com.linvia.app.di

import android.app.Application
import com.linvia.app.util.AppInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LinviaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInitializer.initialize(this)
    }
}
