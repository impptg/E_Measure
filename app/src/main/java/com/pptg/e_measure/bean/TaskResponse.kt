package com.pptg.e_measure.bean

import com.google.gson.annotations.SerializedName

data class TaskResponse(@SerializedName("msg") val msg:String,
                        @SerializedName("code") val code:String,
                        @SerializedName("data") val data:List<TaskBeanData>)

data class TaskBeanData(@SerializedName("id") val id:String,
                        @SerializedName("name") val name:String,
                        @SerializedName("disp") val disp:String,
                        @SerializedName("content") val content:String,
                        @SerializedName("lim_l") val lim_l:String,
                        @SerializedName("lim_r") val lim_r:String)