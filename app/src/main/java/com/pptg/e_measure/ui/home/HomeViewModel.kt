package com.pptg.e_measure.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.adapter.TaskAdapter
import com.pptg.e_measure.bean.TaskBeanData
import com.pptg.e_measure.bean.TaskResponse
import com.pptg.e_measure.network.ApiNet
import com.pptg.e_measure.network.ServiceCreator
import com.pptg.e_measure.ui.login.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {

    companion object{
        private const val TAG = "HomeViewModel"
    }

    var mList:List<TaskBeanData> = emptyList()
    var adapter: TaskAdapter = TaskAdapter(mList)

    fun Task(){
        val appService = ServiceCreator.create<ApiNet>()
        appService.Task().enqueue(object : Callback<TaskResponse> {
            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
                val body = response.body() as TaskResponse
                adapter.mList = body.data
                adapter.notifyDataSetChanged()
                Log.d(TAG, body.toString())
            }

            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}