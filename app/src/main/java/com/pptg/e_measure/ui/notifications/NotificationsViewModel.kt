package com.pptg.e_measure.ui.notifications


import androidx.lifecycle.ViewModel
import com.pptg.e_measure.R
import com.pptg.e_measure.adapter.NotificationAdapter
import com.pptg.e_measure.bean.Notificaton


class NotificationsViewModel : ViewModel() {

    companion object{
        private const val TAG = "NotificationViewModel"
    }
    var itemList = ArrayList<Notificaton>()
    val adapter = NotificationAdapter(itemList)
    fun notification() {
        itemList.add(Notificaton("账户与安全",R.drawable.ic_launcher_foreground))
        itemList.add(Notificaton("更新报表",R.drawable.ic_launcher_foreground))
        itemList.add(Notificaton("设置字体",R.drawable.ic_launcher_foreground))
    }



}