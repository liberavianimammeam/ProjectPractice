package com.example.schedulemaster.model

import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import android.icu.util.Calendar
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.Global
import com.example.schedulemaster.R
import com.example.schedulemaster.bean.DateBean
import com.example.schedulemaster.ui.adapter.MonthAdapter
import kotlinx.coroutines.delay

class CalendarMonthViewModel: ViewModel() {

    private val TAG: String = "ScheduleMaster_CalendarMonthViewModel"
    var positionToday: Int = -1
    var animationPauseTime: Long = 10L
    var animationNum = 30

    suspend fun getCalendarDateList(year: Int, month: Int): ArrayList<DateBean>{
        val data: ArrayList<DateBean> = ArrayList()

        var firstDayInMonth: Calendar = Calendar.getInstance()
        firstDayInMonth.set(year, month, 1)
        var lastDayInMonth: Calendar = Calendar.getInstance()
        lastDayInMonth.set(year, month + 1, 1)
        lastDayInMonth.add(Calendar.DATE, -1)
        var lastDayInPastMonth: Calendar = Calendar.getInstance()
        lastDayInPastMonth.set(year, month, 1)
        lastDayInPastMonth.add(Calendar.DATE, -1)

        Log.i(TAG, "getCalendarDateList: calendar in year" +
                "\n firstDayInMonth year ${firstDayInMonth.get(Calendar.YEAR)} month ${firstDayInMonth.get(Calendar.MONTH)} date ${firstDayInMonth.get(Calendar.DATE)}" +
                "\n lastDayInMonth year ${lastDayInMonth.get(Calendar.YEAR)} month ${lastDayInMonth.get(Calendar.MONTH)} date ${lastDayInMonth.get(Calendar.DATE)}" +
                "\n lastDayInPastMonth year ${lastDayInPastMonth.get(Calendar.YEAR)} month ${lastDayInPastMonth.get(Calendar.MONTH)} date ${lastDayInPastMonth.get(Calendar.DATE)}")

        for(i in 0 until lastDayInPastMonth.get(Calendar.DAY_OF_WEEK)){
            data.add(DateBean(lastDayInPastMonth.get(Calendar.DAY_OF_MONTH) - i, false))
        }
        for (i in 1 .. lastDayInMonth.get(Calendar.DAY_OF_MONTH)){
            data.add(DateBean(i, true))
            if (i == Calendar.getInstance().get(Calendar.DATE)){
                positionToday = data.size
            }
        }
        var weekNum = data.size/ 7
        var lastDate = data.size
        for (i in 1.. (weekNum+1)*7 - lastDate){
            data.add(DateBean(i, false))
        }
        return data
    }

    suspend fun changeDateViewMode(context: Context, recyclerView: RecyclerView, layout: ConstraintLayout, adapter: MonthAdapter, imageView: ImageView){

        if (recyclerView.layoutManager?.javaClass != LinearLayoutManager::class.java){
            imageView.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
            imageView.isClickable = false
            changeFromMonthToWeek(context, recyclerView, layout, adapter, imageView)
        }else{
            imageView.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            imageView.isClickable = false
            changeFromWeekToMonth(context, recyclerView, layout, imageView)
        }

    }

    private suspend fun changeFromMonthToWeek(context: Context, recyclerView: RecyclerView, layout: ConstraintLayout, adapter: MonthAdapter, imageView: ImageView){
        for (i in animationNum downTo 1){
            //TODO 从每月计划到每日计划的转换
            if (Global.RadioTextViewInfo.radioTextViewWidth != -1 && Global.RadioTextViewInfo.radioTextViewHeight != -1 && Global.RadioTextViewInfo.marginBottom != -1 && Global.RadioTextViewInfo.marginTop != -1){

                Log.i(TAG, "changeFromMonthToWeek: currentTime is ${System.currentTimeMillis()}")
                
                var singleLineHeight: Float = (Global.RadioTextViewInfo.radioTextViewHeight + Global.RadioTextViewInfo.marginBottom + Global.RadioTextViewInfo.marginTop).toFloat()
                var percent: Float = i/animationNum.toFloat()
                var heightToSet: Float = singleLineHeight * ((Global.RadioTextViewInfo.lineNumber -1) * percent + 1)
                var mSet: ConstraintSet = ConstraintSet()
                mSet.clone(context, R.layout.fragment_calendar_month)
                mSet.constrainHeight(R.id.fcm_date_recyclerview, heightToSet.toInt())
                mSet.applyTo(layout)
            }
            delay(animationPauseTime)
        }

        var linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.requestFocus()
        adapter.choosenPosition.value?.let {
            recyclerView.scrollToPosition(it)
        }
        imageView.isClickable = true
    }

    private suspend fun changeFromWeekToMonth(context: Context, recyclerView: RecyclerView, layout: ConstraintLayout, imageView: ImageView){

        var GridLayoutManager = GridLayoutManager(context, 7)
        recyclerView.layoutManager = GridLayoutManager

        for (i in 1.. animationNum){

            //TODO 从每月计划到每日计划的转换
            if (Global.RadioTextViewInfo.radioTextViewWidth != -1 &&
                Global.RadioTextViewInfo.radioTextViewHeight != -1 &&
                Global.RadioTextViewInfo.marginBottom != -1 &&
                Global.RadioTextViewInfo.marginTop != -1){

                val singleLineHeight: Float = (Global.RadioTextViewInfo.radioTextViewHeight + Global.RadioTextViewInfo.marginBottom + Global.RadioTextViewInfo.marginTop).toFloat()
                val percent: Float = i/animationNum.toFloat()
                val heightToSet: Float = singleLineHeight * ((Global.RadioTextViewInfo.lineNumber -1) * percent + 1)
                val mSet: ConstraintSet = ConstraintSet()
                mSet.clone(context, R.layout.fragment_calendar_month)
                mSet.constrainHeight(R.id.fcm_date_recyclerview, heightToSet.toInt())
                mSet.applyTo(layout)
            }

            delay(animationPauseTime)
        }
        imageView.isClickable = true
    }


}