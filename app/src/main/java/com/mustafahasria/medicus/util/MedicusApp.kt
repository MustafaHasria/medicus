package com.mustafahasria.medicus.util

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MedicusApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
