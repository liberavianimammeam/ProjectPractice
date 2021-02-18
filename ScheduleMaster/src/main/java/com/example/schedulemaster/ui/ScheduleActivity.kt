package com.example.schedulemaster.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.schedulemaster.Global
import com.example.schedulemaster.R
import com.example.schedulemaster.ui.fragment.ScheduleMonthFragment

class ScheduleActivity: AppCompatActivity() {

    private val TAG: String = "ScheduleMaster_ScheduleActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

//        findViewById<ViewPager2>(R.id.as_viewpager).adapter = ScheduleAdapter(this)

        supportFragmentManager.beginTransaction().add(R.id.as_framlayout_schedule, ScheduleMonthFragment(), "test").commit()
        Log.i(TAG, "onCreate: the window height is ${windowManager.defaultDisplay.height} and the width is ${windowManager.defaultDisplay.width}")
        if (Build.VERSION.SDK_INT >= 30){
            Global.windowHeight = windowManager.currentWindowMetrics.bounds.height()
            Global.windowWidth = windowManager.currentWindowMetrics.bounds.width()
        }else{
            Global.windowHeight = windowManager.defaultDisplay.height
            Global.windowWidth = windowManager.defaultDisplay.width
        }
    }
}