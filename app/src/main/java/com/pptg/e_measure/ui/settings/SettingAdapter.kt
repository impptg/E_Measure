package com.pptg.e_measure.ui.settings

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.R
import com.pptg.e_measure.bean.SettingsBean
import com.pptg.e_measure.ui.changePswd.ChangePswdActivity
import com.pptg.e_measure.ui.user.UserActivity
import com.pptg.e_measure.utils.*

class SettingsAdapter(val fragment: SettingsFragment,val mList: List<SettingsBean>) : RecyclerView.Adapter<SettingsBaseHolder>() {

    companion object {
        const val TYPE_USER = 1
        const val TYPE_NORMAL = 2
        const val TYPE_BLANK = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsBaseHolder {
        when (viewType) {
            TYPE_USER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_settings_user, parent, false)
                return UserHolder(view)
            }
            TYPE_NORMAL -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_settings_normal, parent, false)
                return NormalHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_settings_blank, parent, false)
                return BlankHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: SettingsBaseHolder, position: Int) {
        val mBean = mList[position]
        when (holder) {
            is UserHolder -> {
                // holder.iv_user.setBackgroundResource(R.drawable.frog)
                holder.tv_username.text = "pptg"
                //账户信息的点击响应
                holder.itemView.setOnClickListener {
                    Toast.makeText(EMApplication.context, "更改头像、昵称、账户等其他信息", Toast.LENGTH_LONG)
                        .show()
                    val intent = Intent(fragment.context,UserActivity::class.java)
                    fragment.startActivity(intent)
                }
            }
            is NormalHolder -> {
                holder.tv_settings.text = mBean.item
                holder.iv_settings.setBackgroundResource(mBean.imgeId)
                holder.iv_settings.setBackgroundTintColor(mBean.color)
                //安全（修改密码的点击响应）
                holder.itemView.setOnClickListener {
                    if (holder.tv_settings.text.equals("安全")) {
                        Toast.makeText(EMApplication.context, "修改密码", Toast.LENGTH_LONG).show()
                        val context = holder.itemView.context
                        var intent = Intent(context, ChangePswdActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            }
            is BlankHolder -> {

            }
        }
    }

    override fun getItemCount() = mList.size

    override fun getItemViewType(position: Int): Int {
        return mList[position].type
    }

    class UserHolder(view: View) : SettingsBaseHolder(view) {
        var tv_username: TextView = view.findViewById(R.id.tv_username)
        var iv_user: ImageView = view.findViewById(R.id.iv_user)
    }

    class NormalHolder(view: View) : SettingsBaseHolder(view) {
        var tv_settings: TextView = view.findViewById(R.id.tv_settings)
        var iv_settings: ImageView = view.findViewById(R.id.iv_icon)
    }

    class BlankHolder(view: View) : SettingsBaseHolder(view)
}
sealed class SettingsBaseHolder(view: View) : RecyclerView.ViewHolder(view)

