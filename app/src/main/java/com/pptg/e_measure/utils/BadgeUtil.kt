package com.pptg.e_measure.utils

import android.app.Activity
import android.content.Context
import android.view.View
import q.rorbin.badgeview.QBadgeView
import android.view.Gravity
import com.pptg.e_measure.ui.dashboard.DashboardFragment
import q.rorbin.badgeview.Badge


object BadgeUtil {
    /**
     * 右上小红点数量提示
     * @param context  当前的activity
     * @param view  要显示的控件
     * @param i 数量（点内数字）
     */
    fun QBadge(context: Context, view: View?, i: Int) {
        QBadgeView(context)
            .bindTarget(view)
            .setBadgeNumber(i)
            .setBadgeGravity(Gravity.END or Gravity.TOP)
            .setGravityOffset(30f, 0f, true)
            .setShowShadow(false)
            .setOnDragStateChangedListener { dragState, badge, targetView -> }
    }
}