package com.example.fliemanager.ui.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.R
import com.example.fliemanager.bean.FileNameBean

class SinglePageViewholder(var itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindView(data: FileNameBean){
        itemView.findViewById<TextView>(R.id.hfd_filename).text = data.name
    }

}