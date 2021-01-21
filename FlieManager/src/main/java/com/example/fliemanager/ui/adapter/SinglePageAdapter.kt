package com.example.fliemanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.R
import com.example.fliemanager.bean.FileNameBean
import com.example.fliemanager.ui.viewholder.SinglePageViewholder
import java.util.zip.Inflater

class SinglePageAdapter: RecyclerView.Adapter<SinglePageViewholder>() {

    var data: ArrayList<FileNameBean> = ArrayList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinglePageViewholder {
        return SinglePageViewholder(LayoutInflater.from(parent.context).inflate(R.layout.holder_file_detail, parent, false))
    }

    override fun onBindViewHolder(holder: SinglePageViewholder, position: Int) {
        if (position < data.size){
            holder.bindView(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}