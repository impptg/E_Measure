package com.pptg.e_measure

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class EMeasureApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        // 全局 context
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}