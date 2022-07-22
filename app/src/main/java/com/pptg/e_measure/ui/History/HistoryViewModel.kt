package com.pptg.e_measure.ui.History

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.bean.DashboardBean
import com.pptg.e_measure.bean.DashboardEnum
import com.pptg.e_measure.bean.HistoryBean
import com.pptg.e_measure.ui.search.SearchEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception


class HistoryViewModel():ViewModel() {

    companion object{
        private const val TAG = "HistoryViewModel"
    }

    var mList:MutableList<DashboardBean> = ArrayList<DashboardBean>()
    var mSelectList:HashMap<Int,Boolean> = HashMap<Int,Boolean>()

    var isSelect:MutableLiveData<HistoryEnum.Select> = MutableLiveData(HistoryEnum.Select.INIT)
    var isDelete:MutableLiveData<HistoryEnum.Delete> = MutableLiveData(HistoryEnum.Delete.INIT)


    init {
        // 查询所有历史
        // mList = EMApplication.dbManager.getHistoryDao().queryHistory()

        val mBean = DashboardBean("CZ2220","测试测试测试","2022-0719 10:30", DashboardEnum.FINISH)
        mList.add(mBean)
        val mBean1 = DashboardBean("CZ2320","测试测试测试","2022-0719 11:30", DashboardEnum.FINISH)
        mList.add(mBean1)
        val mBean2 = DashboardBean("AQ2e20","测试测试测试","2022-0719 11:33", DashboardEnum.FINISH)
        mList.add(mBean2)
        val mBean3 = DashboardBean("AQ2e20","测试测试测试","2022-0719 11:33", DashboardEnum.FINISH)
        mList.add(mBean3)
        val mBean4 = DashboardBean("AQ2e20","测试测试测试","2022-0719 11:33", DashboardEnum.FINISH)
        mList.add(mBean4)
        val mBean5 = DashboardBean("CZ2220","测试测试测试","2022-0719 10:30", DashboardEnum.FINISH)
        mList.add(mBean5)
        val mBean6 = DashboardBean("CZ2220","测试测试测试","2022-0719 10:30", DashboardEnum.FINISH)
        mList.add(mBean6)
        val mBean7 = DashboardBean("CZ22120","测试测试测试","2022-0719 10:30", DashboardEnum.FINISH)
        mList.add(mBean7)
        val mBean8 = DashboardBean("CZ22320","测试测试测试","2022-0719 10:30", DashboardEnum.FINISH)
        mList.add(mBean8)
    }

    fun changeSelectStatus(){
        isSelect.value = when(isSelect.value){
            HistoryEnum.Select.INIT -> HistoryEnum.Select.START
            else -> HistoryEnum.Select.INIT
        }
    }

    fun selectItem(i:Int){
        // 选取了Item i，在选择列表中记录
        if(mSelectList[i] == true){
            mSelectList.remove(i)
        }else{
            mSelectList[i] = true
        }
        isSelect.value = HistoryEnum.Select.REFRESH
    }

    fun selectAll(){
        for(i in 0..mList.size-1){
            mSelectList[i] = true
        }
        isSelect.value = HistoryEnum.Select.REFRESH
    }

    fun clearAll(){
        mSelectList.clear()
        isSelect.value = HistoryEnum.Select.REFRESH
    }

    fun delete(){
        isDelete.value = HistoryEnum.Delete.INIT

        GlobalScope.launch(Dispatchers.IO) {
            isDelete.postValue(HistoryEnum.Delete.START)

            // TODO 数据库删除 && 网络
            try {
                isDelete.postValue(HistoryEnum.Delete.SUCCESS)
            }catch (e:Exception){
                e.printStackTrace()
                isDelete.postValue(HistoryEnum.Delete.FAILED)
            }
        }
        isSelect.value = HistoryEnum.Select.REFRESH
    }
}


