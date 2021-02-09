package com.example.schedulemaster.manager

import android.content.Context
import android.icu.util.Calendar
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.Global
import com.example.schedulemaster.R
import com.example.schedulemaster.bean.RadioDataBean
import com.example.schedulemaster.ui.adapter.DateDetailRVAdapter
import kotlinx.coroutines.delay

object DateManager{

    private val TAG: String = "ScheduleMaster_CalendarMonthViewModel"
    var animationPauseTime: Long = 10L
    var animationNum = 30
    var datePosition = -1
    var dateLastMonth = -1

    suspend fun getCalendarDateList(year: Int, month: Int): ArrayList<RadioDataBean>{
        val data: ArrayList<RadioDataBean> = ArrayList()

        var firstDayInMonth: Calendar = Calendar.getInstance()
        firstDayInMonth.set(year, month, 1)
        var lastDayInMonth: Calendar = Calendar.getInstance()
        lastDayInMonth.set(year, month + 1, 1)
        lastDayInMonth.add(Calendar.DATE, -1)
        var lastDayInPastMonth: Calendar = Calendar.getInstance()
        lastDayInPastMonth.set(year, month, 1)
        lastDayInPastMonth.add(Calendar.DATE, -1)

        dateLastMonth = lastDayInPastMonth.get(Calendar.DAY_OF_WEEK)

        for(i in 0 until lastDayInPastMonth.get(Calendar.DAY_OF_WEEK)){
            data.add(RadioDataBean(lastDayInPastMonth.get(Calendar.DAY_OF_MONTH) - i, false))
        }
        for (i in 1 .. lastDayInMonth.get(Calendar.DAY_OF_MONTH)){
            if (datePosition == -1){
                datePosition = Calendar.getInstance().get(Calendar.DATE)
            }
            data.add(RadioDataBean(i, true))
        }
        var weekNum = data.size/ 7
        var lastDate = data.size
        for (i in 1.. (weekNum+1)*7 - lastDate){
            data.add(RadioDataBean(i, false))
        }
        return data
    }

    suspend fun getCalendarDateListWithPosition(year: Int, month: Int, position: Int){

    }

    suspend fun changeDateViewMode(context: Context, recyclerView: RecyclerView, layout: ConstraintLayout, adapter: DateDetailRVAdapter, imageView: ImageView){

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

    private suspend fun changeFromMonthToWeek(context: Context, recyclerView: RecyclerView, layout: ConstraintLayout, adapter: DateDetailRVAdapter, imageView: ImageView){
        for (i in animationNum downTo 1){
            //TODO 从每月计划到每日计划的转换
            if (Global.RadioTextViewInfo.radioTextViewWidth != -1 && Global.RadioTextViewInfo.radioTextViewHeight != -1 && Global.RadioTextViewInfo.marginBottom != -1 && Global.RadioTextViewInfo.marginTop != -1){

                Log.i(TAG, "changeFromMonthToWeek: currentTime is ${System.currentTimeMillis()}")

                //TODO 修改
                var singleLineHeight: Float = (Global.RadioTextViewInfo.radioTextViewHeight + Global.RadioTextViewInfo.marginBottom + Global.RadioTextViewInfo.marginTop).toFloat()
                var percent: Float = i/ animationNum.toFloat()
                var heightToSet: Float = singleLineHeight * ((Global.RadioTextViewInfo.lineNumber -1) * percent + 1)
                var mSet: ConstraintSet = ConstraintSet()
                mSet.clone(context, R.layout.fragment_schedule_month)
//                mSet.constrainHeight(R.id.fcm_date_recyclerview, heightToSet.toInt())
//                mSet.applyTo(layout)
            }
            delay(animationPauseTime)
        }

        var linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.requestFocus()
        if (dateLastMonth + datePosition - 1 > 0 ){
            recyclerView.scrollToPosition(dateLastMonth + datePosition - 1)
        }
        imageView.isClickable = true
    }

    private suspend fun changeFromWeekToMonth(context: Context, recyclerView: RecyclerView, layout: ConstraintLayout, imageView: ImageView){

        var GridLayoutManager = GridLayoutManager(context, 7)
        recyclerView.layoutManager = GridLayoutManager

        for (i in 1.. animationNum){

            //TODO 从每月计划到每日计划的转换
                TODO("还没修改完")
            if (Global.RadioTextViewInfo.radioTextViewWidth != -1 &&
                Global.RadioTextViewInfo.radioTextViewHeight != -1 &&
                Global.RadioTextViewInfo.marginBottom != -1 &&
                Global.RadioTextViewInfo.marginTop != -1){

                val singleLineHeight: Float = (Global.RadioTextViewInfo.radioTextViewHeight + Global.RadioTextViewInfo.marginBottom + Global.RadioTextViewInfo.marginTop).toFloat()
                val percent: Float = i/ animationNum.toFloat()
                val heightToSet: Float = singleLineHeight * ((Global.RadioTextViewInfo.lineNumber -1) * percent + 1)
                val mSet: ConstraintSet = ConstraintSet()
//                mSet.clone(context, R.layout.fragment_schedule_month)
//                mSet.constrainHeight(R.id.fcm_date_recyclerview, heightToSet.toInt())
//                mSet.applyTo(layout)
            }

            delay(animationPauseTime)
        }
        imageView.isClickable = true
    }


}