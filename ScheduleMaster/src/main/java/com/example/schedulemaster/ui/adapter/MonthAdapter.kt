package com.example.schedulemaster.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.Global
import com.example.schedulemaster.R
import com.example.schedulemaster.bean.DateBean
import com.example.schedulemaster.ui.holder.MonthViewHolder

class MonthAdapter: RecyclerView.Adapter<MonthViewHolder>() {

    var data = ArrayList<DateBean>()
        set(value){
            field = value
            Global.RadioTextViewInfo.lineNumber = data.size / 7
        }
    var choosenPosition: MutableLiveData<Int> = MutableLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        return MonthViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_month, parent, false))
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        if (position < data.size){
            if (choosenPosition.value != null && position == choosenPosition.value){
                holder.bindView(data[position], true, choosenPosition, position)
            }else{
                holder.bindView(data[position], false, choosenPosition, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}