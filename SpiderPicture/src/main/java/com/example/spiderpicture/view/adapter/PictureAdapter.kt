package com.example.spiderpicture.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spiderpicture.R
import com.example.spiderpicture.view.holder.PictureViewHolder

class PictureAdapter: RecyclerView.Adapter<PictureViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_imageloader, parent, false))
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }

}