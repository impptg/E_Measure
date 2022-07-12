package com.pptg.e_measure.ui.home

import android.util.Log
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

    var mList:List<TaskBean> = emptyList()

    init {
        mList = EMApplication.dbManager.getTaskDao().queryTask()
    }

    val mLiveList = MutableLiveData<List<TaskBean>>()

    fun refresh(){
        Task()
    }

    fun Task(){
        val appService = ServiceCreator.create<ApiNet>()
        appService.Task().enqueue(object : Callback<TaskResponse> {
            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
                val body = response.body() as TaskResponse
                mLiveList.value = body.data
                Log.d(TAG, "onResponse: "+mList)
                mList = body.data
                Log.d(TAG, "onResponse: "+mList)
                EMApplication.dbManager.getTaskDao().insertTask(mList)
                Log.d(TAG, body.toString())
            }

            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}