package com.example.schedulemaster.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.R
import com.example.schedulemaster.ui.holder.PlanViewHolder

class PlanAdapter: RecyclerView.Adapter<PlanViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        return PlanViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_plan, parent, false))
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 20
    }
}