package com.example.schedulemaster.ui.holder

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.R
import com.example.schedulemaster.bean.DateBean
import com.example.schedulemaster.view.RadioTextView

class MonthViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val TAG: String = "ScheduleMaster_MonthViewHolder"

    fun bindView(date: DateBean, beenChosen: Boolean, chosenPosition: MutableLiveData<Int>, messagePost: Int){
        itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#000000"))
        itemView.findViewById<TextView>(R.id.hm_text_test).text = date.date.toString()
        itemView.findViewById<RadioTextView>(R.id.hm_text_test).changeChosenState(beenChosen)

        Log.i(TAG, "bindView: have been chosen? $beenChosen")

        if (date.isThisMonth){
            itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#000000"))
//            itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#00000000"))
        }else{
            itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#FFB8B8B8"))
//            itemView.findViewById<TextView>(R.id.hm_text_test).setTextColor(Color.parseColor("#00000000"))
        }

        itemView.findViewById<TextView>(R.id.hm_text_test).setOnClickListener {

            if (date.isThisMonth) {
                chosenPosition.postValue(messagePost)
            }
        }

    }

}