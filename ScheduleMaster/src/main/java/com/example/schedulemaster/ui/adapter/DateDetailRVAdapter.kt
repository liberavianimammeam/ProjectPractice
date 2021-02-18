package com.example.schedulemaster.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.Global
import com.example.schedulemaster.R
import com.example.schedulemaster.bean.DateBean
import com.example.schedulemaster.ui.holder.DateDetailRVHolder

class DateDetailRVAdapter: RecyclerView.Adapter<DateDetailRVHolder>() {

    var data = ArrayList<DateBean>()
        set(value){
            field = value
            Global.RadioTextViewInfo.lineNumber = data.size / 7
        }
    var chosenDateLiveData: MutableLiveData<Int> = MutableLiveData()
    var chosenPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateDetailRVHolder {
        return DateDetailRVHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_month, parent, false))
    }

    override fun onBindViewHolder(holder: DateDetailRVHolder, position: Int) {
        if (position < data.size){
            holder.bindView(data[position], chosenDateLiveData)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}