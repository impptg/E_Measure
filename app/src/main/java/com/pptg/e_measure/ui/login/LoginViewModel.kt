package com.pptg.e_measure.ui.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMeasureApplication
import com.pptg.e_measure.bean.LoginBean
import com.pptg.e_measure.network.ApiNet
import com.pptg.e_measure.network.HttpCore
import com.pptg.e_measure.network.ServiceCreator
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class LoginViewModel : ViewModel(){
    companion object {
        private const val TAG = "LoginViewModel"
    }
    var user_id = ""
    var user_pswd = ""
    var isPreview = false

    fun Login(){
        val appService = ServiceCreator.create<ApiNet>()
        appService.Login(user_id,user_pswd).enqueue(object : Callback<LoginBean> {
            override fun onResponse(call: Call<LoginBean>, response: Response<LoginBean>) {
                val body = response.body()
                Toast.makeText(EMeasureApplication.context, body.toString(), Toast.LENGTH_SHORT).show()
                Log.d(TAG, body.toString())
            }

            override fun onFailure(call: Call<LoginBean>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }
}