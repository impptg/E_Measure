package com.pptg.e_measure.ui.search

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.bean.TaskBean
import com.pptg.e_measure.repository.network.Api.ApiNet
import com.pptg.e_measure.repository.network.ServiceCreator
import com.pptg.e_measure.repository.network.response.TaskResponse
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
    val mSearchList = MutableLiveData<List<TaskBean>>()
    init {
        mList = EMApplication.dbManager.getTaskDao().queryTask()
    }

    val mLiveList = MutableLiveData<List<TaskBean>>()
    fun search() {
        val appService = ServiceCreator.create<ApiNet>()
        // 动画

//        appService.Task().enqueue(object : Callback<TaskResponse> {
//            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
//                Log.d(TAG, "onResponse: 响应")
//                //结束
//                val body = response.body() as TaskResponse
//                mLiveList.value = body.data!!
//                //Log.d(TAG, "onResponse: "+mList)
//                mList = body.data
//                //Log.d(TAG, "onResponse: "+mList)
//                // EMApplication.dbManager.getTaskDao().insertTask(mList)
//                //Log.d(TAG, body.toString())
//                for (task in mList) {
//                    searchContent = searchContent.uppercase()
//                    if (task.name.contains(searchContent)) {
//                       if(task !in searchList) {
//                            searchList.add(task)
//                            mSearchList.value = searchList
//                        }
//                    }
//                }
//
//                if (searchList.isEmpty()) {
//                    Toast.makeText(EMApplication.context,"报表不存在",Toast.LENGTH_LONG).show()
//                }else{
//                    Log.d(TAG,""+searchList.size)
//                }
//
//            }
//
//            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
//                t.printStackTrace()
//            }
//
//        })
    }
}