package com.pptg.e_measure.ui.scan

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.R

class ScanAdapter(var mList:MutableList<BluetoothDevice>):
    RecyclerView.Adapter<ScanAdapter.ViewHolder>(){

    private lateinit var mOnItemClickListener:OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, pos: Int)
    }

    fun setOnItemClickListener(mOnItemClickListener:OnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scan,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mBean = mList[position]
        holder.apply {
            tv_name.text = mBean.name
            tv_address.text = mBean.address
            cv_item.setOnClickListener {
                mOnItemClickListener.onItemClick(itemView,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        var tv_name:TextView = view.findViewById(R.id.tv_name)
        var tv_address:TextView = view.findViewById(R.id.tv_address)
        var cv_item:CardView = view.findViewById(R.id.cv_item)
    }
}