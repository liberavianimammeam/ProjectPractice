package com.example.schedulemaster.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.schedulemaster.Global
import com.example.schedulemaster.ui.fragment.SingleMonthDateFragment

class SingleMonthDateAdapter(fragment: Fragment): androidx.viewpager2.adapter.FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return Global.scheduleTotalMonth
    }

    override fun createFragment(position: Int): Fragment {
        return SingleMonthDateFragment(position)
    }
}