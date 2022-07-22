package com.pptg.e_measure.ui.History

import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.bean.DashboardBean
import com.pptg.e_measure.bean.DashboardEnum
import com.pptg.e_measure.bean.HistoryBean


class HistoryViewModel():ViewModel() {

    companion object{
        private const val TAG = "HistoryViewModel"
    }

    var mList:MutableList<DashboardBean> = ArrayList<DashboardBean>()
    var mSelectList:HashMap<Int,Boolean> = HashMap<Int,Boolean>()
    var isSelect = false


    init {
        // 查询所有历史
        // mList = EMApplication.dbManager.getHistoryDao().queryHistory()

        val mBean = DashboardBean("CZ2220","测试测试测试","2022-0719 10:30", DashboardEnum.TO_DO)
        mList.add(mBean)
        val mBean1 = DashboardBean("CZ2320","测试测试测试","2022-0719 11:30", DashboardEnum.TO_SUBMIT)
        mList.add(mBean1)
        val mBean2 = DashboardBean("AQ2e20","测试测试测试","2022-0719 11:33", DashboardEnum.REVIEWING)
        mList.add(mBean2)
        val mBean3 = DashboardBean("AQ2e20","测试测试测试","2022-0719 11:33", DashboardEnum.MODIFY)
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
        isSelect = !isSelect
    }

    fun selectItem(i:Int){
        // 选取了Item i，在选择列表中记录
        if(mSelectList[i] == true){
            mSelectList.remove(i)
        }else{
            mSelectList[i] = true
        }
    }
}


