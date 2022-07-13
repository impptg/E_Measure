package com.pptg.e_measure.ui.search

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.bean.TaskBean
import com.pptg.e_measure.network.ApiNet
import com.pptg.e_measure.network.ServiceCreator
import com.pptg.e_measure.network.response.TaskResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchViewModel : ViewModel(){
    companion object {
        private const val TAG = "SearchViewModel"
    }
    var searchContent = ""

    var mList:List<TaskBean> = emptyList()
    var searchList = mutableListOf<TaskBean>()
    init {
        mList = EMApplication.dbManager.getTaskDao().queryTask()
    }

    val mLiveList = MutableLiveData<List<TaskBean>>()
    fun search() {
        val appService = ServiceCreator.create<ApiNet>()
        appService.Task().enqueue(object : Callback<TaskResponse> {
            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
                val body = response.body() as TaskResponse
                mLiveList.value = body.data
                //Log.d(TAG, "onResponse: "+mList)
                mList = body.data
                //Log.d(TAG, "onResponse: "+mList)
                EMApplication.dbManager.getTaskDao().insertTask(mList)
                //Log.d(TAG, body.toString())
                for (task in mList) {
                    if (searchContent.equals(task.name)) {
                        searchList.add(task)
                    }
                }
                if (searchList.isEmpty()) {
                    Toast.makeText(EMApplication.context,"报表不存在",Toast.LENGTH_LONG).show()
                }else{
                    Log.d(TAG,""+searchList.size)
                }

            }

            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}