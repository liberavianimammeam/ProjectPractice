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

    /**
     * this project created at 2021
     * so i will only give 20 year from 2020.01.01 to 2039.12.31 to write the schedule
     */
    val calendarStart = Calendar.Builder()
        .set(Calendar.YEAR, 2020)
        .set(Calendar.MONTH, 0)
        .set(Calendar.DATE, 1)
        .build()
    val scheduleTotalMonth = 240
}