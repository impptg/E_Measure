package com.pptg.e_measure.bean

import com.google.gson.annotations.SerializedName

/**
 * 用户登陆的响应内容
 */

class LoginBean(@SerializedName("status") val status:String,
                         @SerializedName("info") val info:String)

//{
//    "msg": "success",
//    "code": 0,
//    "data": {
//        "status": "true",
//        "info": "pptg"
//    }
//}