package com.pptg.e_measure.sp

import android.content.Context
import androidx.core.content.edit
import com.pptg.e_measure.EMApplication

class UserSP {

    companion object {
        fun setUserSP(id: String, pswd: String) {
            EMApplication.context.getSharedPreferences(EMApplication.USER_SP, Context.MODE_PRIVATE)
                .edit {
                    putString("id", id)
                    putString("pswd", pswd)
                }
        }

        fun getUserIDSP(): String {
            val id =
                EMApplication.context.getSharedPreferences(
                    EMApplication.USER_SP,
                    Context.MODE_PRIVATE
                )
                    .getString("id", "")
            if (id != null) {
                return id
            } else {
                return ""
            }
        }

        fun getUserPswdSP(): String {
            val pswd =
                EMApplication.context.getSharedPreferences(
                    EMApplication.USER_SP,
                    Context.MODE_PRIVATE
                )
                    .getString("pswd", "")
            if (pswd != null) {
                return pswd
            } else {
                return ""
            }
        }
    }
}