package com.example.spiderpicture.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spiderpicture.R
import com.example.spiderpicture.bean.ImageDataBean
import com.example.spiderpicture.util.RequestUtil
import com.example.spiderpicture.util.ResolveUtil
import com.example.spiderpicture.view.holder.PictureViewHolder

class PictureAdapter: RecyclerView.Adapter<PictureViewHolder>() {

    var data: ArrayList<ImageDataBean> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_imageloader, parent, false))
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        if (data.size > position){
            data[position].bitmapCover?.let { holder.bindView(it) }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}