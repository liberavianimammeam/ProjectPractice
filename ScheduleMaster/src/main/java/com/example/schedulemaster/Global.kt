package com.example.schedulemaster

import androidx.lifecycle.MutableLiveData

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

}