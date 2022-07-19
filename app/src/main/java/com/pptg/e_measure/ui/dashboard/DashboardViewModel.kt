package com.pptg.e_measure.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.bean.DashboardBean
import com.pptg.e_measure.bean.DashboardEnum
import com.pptg.e_measure.bean.TaskBean

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    var mList:MutableList<DashboardBean> = ArrayList<DashboardBean>()
    val text: LiveData<String> = _text

    init {
        val mBean = DashboardBean("CZ2220","测试测试测试","2022-0719 10:30",DashboardEnum.TO_DO)
        mList.add(mBean)
        val mBean1 = DashboardBean("CZ2320","测试测试测试","2022-0719 11:30",DashboardEnum.TO_SUBMIT)
        mList.add(mBean1)
        val mBean2 = DashboardBean("AQ2e20","测试测试测试","2022-0719 11:33",DashboardEnum.REVIEWING)
        mList.add(mBean2)
        val mBean3 = DashboardBean("AQ2e20","测试测试测试","2022-0719 11:33",DashboardEnum.MODIFY)
        mList.add(mBean3)
        val mBean4 = DashboardBean("AQ2e20","测试测试测试","2022-0719 11:33",DashboardEnum.FINISH)
        mList.add(mBean4)
    }
}