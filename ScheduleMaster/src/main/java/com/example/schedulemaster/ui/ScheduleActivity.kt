package com.example.schedulemaster.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schedulemaster.R
import com.example.schedulemaster.ui.fragment.ScheduleFragment

class ScheduleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        supportFragmentManager.beginTransaction()
            .replace(R.id.as_fragment, ScheduleFragment())
            .addToBackStack("test")
            .commit()

//        findViewById<ViewPager2>(R.id.as_viewpager).adapter = ScheduleAdapter(this)
    }
}