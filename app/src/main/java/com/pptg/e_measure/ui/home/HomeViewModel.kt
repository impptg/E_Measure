package com.pptg.e_measure.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.network.response.TaskResponse
import com.pptg.e_measure.bean.TaskBean
import com.pptg.e_measure.network.ApiNet
import com.pptg.e_measure.network.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {

    companion object{
        private const val TAG = "HomeViewModel"
    }

    // 主页面任务报表
    var mList:List<TaskBean> = emptyList()

    private val _mLiveList = MutableLiveData<List<TaskBean>>()
    val mLiveList: LiveData<List<TaskBean>>
        get() = _mLiveList

    init {
        // 离线数据库读取
        mList = EMApplication.dbManager.getTaskDao().queryTask()
    }

    fun refresh(){
        Task()
    }

    fun Task(){
        val appService = ServiceCreator.create<ApiNet>()
        appService.Task().enqueue(object : Callback<TaskResponse> {
            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
                val body = response.body() as TaskResponse
                body.data.let {
                    _mLiveList.value = it
                    EMApplication.dbManager.getTaskDao().insertTask(it)
                }
                Log.d(TAG, "onResponse: "+_mLiveList)
            }
            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}