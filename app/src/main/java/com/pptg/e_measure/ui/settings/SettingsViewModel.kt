package com.pptg.e_measure.ui.settings

import androidx.lifecycle.ViewModel
import com.pptg.e_measure.EMApplication
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
//        itemList.add(Notificaton("BLANK",-1,3))
        itemList.add(Notificaton("安全",R.drawable.ic_baseline_security_24,2,
            EMApplication.context.getResources().getColor(R.color.green_400,null)))
//        itemList.add(Notificaton("BLANK",-1,3))
        itemList.add(Notificaton("设备",R.drawable.ic_baseline_devices_other_24,2,
            EMApplication.context.getResources().getColor(R.color.blue_300,null)))
//        itemList.add(Notificaton("BLANK",-1,3))
        itemList.add(Notificaton("字体",R.drawable.ic_baseline_font_download_24,2,
            EMApplication.context.getResources().getColor(R.color.black,null)))
        itemList.add(Notificaton("其他",R.drawable.ic_baseline_more_horiz_24,2,
            EMApplication.context.getResources().getColor(R.color.orange_700,null)))
    }

}