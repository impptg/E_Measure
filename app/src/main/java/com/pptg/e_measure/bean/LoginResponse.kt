package com.pptg.e_measure.bean

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("msg") val msg:String,
                @SerializedName("code") val code:String,
                @SerializedName("data") val data:LoginBeanData)

data class LoginBeanData(@SerializedName("login") val login:String,
                         @SerializedName("id") val id:String)