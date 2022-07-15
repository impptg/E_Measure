package com.pptg.e_measure.utils

import android.annotation.SuppressLint
import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateUtil {
    // 获取当前的日期时间
    fun getNowDateTime(formatStr: String?): String {
        var format = formatStr
        if (TextUtils.isEmpty(format)) {
            format = "yyyyMMddHHmmss"
        }
        val sdf = SimpleDateFormat(format)
        return sdf.format(Date())
    }

    // 获取当前的时间
    val nowTime: String
        get() {
            val sdf = SimpleDateFormat("HHmmss")
            return sdf.format(Date())
        }

    // 获取当前的时间（精确到毫秒）
    val nowTimeDetail: String
        get() {
            val sdf = SimpleDateFormat("HH:mm:ss.SSS")
            return sdf.format(Date())
        }

    // 获取当前的日期
    val nowDate: String
        get() {
            val sdf = SimpleDateFormat("ddHHmmss")
            return sdf.format(Date())
        }
}
