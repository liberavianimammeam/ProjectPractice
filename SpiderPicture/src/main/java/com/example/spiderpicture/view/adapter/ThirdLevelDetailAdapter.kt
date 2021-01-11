package com.example.spiderpicture.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spiderpicture.R
import com.example.spiderpicture.bean.ImageDataBean
import com.example.spiderpicture.view.holder.ThirdLevelDetailViewHolder

class ThirdLevelDetailAdapter: RecyclerView.Adapter<ThirdLevelDetailViewHolder>() {

    var data: ArrayList<ImageDataBean> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdLevelDetailViewHolder {
        return ThirdLevelDetailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_third_level_detail, parent, false))
    }

    override fun onBindViewHolder(holder: ThirdLevelDetailViewHolder, position: Int) {
        if (position < data.size){
            holder.bindView(data.get(position))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}