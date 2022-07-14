package com.pptg.e_measure.network

import com.pptg.e_measure.EMApplication
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    lateinit var BASE_URL:String

    init {
        when(EMApplication.ISMOCK){
            true -> BASE_URL = EMApplication.MOCK_URL
            false -> BASE_URL = EMApplication.HOST_URL
        }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)

}