package com.example.schedulemaster.ui.holder

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.R
import com.example.schedulemaster.bean.DateBean

class MonthViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindView(date: DateBean){
        itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#000000"))
        itemView.findViewById<TextView>(R.id.hm_text_test).text = date.date.toString()

        if (date.isThisMonth){
            itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#000000"))
//            itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#00000000"))
        }else{
            itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#FFB8B8B8"))
//            itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#00000000"))
        }

    }

}