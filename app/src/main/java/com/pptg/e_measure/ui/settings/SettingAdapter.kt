package com.pptg.e_measure.ui.settings

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.R

class SettingAdapter(var mList: MutableList<SettingsViewModel.SettingsBean>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val ITEM_TYPE = 1
        const val BLANK_TYPE = 2
    }

    open class SettingHolder(view: View):RecyclerView.ViewHolder(view)

    inner class ItemHolder(view: View):SettingHolder(view){
        var tv_settings:TextView = view.findViewById(R.id.tv_settings)
    }

    inner class BlankHolder(view: View):SettingHolder(view){
        var tv_blank:TextView = view.findViewById(R.id.tv_blank)
    }

    // 根据不同type返回不同holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == ITEM_TYPE){
            return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_settings, parent, false))
        }else{
            return BlankHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_blank, parent, false))
        }
    }

    // 根据不同type显示不同页面
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mBean = mList[position]
        when(getItemViewType(position)){
            ITEM_TYPE -> {
                var h = holder as ItemHolder
                h.tv_settings.text = mBean.text
                // ...
            }
            else -> {
                var h = holder as BlankHolder
                // ...
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        val mBean = mList[position]
        return when(mBean.type){
            1 -> ITEM_TYPE
            else -> BLANK_TYPE
        }
    }
}