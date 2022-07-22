package com.pptg.e_measure.ui.History

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.R
import com.pptg.e_measure.bean.DashboardEnum
import com.pptg.e_measure.bean.DashboardBean
import com.pptg.e_measure.bean.TaskBean

class HistoryAdapter(var mList: List<DashboardBean>,var isSelecting:Boolean,var mSelectList:HashMap<Int,Boolean>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    companion object {
        private const val TAG = "HistoryAdapter"
    }
    private lateinit var mItemClickListener:OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, pos: Int)
        fun onItemLongClick(view: View, pos: Int):Boolean
    }

    fun setOnItemClickListener(Item:OnItemClickListener){
        this.mItemClickListener = Item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history,parent,false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mBean = mList[position]
        holder.apply {
            tv_name.text = mBean.name
            tv_disp.text = mBean.disp
            tv_time.text = mBean.time
            when (mBean.status) {
                DashboardEnum.TO_DO -> {
                    cv_status.setCardBackgroundColor(
                        EMApplication.context.getResources()
                            .getColor(R.color.status_1, null)
                    )
                    tv_status.text = "待测量"
                }
                DashboardEnum.TO_SUBMIT -> {
                    cv_status.setCardBackgroundColor(
                        EMApplication.context.getResources()
                            .getColor(R.color.status_2, null)
                    )
                    tv_status.text = "待提交"
                }
                DashboardEnum.REVIEWING -> {
                    cv_status.setCardBackgroundColor(
                        EMApplication.context.getResources()
                            .getColor(R.color.status_3, null)
                    )
                    tv_status.text = "审核中"
                }
                DashboardEnum.MODIFY -> {
                    cv_status.setCardBackgroundColor(
                        EMApplication.context.getResources()
                            .getColor(R.color.status_4, null)
                    )
                    tv_status.text = "请修改"
                }
                DashboardEnum.FINISH -> {
                    cv_status.setCardBackgroundColor(
                        EMApplication.context.getResources()
                            .getColor(R.color.status_5, null)
                    )
                    tv_status.text = "已完成"
                }
            }
            cb_history.visibility = when (isSelecting) {
                    true -> View.VISIBLE
                    else -> View.GONE
                }
            Log.d(TAG, "onBindViewHolder: " + isSelecting)
            Log.d(TAG, "onBindViewHolder: " + cb_history.visibility)
            cb_history.isChecked = isSelecting and (mSelectList[position] == true)

            ll_history.setOnLongClickListener{
                mItemClickListener.onItemLongClick(itemView,position)
            }
            ll_history.setOnClickListener{
                mItemClickListener.onItemClick(itemView,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view) {
        val tv_name:TextView = view.findViewById(R.id.tv_item_history_name)
        val tv_disp:TextView = view.findViewById(R.id.tv_item_history_disp)
        val tv_time:TextView = view.findViewById(R.id.tv_item_history_time)
        val tv_status:TextView = view.findViewById(R.id.tv_item_history_status)
        val cv_status:CardView = view.findViewById(R.id.cv_item_history_status)
        val cb_history:CheckBox = view.findViewById(R.id.cb_history)
        val ll_history:LinearLayout = view.findViewById(R.id.ll_history)
    }
}