package com.example.schedulemaster.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.R
import com.example.schedulemaster.ui.holder.DateMonthRVHolder

class DateMonthRVAdapter(val lifecycleOwner: LifecycleOwner): RecyclerView.Adapter<DateMonthRVHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateMonthRVHolder {
        return DateMonthRVHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_date_rv, parent, false))
    }

    override fun onBindViewHolder(holderMonth: DateMonthRVHolder, position: Int) {
        holderMonth.bindView(lifecycleOwner = lifecycleOwner, position)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}