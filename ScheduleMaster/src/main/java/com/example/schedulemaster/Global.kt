package com.example.schedulemaster

import androidx.lifecycle.MutableLiveData
import java.util.*

object Global {

    var dateRecyclerViewChanging: MutableLiveData<Boolean> = MutableLiveData()

    object RadioTextViewInfo{
        var state: String = ChangeState.STATE_NORMAL
        var radioTextViewHeight: Int = -1
        var radioTextViewWidth: Int = -1
        var marginTop: Int = -1
        var marginBottom: Int = -1
        var lineNumber: Int = -1
    }

    object ChangeState{
        val STATE_CHANGING = "state_changing"
        val STATE_NORMAL = "state_normal"
    }

    object DateViewMode{
        val MODE_SINGLE_LINE = "mode_single_line"
        val MODE_LINES = "mode_lines"
    }

    object StartTime{
        val startYear = 2020
        val startMonth = 0
        val startDate = 1
    }

    /**
     * this project created at 2021
     * so i will only give 20 year from 2020.01.01 to 2039.12.31 to write the schedule
     */
    val calendarStart = Calendar.Builder()
        .set(Calendar.YEAR, StartTime.startYear)
        .set(Calendar.MONTH, StartTime.startMonth)
        .set(Calendar.DATE, StartTime.startDate)
        .build()
    val scheduleTotalMonth = 240

    var windowHeight: Int = 0
    var windowWidth: Int = 0
    var singleLineHeight: Int = 0

}