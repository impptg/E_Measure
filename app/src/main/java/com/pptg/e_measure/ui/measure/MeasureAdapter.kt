package com.pptg.e_measure.ui.measure

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.R
import com.pptg.e_measure.bean.MeasureBean

class MeasureAdapter(var mList:List<MeasureBean>):
    RecyclerView.Adapter<MeasureAdapter.ViewHolder>() {

    inner class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var ll_item_measure: LinearLayout = view.findViewById(R.id.ll_item_measure)
        var tv_item_measure_attr: TextView = view.findViewById(R.id.tv_item_measure_attr)
        var tv_item_measure_value: TextView = view.findViewById(R.id.tv_item_measure_value)
        var tv_item_measure_upper: TextView = view.findViewById(R.id.tv_item_measure_upper)
        var tv_item_measure_lower: TextView = view.findViewById(R.id.tv_item_measure_lower)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_measure,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mBean: MeasureBean = mList[position]
        holder.tv_item_measure_attr.text = mBean.attr
        holder.tv_item_measure_upper.text = mBean.lim_r
        holder.tv_item_measure_lower.text = mBean.lim_l
        holder.tv_item_measure_value.text = mBean.value
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}