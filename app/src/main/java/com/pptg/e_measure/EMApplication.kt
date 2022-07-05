package com.pptg.e_measure

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.pptg.e_measure.db.DBManager

class EMApplication : Application() {

    companion object {
        // 全局 context
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        // 全局 Dao
        lateinit var dbManager: DBManager
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        dbManager = DBManager.single
    }
}