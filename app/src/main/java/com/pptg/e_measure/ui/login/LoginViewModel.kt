package com.pptg.e_measure.ui.login

import android.content.Intent
import android.util.Log
import android.view.View

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.MainActivity
import com.pptg.e_measure.network.response.LoginResponse
import com.pptg.e_measure.network.ApiNet
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

    fun Login(view: View){
        val appService = ServiceCreator.create<ApiNet>()
        appService.Login(user_id,user_pswd).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val body = response.body() as LoginResponse
                if (body.data.status.equals("true")){
                    Toast.makeText(EMApplication.context, body.data.info, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, body.toString())
                    val context = view.context
                    var intent = Intent(context,MainActivity::class.java)
                    context.startActivity(intent)
                }else{
                    Toast.makeText(EMApplication.context, "账号或密码错误", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, body.toString())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}