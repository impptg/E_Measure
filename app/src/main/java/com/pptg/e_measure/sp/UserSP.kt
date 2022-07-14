package com.pptg.e_measure.sp

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.pptg.e_measure.EMApplication

class UserSP {
    companion object {
        // sharedpreferences
        // id
        const val USER_ID = "id"
        // 密码
        const val USER_PSWD = "pswd"
        // 是否阅读协议
        const val USER_READ = "read"

        private val SP = EMApplication.context.getSharedPreferences(EMApplication.USER_SP,Context.MODE_PRIVATE)

        // 设置
        fun setUser(block: SharedPreferences.Editor.() -> Unit){
            SP.edit{
                    block()
            }
        }

        // 空返回""
        fun getUser(index:String):String{
            return SP.getString(index,"") ?: ""
        }
    }
}