package com.example.fliemanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.Global
import com.example.fliemanager.R
import com.example.fliemanager.bean.FileNameBean
import com.example.fliemanager.ui.viewholder.SinglePageViewHolder

class SinglePageAdapter: RecyclerView.Adapter<SinglePageViewHolder>() {

    var choosePath: MutableLiveData<FileNameBean.FileNamePositionBean> = MutableLiveData()

    var data: ArrayList<FileNameBean> = ArrayList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinglePageViewHolder {
        return SinglePageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_file_detail, parent, false))
    }

    override fun onBindViewHolder(holder: SinglePageViewHolder, position: Int) {
        if (position < data.size){
            holder.bindView(data[position], choosePath, position)
        }
        if (Global.positionReturn == position){
            holder.changeBackground(true)
        }else{
            holder.changeBackground(false)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}