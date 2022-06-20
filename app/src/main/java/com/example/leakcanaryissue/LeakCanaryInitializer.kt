package com.example.leakcanaryissue

import leakcanary.LeakCanary

object LeakCanaryInitializer {
    fun init() {
        LeakCanary.showLeakDisplayActivityLauncherIcon(true)
        LeakCanary.config = LeakCanary.config.copy(dumpHeap = true)
    }
}

