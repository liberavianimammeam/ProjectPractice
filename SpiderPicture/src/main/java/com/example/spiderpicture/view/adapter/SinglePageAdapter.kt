package com.example.spiderpicture.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spiderpicture.R
import com.example.spiderpicture.bean.ImageCoverBean
import com.example.spiderpicture.view.holder.SinglePageViewHolder

class SinglePageAdapter: RecyclerView.Adapter<SinglePageViewHolder>() {

    var data: ArrayList<ImageCoverBean>? = null
    private val TAG: String = "SpiderPicture_SinglePageAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinglePageViewHolder {
        return SinglePageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_single_page, parent, false))
    }

    override fun onBindViewHolder(holder: SinglePageViewHolder, position: Int) {
        data?.get(position)?.let {
            holder.bindView(it, position)
            Log.i(TAG, "onBindViewHolder: the postiion is $position  and the data url is ${it.coverImageUrl}")
        }

    }

    override fun getItemCount(): Int {
        if (data == null) return 0
        else return data?.size!!
    }
}