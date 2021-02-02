package com.example.schedulemaster.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.schedulemaster.R
import com.example.schedulemaster.view.adapter.ScheduleAdapter

class ScheduleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        findViewById<ViewPager2>(R.id.as_viewpager).adapter = ScheduleAdapter(this)
    }
}