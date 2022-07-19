package com.pptg.e_measure.ui.search

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.bean.TaskBean
import com.pptg.e_measure.repository.EMRepository
import com.pptg.e_measure.repository.network.Api.ApiNet
import com.pptg.e_measure.repository.network.ServiceCreator
import com.pptg.e_measure.repository.network.response.TaskResponse
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class SearchViewModel : ViewModel(){
    companion object {
        private const val TAG = "SearchViewModel"
    }
    var searchContent = ""

    var isSearch:MutableLiveData<SearchEnum> = MutableLiveData(SearchEnum.Init)
    private var mList:MutableList<TaskBean> = ArrayList()
    var mSearchList:MutableList<TaskBean> = ArrayList()
    init {
        mList = EMApplication.dbManager.getTaskDao().queryTask() as MutableList<TaskBean>
    }
    lateinit var job:Job

    fun search() {
        // 如果协程已经被创建，就取消先前的协程
        if(this::job.isInitialized) { job.cancel() }
        isSearch.value = SearchEnum.START
        job = GlobalScope.launch(Dispatchers.IO) {
            try {
                val body = EMRepository.getTask()
                body.data.let {
                    mList = it as MutableList<TaskBean>
                    for (task in mList) {
                        searchContent = searchContent.uppercase()
                        if (task.name.contains(searchContent)) {
                            mSearchList.add(task)
                        }
                    }
                }
                isSearch.postValue(SearchEnum.SUCCESS)
            }catch (e:Exception){
                isSearch.postValue(SearchEnum.FAILED)
                e.printStackTrace()
            }
        }
    }
}