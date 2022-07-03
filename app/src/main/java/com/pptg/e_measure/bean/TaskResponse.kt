package com.pptg.e_measure.bean

import com.google.gson.annotations.SerializedName

/**
 * TaskResponse: 用于接受服务器最新"数据报表"的响应
 * TaskBeanData: TaskResponse的Data部分，存储数据表的信息
 */

data class TaskResponse(@SerializedName("msg") val msg:String,
                        @SerializedName("code") val code:String,
                        @SerializedName("data") val data:List<TaskBeanData>)

// TaskBeanData
//{
//    "id": 1
//    "name": "CZ2220",
//    "disp": "复式交分道岔关键数据测量分析表",
//    "attr": "21-22(翼轨咽喉口）1,21-22(翼轨咽喉口）2,21-22(翼轨咽喉口）3,钝角顶点到外基本轨内侧",
//    "lim_l": "388,388,388,319",
//    "lim_r": "408,408,408,339"
//}
data class TaskBeanData(@SerializedName("id") val id:String,
                        @SerializedName("name") val name:String,
                        @SerializedName("disp") val disp:String,
                        @SerializedName("attr") val attr:String,
                        @SerializedName("lim_l") val lim_l:String,
                        @SerializedName("lim_r") val lim_r:String)