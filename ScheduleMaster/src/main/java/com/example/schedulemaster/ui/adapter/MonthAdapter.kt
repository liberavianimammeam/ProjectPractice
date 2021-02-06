package com.example.schedulemaster.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.R
import com.example.schedulemaster.bean.DateBean
import com.example.schedulemaster.ui.holder.MonthViewHolder

class MonthAdapter: RecyclerView.Adapter<MonthViewHolder>() {

    var data = ArrayList<DateBean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        return MonthViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_month, parent, false))
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        if (position < data.size){
            holder.bindView(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}