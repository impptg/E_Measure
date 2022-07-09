package com.pptg.e_measure.network

import com.pptg.e_measure.network.response.ChangePswdResponse
import com.pptg.e_measure.network.response.LoginResponse
import com.pptg.e_measure.network.response.TaskResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*

interface ApiNet {
    @FormUrlEncoded
    @POST("mobileLogin")
    fun Login(@Field("id") user_id:String,
              @Field("pswd") user_pswd:String): Call<LoginResponse>

    @FormUrlEncoded
    @POST("changepswd")
    fun ChangePswd(@Field("id") user_id:String,
                   @Field("old_pswd") old_pswd:String,
                   @Field("new_pswd") new_pswd:String): Call<ChangePswdResponse>

    @GET("task")
    fun Task(): Call<TaskResponse>

}