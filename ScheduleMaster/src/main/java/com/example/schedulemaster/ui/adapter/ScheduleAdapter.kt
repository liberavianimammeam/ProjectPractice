package com.example.schedulemaster.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.schedulemaster.ui.fragment.CalendarMonthFragment
import com.example.schedulemaster.ui.fragment.CalendarWeekFragment

class ScheduleAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    var calendarMonthFragment: CalendarMonthFragment? = null
    var calendarWeekFragment: CalendarWeekFragment? = null

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 ->{
                if (calendarMonthFragment == null){
                    calendarMonthFragment = CalendarMonthFragment()
                }
                return calendarMonthFragment!!
            }
            1 ->{
                if (calendarWeekFragment == null){
                    calendarWeekFragment = CalendarWeekFragment()
                }
                return calendarWeekFragment!!
            }
            else -> return Fragment()   //TODO 写一个错误的fragment？
        }
    }

}