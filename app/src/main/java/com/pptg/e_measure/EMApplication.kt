package com.pptg.e_measure

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.view.KeyEvent
import android.widget.Toast
import com.pptg.e_measure.db.DBManager

class EMApplication : Application() {

    companion object {
        // 是否Mock
        const val ISMOCK = true
        // 正式地址
        const val HOST_URL = "http://47.95.223.213/myadmin/"
        // MOCK地址
        const val MOCK_URL = "https://mock.apifox.cn/m1/1227265-0-default/myadmin/"
        // 全局 contextß
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        // 全局 Dao
        lateinit var dbManager: DBManager
        // intent信息
        const val TASK_ID = "taskID"
        // sp
        const val USER_SP = "user"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        dbManager = DBManager.single
    }

}