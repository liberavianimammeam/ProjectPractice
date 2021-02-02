package com.example.schedulemaster.view.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.R

class MonthViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindView(position: Int){

        itemView.findViewById<TextView>(R.id.hm_text_test).text = position.toString()

    }

}