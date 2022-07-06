package com.pptg.e_measure.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.R
import com.pptg.e_measure.bean.Notificaton

class NotificationAdapter(val itemList: List<Notificaton>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.notificationItem)
        val image: ImageView = view.findViewById(R.id.itemImage)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_item,null,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationAdapter.ViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemName.text = item.item
        holder.image.setImageResource(item.imgeId)
    }

    override fun getItemCount() = itemList.size

}