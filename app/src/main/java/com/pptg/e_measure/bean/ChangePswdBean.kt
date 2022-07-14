package com.pptg.e_measure.bean

import com.google.gson.annotations.SerializedName

/**
 * 修改密码的响应内容
 */

class ChangePswdBean(@SerializedName("status") val status:String,
                          @SerializedName("info") val info:String)

//{
//    "msg": "success",
//    "code": 0,
//    "data": {
//        "status": "true",
//        "info": "pswd changed"
//    }
//}