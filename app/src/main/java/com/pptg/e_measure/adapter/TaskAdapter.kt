package com.pptg.e_measure.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.R
import com.pptg.e_measure.bean.TaskBeanData
import com.pptg.e_measure.db.task.TaskEntity

class TaskAdapter(var mList:List<TaskEntity>):RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ll_item: LinearLayout = view.findViewById(R.id.ll_item)
        val tv_name:TextView = view.findViewById(R.id.tv_name)
        val tv_disp:TextView = view.findViewById(R.id.tv_disp)
        val tv_index:TextView = view.findViewById(R.id.tv_index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mBean: TaskEntity = mList[position]
        holder.tv_name.text = mBean.name
        holder.tv_disp.text = mBean.disp
        holder.tv_index.text = "001"
    }

    override fun getItemCount(): Int {
        return mList.size
    }


}