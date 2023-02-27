package com.example.testworkvitaliyptitsyn.ui

import android.support.multidex.MultiDexApplication
import com.example.testworkvitaliyptitsyn.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TestWorkApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}