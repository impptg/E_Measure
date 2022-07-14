package com.pptg.e_measure.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.View

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.MainActivity
import com.pptg.e_measure.network.response.LoginResponse
import com.pptg.e_measure.network.ApiNet
import com.pptg.e_measure.network.ServiceCreator
import com.pptg.e_measure.sp.UserSP
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class LoginViewModel : ViewModel(){
    companion object {
        private const val TAG = "LoginViewModel"
    }

    var isFinished : MutableLiveData<Boolean> = MutableLiveData(false)
    var user_id = ""
    var user_pswd = ""
    var user_read = false
    var isPreview = false
    init {
        user_id = UserSP.getUser(UserSP.USER_ID)
        user_pswd = UserSP.getUser(UserSP.USER_PSWD)
        user_read = UserSP.getUser(UserSP.USER_READ) == "true"
    }




    fun Login(view: View){
        val appService = ServiceCreator.create<ApiNet>()
        appService.Login(user_id,user_pswd).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val body = response.body() as LoginResponse
                if (body.data.status.equals("true")){
                    Toast.makeText(EMApplication.context, body.data.info, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, body.toString())
                    //完成user_name的存储
                    UserSP.setUser {
                        putString(UserSP.USER_ID,user_id)
                        putString(UserSP.USER_PSWD,user_pswd)
                        putString(UserSP.USER_READ,"true")
                    }
                    val context = view.context
                    var intent = Intent(context,MainActivity::class.java)
                    context.startActivity(intent)
                    isFinished.value = true
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