package com.example.schedulemaster.ui.holder

import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.R
import com.example.schedulemaster.manager.DateManager
import com.example.schedulemaster.ui.adapter.DateDetailRVAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class DateMonthRVHolder( itemView: View): RecyclerView.ViewHolder(itemView) {

    private val TAG: String = "ScheduleMaster_DateMonthRVHolder"
    lateinit var dateDetailRecyclerView: RecyclerView
    val adapter = DateDetailRVAdapter()

    fun bindView(lifecycleOwner: LifecycleOwner, position: Int){

        itemView.findViewById<TextView>(R.id.hdr_test_text).text = position.toString()

        dateDetailRecyclerView = itemView.findViewById(R.id.hdr_recyclerview)
        dateDetailRecyclerView.layoutManager = GridLayoutManager(itemView.context, 7)
        dateDetailRecyclerView.adapter = adapter

        GlobalScope.launch(Dispatchers.Main) {
            adapter.data = DateManager.getCalendarDateList(
                Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(
                    Calendar.MONTH))
            adapter.chosenPosition = DateManager.datePosition + DateManager.dateLastMonth - 1
            adapter.notifyDataSetChanged()
        }

        adapter.chosenDateLiveData.observe(lifecycleOwner, androidx.lifecycle.Observer {
            DateManager.datePosition = it
            adapter.chosenPosition = DateManager.datePosition+ DateManager.dateLastMonth- 1
            adapter.notifyDataSetChanged()
        })

        dateDetailRecyclerView.setOnScrollChangeListener(object : View.OnScrollChangeListener {
            override fun onScrollChange(
                v: View?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                Log.i(TAG, "onScrollChange: the scrollX is $scrollX and scrollY is $scrollY and old scrollX is $oldScrollX and old scrollY is $scrollY")
            }

        })

    }

}