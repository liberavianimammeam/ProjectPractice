package com.example.spiderpicture.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.spiderpicture.R
import com.example.spiderpicture.bean.ImageDataBean
import com.example.spiderpicture.view.holder.RootViewHolder

class RootAdapter: RecyclerView.Adapter<RootViewHolder>() {

    var clickEventLiveData: MutableLiveData<Int> = MutableLiveData()

    var data: ArrayList<ImageDataBean> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootViewHolder {
        return RootViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_imageloader, parent, false))
    }

    override fun onBindViewHolder(holder: RootViewHolder, position: Int) {
        if (data.size > position){
            data[position].bitmapCover?.let { holder.bindView(it) }
            holder.itemView.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    clickEventLiveData.postValue(position)
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}