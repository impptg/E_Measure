package com.pptg.e_measure.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pptg.e_measure.adapter.TaskAdapter

class SettingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    lateinit var mList:MutableList<SettingsBean>
    lateinit var adapter: SettingAdapter

    class SettingsBean(
        val type:Int,
        val text:String){
    }

    init {
        mList = mutableListOf<SettingsBean>()
        val mBean:SettingsBean = SettingsBean(1,"亮度")
        mList.add(0,mBean)

        val mBean2:SettingsBean = SettingsBean(1,"字体")
        mList.add(1,mBean2)

        val mBean3:SettingsBean = SettingsBean(2,"")
        mList.add(2,mBean3)

        val mBean4:SettingsBean = SettingsBean(1,"其他")
        mList.add(3,mBean4)

        adapter = SettingAdapter(mList)

    }
}