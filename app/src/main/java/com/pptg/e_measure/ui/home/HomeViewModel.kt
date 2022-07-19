package com.pptg.e_measure.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.repository.network.response.TaskResponse
import com.pptg.e_measure.bean.TaskBean
import com.pptg.e_measure.repository.EMRepository
import com.pptg.e_measure.repository.network.Api.ApiNet
import com.pptg.e_measure.repository.network.ServiceCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class HomeViewModel():ViewModel() {

    companion object{
        private const val TAG = "HomeViewModel"
    }
    // 主页面任务报表
    var mList:MutableList<TaskBean> = ArrayList()
    var isRefresh:MutableLiveData<HomeEnum> = MutableLiveData(HomeEnum.Init)

    init {
        // 离线数据库读取
        mList = EMApplication.dbManager.getTaskDao().queryTask() as MutableList<TaskBean>
    }

    fun refresh(){
        isRefresh.value = HomeEnum.START
        GlobalScope.launch(Dispatchers.IO){
            try {
                val body = async { EMRepository.getTask() }.await()
                body.data.let {
                    mList = it as MutableList<TaskBean>
                    EMApplication.dbManager.getTaskDao().apply {
                        deleteAllTask()
                        insertTask(it)
                    }
                }
                isRefresh.postValue(HomeEnum.SUCCESS)
            }catch (e:Exception){
                e.printStackTrace()
                isRefresh.postValue(HomeEnum.FAILED)
            }
        }
    }
}