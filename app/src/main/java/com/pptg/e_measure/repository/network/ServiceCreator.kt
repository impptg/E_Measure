package com.pptg.e_measure.repository.network

import com.pptg.e_measure.EMApplication
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 根据EMApplication的配置
 * 创建Retrofit
 */
object ServiceCreator {
    private val BASE_URL= when(EMApplication.ISMOCK){
        true -> EMApplication.MOCK_URL
        false -> EMApplication.HOST_URL
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}