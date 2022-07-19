package com.pptg.e_measure.ui.History

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.R
import com.pptg.e_measure.bean.HistoryBean
import com.pptg.e_measure.bean.TaskBean

class HistoryAdapter(var activity: HistoryActivity,var historyList: List<HistoryBean>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view) {
        val tv_name: TextView = view.findViewById(R.id.tv_name)
        val tv_disp: TextView = view.findViewById(R.id.tv_disp)
        val tv_index: TextView = view.findViewById(R.id.tv_index)
        val tv_time: TextView = view.findViewById(R.id.tv_time)
        val cv_item: CardView = view.findViewById(R.id.cv_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history,parent,false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {  }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mBean: HistoryBean = historyList[position]
        when(position%3) {
            0 -> holder.cv_item.setCardBackgroundColor(
                EMApplication.context.getResources()
                    .getColor(R.color.orange_700,null))
            1 -> holder.cv_item.setCardBackgroundColor(
                EMApplication.context.getResources()
                    .getColor(R.color.blue_300,null))
            2 -> holder.cv_item.setCardBackgroundColor(
                EMApplication.context.getResources()
                    .getColor(R.color.green_500,null))
        }


        holder.apply {
            tv_name.text = mBean.name
            tv_disp.text = mBean.disp
            tv_index.text = mBean.taskID.toString()
            tv_time.text = mBean.createTime.toString()
        }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}