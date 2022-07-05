package com.pptg.e_measure

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class EMApplication : Application() {

    companion object {
        // 全局 context
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}