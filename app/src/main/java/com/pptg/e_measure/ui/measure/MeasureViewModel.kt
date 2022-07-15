package com.pptg.e_measure.ui.measure

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.bean.HistoryBean
import com.pptg.e_measure.bean.MeasureBean
import com.pptg.e_measure.bean.TaskBean
import com.pptg.e_measure.utils.DateUtil
import kotlin.math.log

class MeasureViewModel():ViewModel() {

    companion object {
        private const val TAG = "MeasureViewModel"
    }

    var mList: MutableList<MeasureBean> = ArrayList()
    var mPos:Int = 0
    var mTask:TaskBean = TaskBean()

    fun ScrollPos():Int{
        mPos = mPos + 1
        if(mPos == mList.size){
            mPos = 0
            return 1
        }
        return mPos-1
    }

    fun DecodeTask(TaskID:String){
        mTask = EMApplication.dbManager.getTaskDao().queryTask(TaskID)
        mList = TaskToMeasure(mTask) as MutableList<MeasureBean>
        Log.d(TAG, "DecodeTask: ")
    }

    fun TaskToMeasure(mTaskBean: TaskBean):List<MeasureBean>{
        var mMeasureBeanList:MutableList<MeasureBean> = ArrayList()
        Log.d(TAG, mTaskBean.attr)
        val attr = mTaskBean.attr.split(",")
        val lim_l = mTaskBean.lim_l.split(",")
        val lim_r = mTaskBean.lim_r.split(",")
        for(i in 0..lim_l.size-1){
            var mBean = MeasureBean(attr[i], "-1",lim_l[i],lim_r[i])
            mMeasureBeanList.add(mBean)
        }
        return mMeasureBeanList
    }

    fun save(){
        val mCreateTime = DateUtil.nowTime
        var mValue = ""
        for (i in mList.indices) {
            mValue += mList[i].value
            if (i != mList.size - 1) {
                mValue += ","
            }
        }
        val hisID = mCreateTime.toInt()
        val mHistoryBean = HistoryBean()
        mHistoryBean.apply {
            historyID = hisID
            name = mTask.name
            disp = mTask.disp
            value = mValue
            attr = mTask.attr
            lim_r = mTask.lim_r
            lim_l = mTask.lim_l
            createTime = mCreateTime
            taskID = mTask.taskID.toString()
        }
        EMApplication.dbManager.getHistoryDao().insertHistory(mHistoryBean)
        val s = EMApplication.dbManager.getHistoryDao().queryHistory()
        Log.d(TAG, "save: "+ s)
    }
}