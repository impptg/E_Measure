package com.pptg.e_measure.ui.login

import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.repository.EMRepository
import com.pptg.e_measure.repository.sp.UserSP
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel : ViewModel(){
    companion object {
        private const val TAG = "LoginViewModel"
    }

    var isLogin : MutableLiveData<LoginEnum> = MutableLiveData(LoginEnum.Init)
    var user_id = ""
    var user_pswd = ""
    var user_read = false
    var isPreview = false
    init {
        user_id = UserSP.getUser(UserSP.USER_ID)
        user_pswd = UserSP.getUser(UserSP.USER_PSWD)
        user_read = UserSP.getUser(UserSP.USER_READ) == "true"
    }


    fun Login(){
        isLogin.value = LoginEnum.START
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val body = async{EMRepository.postLogin(user_id, user_pswd)}.await()
                if (body.data.status.equals("true")){
                    //完成user_name的存储
                    UserSP.setUser {
                        putString(UserSP.USER_ID,user_id)
                        putString(UserSP.USER_PSWD,user_pswd)
                        putString(UserSP.USER_READ,"true")
                    }
                    isLogin.postValue(LoginEnum.SUCCESS)
                }else{
                    Log.d(TAG, body.toString())
                    isLogin.postValue(LoginEnum.FAILED)
                }
            }catch (e: Exception){
                isLogin.postValue(LoginEnum.FAILED)
                e.printStackTrace()
            }
        }.start()
    }
}