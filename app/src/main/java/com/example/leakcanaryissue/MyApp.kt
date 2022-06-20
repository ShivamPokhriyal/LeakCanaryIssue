package com.example.leakcanaryissue

import android.app.Application

open class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            LeakCanaryInitializer.init()
        }
    }
}
