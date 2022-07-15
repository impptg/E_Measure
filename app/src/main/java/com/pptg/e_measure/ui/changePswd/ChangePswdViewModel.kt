package com.pptg.e_measure.ui.changePswd

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.network.ApiNet
import com.pptg.e_measure.network.ServiceCreator
import com.pptg.e_measure.network.response.ChangePswdResponse
import com.pptg.e_measure.sp.UserSP
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePswdViewModel: ViewModel() {
    companion object {
        private const val TAG = "ChangePswdViewModel"
    }
    var older_pswd = ""
    var new_pswd = ""
    var new_pswd1 = ""
    var user_id = ""

    init {
        user_id = UserSP.getUser(UserSP.USER_ID)
    }

    fun changePswd() {
        if (new_pswd.equals(new_pswd1) && user_id != null && older_pswd != null && new_pswd != null){
            Log.d(TAG,"进入了第一层判断")

            val appServcie = ServiceCreator.create<ApiNet>()
            Log.d(TAG," "+user_id)
            Log.d(TAG,older_pswd)
            Log.d(TAG,new_pswd)
            appServcie.ChangePswd(user_id,older_pswd,new_pswd).enqueue(object :
                Callback<ChangePswdResponse> {
                override fun onResponse(
                    call: Call<ChangePswdResponse>,
                    response: Response<ChangePswdResponse>) {
                    val body = response.body() as ChangePswdResponse
                    Log.d(TAG,body.toString())
                    if (body.data.status.equals("true")){
                        Log.d(TAG,body.data.status)
                        Toast.makeText(EMApplication.context,body.data.info, Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(EMApplication.context,body.data.info, Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<ChangePswdResponse>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }else{
            Toast.makeText(EMApplication.context,"两次输入的密码不一致",Toast.LENGTH_LONG).show()
            Log.d(TAG,"没有进入第一层判断")
        }

        }
    }
