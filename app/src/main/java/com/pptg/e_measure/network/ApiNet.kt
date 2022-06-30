package com.pptg.e_measure.network

import com.pptg.e_measure.bean.LoginBean
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*

interface ApiNet {
    @FormUrlEncoded
    @POST("login_formdata.php")
    fun Login(@Field("user_name") user_name:String,
              @Field("user_password") user_pswd:String,
              @Field("user_type") user_type:String="normal"): Call<LoginBean>

    @GET("points.php")
    fun GetPoint()
}