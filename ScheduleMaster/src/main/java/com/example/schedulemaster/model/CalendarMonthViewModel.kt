package com.example.schedulemaster.model

import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.schedulemaster.bean.DateBean

class CalendarMonthViewModel: ViewModel() {

    private val TAG: String = "ScheduleMaster_CalendarMonthViewModel"
    
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
        }
        var weekNum = data.size/ 7
        var lastDate = data.size
        for (i in 1.. (weekNum+1)*7 - lastDate){
            data.add(DateBean(i, false))
        }
        return data
    }



}