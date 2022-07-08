package com.pptg.e_measure.ui.settings

import androidx.lifecycle.ViewModel
import com.pptg.e_measure.R
import com.pptg.e_measure.ui.settings.SettingsAdapter
import com.pptg.e_measure.bean.Notificaton


class SettingsViewModel : ViewModel() {

    companion object{
        private const val TAG = "NotificationViewModel"
    }
    var itemList = ArrayList<Notificaton>()
    val adapter = SettingsAdapter(itemList)
    fun notification() {
        itemList.add(Notificaton("用户",-1,1))
        itemList.add(Notificaton("BLANK",-1,3))
        itemList.add(Notificaton("账户与安全",R.drawable.ic_launcher_foreground,2))
        itemList.add(Notificaton("BLANK",-1,3))
        itemList.add(Notificaton("更新报表",R.drawable.ic_launcher_foreground,2))
        itemList.add(Notificaton("BLANK",-1,3))
        itemList.add(Notificaton("设置字体",R.drawable.ic_launcher_foreground,2))
    }

}