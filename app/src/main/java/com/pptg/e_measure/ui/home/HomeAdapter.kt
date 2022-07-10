package com.pptg.e_measure.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.R
import com.pptg.e_measure.bean.TaskBean
import com.pptg.e_measure.ui.measure.MeasureActivity

class HomeAdapter(var fragment: HomeFragment, var mList:List<TaskBean>):
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tv_name:TextView = view.findViewById(R.id.tv_name)
        val tv_disp:TextView = view.findViewById(R.id.tv_disp)
        val tv_index:TextView = view.findViewById(R.id.tv_index)
        val cv_item:CardView = view.findViewById(R.id.cv_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val mBean = mList[holder.adapterPosition]
            val intent = Intent(fragment.context,MeasureActivity::class.java).apply {
                putExtra(EMApplication.TASK_ID,mBean.taskID.toString())
            }
            fragment.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mBean: TaskBean = mList[position]
        when(position%3) {
            0 -> holder.cv_item.setCardBackgroundColor(EMApplication.context.getResources()
                .getColor(R.color.orange_700,null))
            1 -> holder.cv_item.setCardBackgroundColor(EMApplication.context.getResources()
                .getColor(R.color.blue_300,null))
            2 -> holder.cv_item.setCardBackgroundColor(EMApplication.context.getResources()
                .getColor(R.color.green_500,null))
        }

        holder.tv_name.text = mBean.name
        holder.tv_disp.text = mBean.disp
        holder.tv_index.text = mBean.taskID.toString()
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}