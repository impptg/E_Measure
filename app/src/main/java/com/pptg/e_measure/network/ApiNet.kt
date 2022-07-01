package com.pptg.e_measure.network

import com.pptg.e_measure.bean.LoginBean
import com.pptg.e_measure.bean.TaskResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*

interface ApiNet {
    @FormUrlEncoded
    @POST("login")
    fun Login(@Field("id") user_name:String,
              @Field("pswd") user_pswd:String): Call<LoginBean>

    @GET("task")
    fun Task(): Call<TaskResponse>

}