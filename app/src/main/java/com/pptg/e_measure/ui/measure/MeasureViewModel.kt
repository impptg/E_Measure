package com.pptg.e_measure.ui.measure

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.bean.MeasureBean
import com.pptg.e_measure.bean.TaskBean
import kotlin.math.log

class MeasureViewModel():ViewModel() {

    companion object {
        private const val TAG = "MeasureViewModel"
    }

    var mList: MutableList<MeasureBean> = ArrayList()

    fun DecodeTask(TaskID:String){
        var mTask = EMApplication.dbManager.getTaskDao().queryTask(TaskID)
        mList = TaskToMeasure(mTask) as MutableList<MeasureBean>
        Log.d(TAG, "DecodeTask: ")
    }

    fun TaskToMeasure(mTaskBean: TaskBean):List<MeasureBean>{
        var mMeasureBeanList:MutableList<MeasureBean> = ArrayList()
        Log.d(TAG, mTaskBean.attr.toString())
        val attr = mTaskBean.attr.split(",")
        val lim_l = mTaskBean.lim_l.split(",")
        val lim_r = mTaskBean.lim_r.split(",")
        for(i in 0..lim_l.size-1){
            var mBean = MeasureBean(attr[i], "-1",lim_l[i],lim_r[i])
            mMeasureBeanList.add(mBean)
        }
        return mMeasureBeanList
    }
}