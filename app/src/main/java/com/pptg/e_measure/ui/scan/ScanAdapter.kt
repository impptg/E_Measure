package com.pptg.e_measure.ui.scan

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.R

class ScanAdapter(var mList:MutableList<BluetoothDevice>):
    RecyclerView.Adapter<ScanAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scan,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mBean = mList[position]
        holder.tv_name.text = mBean.name
        holder.tv_address.text = mBean.address
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        var tv_name:TextView = view.findViewById(R.id.tv_name)
        var tv_address:TextView = view.findViewById(R.id.tv_address)
    }
}