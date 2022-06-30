package com.pptg.e_measure.bean

import com.google.gson.annotations.SerializedName

data class LoginBean(@SerializedName("status") val status:String,
                @SerializedName("user_name") val user_name:String,
                @SerializedName("login") val login:String)