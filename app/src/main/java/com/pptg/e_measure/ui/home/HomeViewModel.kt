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
    var adapter: TaskAdapter = TaskAdapter(mList)

    init {
        mList = EMApplication.dbManager.getTaskDao().queryTask()
        adapter.mList = mList
    }

    val mLiveList = MutableLiveData<List<TaskBean>>()

    fun refresh(){
        Task()
        // mLiveList.value = mList
    }

    fun Task(){
        val appService = ServiceCreator.create<ApiNet>()
        appService.Task().enqueue(object : Callback<TaskResponse> {
            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
                val body = response.body() as TaskResponse
                adapter.mList = body.data
                mLiveList.value = body.data
                // EMApplication.dbManager.getTaskDao().insertTask(adapter.mList)
                adapter.notifyDataSetChanged()
                mList = EMApplication.dbManager.getTaskDao().queryTask()
                Log.d(TAG, body.toString())
            }

            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}