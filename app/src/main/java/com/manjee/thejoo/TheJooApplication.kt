package com.manjee.thejoo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TheJooApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}