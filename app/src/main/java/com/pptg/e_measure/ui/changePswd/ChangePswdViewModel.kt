package com.pptg.e_measure.ui.changePswd

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.repository.EMRepository
import com.pptg.e_measure.repository.network.Api.ApiNet
import com.pptg.e_measure.repository.network.ServiceCreator
import com.pptg.e_measure.repository.network.response.ChangePswdResponse
import com.pptg.e_measure.repository.sp.UserSP
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ChangePswdViewModel: ViewModel() {
    companion object {
        private const val TAG = "ChangePswdViewModel"
    }
    var isChanged : MutableLiveData<ChangedEnum> = MutableLiveData(ChangedEnum.Init)
    var older_pswd = ""
    var new_pswd = ""
    var new_pswd1 = ""
    var user_id = ""

    init {
        user_id = UserSP.getUser(UserSP.USER_ID)
    }

    fun changePswd() {
        isChanged.value = ChangedEnum.START
        if (new_pswd.equals(new_pswd1) && user_id != null && older_pswd != null && new_pswd != null){
            GlobalScope.launch (Dispatchers.IO){
                try {
                    val body = async {EMRepository.postChangePswd(user_id,older_pswd,new_pswd)}.await()
                    if(body.data.status.equals("true")){
                        isChanged.postValue(ChangedEnum.SUCCESS)
                    }else{
                        isChanged.postValue(ChangedEnum.FAILED)
                    }
                }catch (e:Exception){
                    isChanged.postValue(ChangedEnum.FAILED)
                    e.printStackTrace()
                }
            }
        }else{
            isChanged.value = ChangedEnum.WRONG
        }
    }
}
