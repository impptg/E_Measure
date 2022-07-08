package com.pptg.e_measure.ui.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.R
import com.pptg.e_measure.bean.Notificaton

class SettingsAdapter(val mList: List<Notificaton>) : RecyclerView.Adapter<SettingsBaseHolder>() {

    companion object {
        const val TYPE_USER = 1
        const val TYPE_NORMAL = 2
        const val TYPE_BLANK = 3
    }

//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val tv_settings: TextView = view.findViewById(R.id.tv_settings)
//        val iv_settings: ImageView = view.findViewById(R.id.iv_settings)
//    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsBaseHolder {
        when(viewType){
            TYPE_USER->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_settings_user,parent,false)
                return UserHolder(view)
            }
            TYPE_NORMAL->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_settings_normal,parent,false)
                return NormalHolder(view)
            }
            else->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_settings_blank,parent,false)
                return BlankHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: SettingsBaseHolder, position: Int) {
        val mBean = mList[position]
        when(holder){
            is UserHolder ->{
                holder.iv_user.setBackgroundResource(R.mipmap.ic_app)
                holder.tv_username.text = "用户名"
            }
            is NormalHolder ->{
                holder.tv_settings.text = mBean.item
                holder.iv_settings.setBackgroundResource(R.drawable.ic_launcher_foreground)
            }
            is BlankHolder ->{

            }
        }
    }

    override fun getItemCount() = mList.size

    override fun getItemViewType(position: Int): Int {
        return mList[position].type
    }

    class UserHolder(view: View): SettingsBaseHolder(view){
        var tv_username:TextView = view.findViewById(R.id.tv_username)
        var iv_user:ImageView = view.findViewById(R.id.iv_user)
    }

    class NormalHolder(view: View): SettingsBaseHolder(view){
        var tv_settings:TextView = view.findViewById(R.id.tv_settings)
        var iv_settings:ImageView = view.findViewById(R.id.iv_settings)
    }

    class BlankHolder(view: View): SettingsBaseHolder(view){

    }
}
//密封类
sealed class SettingsBaseHolder(view: View) : RecyclerView.ViewHolder(view)
