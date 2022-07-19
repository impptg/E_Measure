package com.pptg.e_measure.ui.History

import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.bean.HistoryBean


class HistoryViewModel():ViewModel() {

    companion object{
        private const val TAG = "HistoryViewModel"
    }

    lateinit var mList:List<HistoryBean>
    init {
        // 查询所有历史
        mList = EMApplication.dbManager.getHistoryDao().queryHistory()
    }


}


