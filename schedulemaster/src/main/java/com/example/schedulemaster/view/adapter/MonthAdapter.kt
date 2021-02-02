package com.example.schedulemaster.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.R
import com.example.schedulemaster.view.holder.MonthViewHolder

class MonthAdapter: RecyclerView.Adapter<MonthViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        return MonthViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_month, parent, false))
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int {
        return 42
    }
}