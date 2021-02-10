package com.example.schedulemaster.manager

import android.util.Log
import com.example.schedulemaster.bean.DateBean
import com.example.schedulemaster.ui.fragment.CalendarMonthFragment
import java.util.*
import kotlin.collections.ArrayList

object DataManager {

    private val TAG: String = "ScheduleMaster_DataManager"
    var chosenDate = -1

    suspend fun getDateList(year: Int, month: Int): ArrayList<DateBean>{
        var data: ArrayList<DateBean> = ArrayList()
        if (chosenDate == -1) chosenDate = Calendar.getInstance().get(Calendar.DATE)

        var calendarThisMonthFirstDay: Calendar = Calendar.Builder()
            .set(Calendar.YEAR, year)
            .set(Calendar.MONTH, month)
            .set(Calendar.DATE, 1)
            .build()
        var calendarLastMonthLastDay: Calendar = Calendar.Builder()
            .set(Calendar.YEAR, year)
            .set(Calendar.MONTH, month)
            .set(Calendar.DATE, 1)
            .build()
        calendarLastMonthLastDay.add(Calendar.DATE, -1)
        var calendarThisMonthLastDay = Calendar.Builder()
            .set(Calendar.YEAR, year)
            .set(Calendar.MONTH, month)
            .set(Calendar.DATE, 1)
            .build()
        calendarThisMonthLastDay.add(Calendar.MONTH, 1)
        calendarThisMonthLastDay.add(Calendar.DATE, -1)
        Log.i(TAG, """
            getDateList: the calendarIn is $calendarThisMonthFirstDay
            | and the calendarLastMontLastDay is $calendarLastMonthLastDay
            | and the calendarThisMonthLastDay is $calendarThisMonthLastDay
        """.trimIndent())

        for (i in calendarThisMonthFirstDay.get(Calendar.DAY_OF_WEEK) downTo 1){
            data.add(DateBean( calendarLastMonthLastDay.get(Calendar.DATE)- i, false))
        }
        for (i in 1.. calendarThisMonthLastDay.get(Calendar.DATE)){
            data.add(DateBean(i, true))
        }
        if (data.size %7 != 0){
            for (i in 1 ..(7*(data.size/7 + 1) - data.size)){
                data.add(DateBean(i, false))
            }
        }


        Log.i(TAG, "getDateList: the data list is $data")
        return data
    }


}