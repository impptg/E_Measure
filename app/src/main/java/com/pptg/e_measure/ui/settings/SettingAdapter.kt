package com.pptg.e_measure.ui.settings

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.TintAwareDrawable
import androidx.core.view.TintableBackgroundView
import androidx.core.widget.TintableImageSourceView
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.R
import com.pptg.e_measure.bean.SettingsBean

class SettingsAdapter(val mList: List<SettingsBean>) : RecyclerView.Adapter<SettingsBaseHolder>() {

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
                // holder.iv_user.setBackgroundResource(R.drawable.frog)
                holder.tv_username.text = "pptg"
            }
            is NormalHolder ->{
                holder.tv_settings.text = mBean.item
                holder.iv_settings.setBackgroundResource(mBean.imgeId)
                holder.iv_settings.setBackgroundTintColor(mBean.color)
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
        var iv_settings:ImageView = view.findViewById(R.id.iv_icon)
    }

    class BlankHolder(view: View): SettingsBaseHolder(view){

    }


    /**
     * 拓展 ImageView，使其支持自定义颜色
     */
    fun View.setBackgroundTintRes(@ColorRes colorRes: Int) {
        val colorStateList = ContextCompat.getColorStateList(this.context, colorRes)
        setBackgroundTintColor(colorStateList)
    }

    fun View.setBackgroundTintColor(@ColorInt colorInt: Int) {
        val colorStateList = ColorStateList.valueOf(colorInt)
        setBackgroundTintColor(colorStateList)
    }

    fun View.setBackgroundTintColor(colorStateList: ColorStateList?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.backgroundTintList = colorStateList
        } else if (this is TintableBackgroundView) {
            val tintView = this as TintableBackgroundView
            tintView.supportBackgroundTintList = colorStateList
        } else {
            var background: Drawable = this.background ?: return
            if (background !is TintAwareDrawable) {
                background = DrawableCompat.wrap(background)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    this.background = background
                } else {
                    this.setBackgroundDrawable(background)
                }
            }
            DrawableCompat.setTintList(background, colorStateList)
        }
    }

    fun ImageView.setSrcTintRes(@ColorRes colorRes: Int) {
        val colorStateList = ContextCompat.getColorStateList(this.context, colorRes)
        setSrcTint(colorStateList)
    }

    @SuppressLint("RestrictedApi")
    fun ImageView.setSrcTint(colorStateList: ColorStateList?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.imageTintList = colorStateList
        } else if (this is TintableImageSourceView) {
            val tintView = this as TintableImageSourceView
            tintView.supportImageTintList = colorStateList
        } else {
            var drawable: Drawable = this.drawable ?: return
            if (drawable !is TintAwareDrawable) {
                drawable = DrawableCompat.wrap(drawable)
                this.setImageDrawable(drawable)
            }
            DrawableCompat.setTintList(drawable, colorStateList)
        }
    }
}
//密封类
sealed class SettingsBaseHolder(view: View) : RecyclerView.ViewHolder(view)
