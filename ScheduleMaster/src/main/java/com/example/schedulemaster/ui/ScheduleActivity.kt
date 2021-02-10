package com.example.schedulemaster.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schedulemaster.R
import com.example.schedulemaster.ui.fragment.ScheduleMonthFragment

class ScheduleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

//        findViewById<ViewPager2>(R.id.as_viewpager).adapter = ScheduleAdapter(this)

        supportFragmentManager.beginTransaction().add(R.id.as_framlayout_schedule, ScheduleMonthFragment(), "test").commit()

    }
}