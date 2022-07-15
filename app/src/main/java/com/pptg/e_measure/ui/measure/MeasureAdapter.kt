package com.pptg.e_measure.ui.measure

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.R
import com.pptg.e_measure.bean.MeasureBean

class MeasureAdapter(var mList:List<MeasureBean>):
    RecyclerView.Adapter<MeasureAdapter.ViewHolder>() {

    private lateinit var mOnItemClickListener:OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, pos: Int)
    }

    fun setOnItemClickListener(mOnItemClickListener:OnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_measure,parent,false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mBean: MeasureBean = mList[position]
        holder.apply {
            tv_item_measure_attr.text = mBean.attr
            tv_item_measure_upper.text = mBean.lim_r
            tv_item_measure_lower.text = mBean.lim_l
            tv_item_measure_value.text = mBean.value
            cv_item_measure.setOnClickListener{
                mOnItemClickListener.onItemClick(itemView,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var cv_item_measure: CardView = view.findViewById(R.id.cv_item_measure)
        var tv_item_measure_attr: TextView = view.findViewById(R.id.tv_item_measure_attr)
        var tv_item_measure_value: TextView = view.findViewById(R.id.tv_item_measure_value)
        var tv_item_measure_upper: TextView = view.findViewById(R.id.tv_item_measure_upper)
        var tv_item_measure_lower: TextView = view.findViewById(R.id.tv_item_measure_lower)
    }

}