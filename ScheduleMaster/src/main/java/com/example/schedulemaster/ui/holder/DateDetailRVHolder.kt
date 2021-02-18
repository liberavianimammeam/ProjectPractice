package com.example.schedulemaster.ui.holder

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.R
import com.example.schedulemaster.bean.DateBean
import com.example.schedulemaster.bean.RadioDataBean
import com.example.schedulemaster.manager.DataManager
import com.example.schedulemaster.view.RadioDateTextView

class DateDetailRVHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val TAG: String = "ScheduleMaster_MonthViewHolder"

    fun bindView(data: DateBean, chosenDateLiveData: MutableLiveData<Int>){
        itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#000000"))
        itemView.findViewById<TextView>(R.id.hm_text_test).text = data.date.toString()

        if (data.isThisMonth){
            itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#000000"))
            itemView.findViewById<RadioDateTextView>(R.id.hm_text_test).changeRoundColor(Color.parseColor("#8500ffff"))
//            itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#00000000"))
        }else{
            itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#FFB8B8B8"))
            itemView.findViewById<RadioDateTextView>(R.id.hm_text_test).changeRoundColor(Color.parseColor("#00000000"))
//            itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#00000000"))
        }

        if (data.isThisMonth && data.date == DataManager.chosenDate){
            itemView.findViewById<RadioDateTextView>(R.id.hm_text_test).changeChosenState(true)
        }


        itemView.findViewById<TextView>(R.id.hm_text_test).setOnClickListener {

            if (data.isThisMonth) {
                chosenDateLiveData.postValue(data.date)
            }
        }

    }

}