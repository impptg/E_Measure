package com.pptg.e_measure.repository.network.Api

import com.pptg.e_measure.repository.network.response.ChangePswdResponse
import com.pptg.e_measure.repository.network.response.LoginResponse
import com.pptg.e_measure.repository.network.response.TaskResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*

/**
 * Retrofit网络请求
 */
interface ApiNet {
    // 用户登陆
    @FormUrlEncoded
    @POST("mobileLogin")
    suspend fun Login(@Field("id") user_id:String,
              @Field("pswd") user_pswd:String): LoginResponse

    // 修改密码
    @FormUrlEncoded
    @POST("changepswd")
    suspend fun ChangePswd(@Field("id") user_id:String,
                   @Field("old_pswd") old_pswd:String,
                   @Field("new_pswd") new_pswd:String): ChangePswdResponse

    // 获取数据报表
    @GET("task")
    suspend fun Task(): TaskResponse

}