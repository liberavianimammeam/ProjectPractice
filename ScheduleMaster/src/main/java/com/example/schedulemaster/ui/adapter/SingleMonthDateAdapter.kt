package com.example.schedulemaster.ui.adapter

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.example.schedulemaster.Global
import com.example.schedulemaster.bean.ModeDataBean
import com.example.schedulemaster.ui.fragment.SingleMonthDateFragment

class SingleMonthDateAdapter(fragment: Fragment, val lineNumberAdapter: MutableLiveData<Int>, val modeLiveData: MutableLiveData<ModeDataBean>): androidx.viewpager2.adapter.FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return Global.scheduleTotalMonth
    }

    override fun createFragment(position: Int): Fragment {
        return SingleMonthDateFragment(position, lineNumberAdapter, modeLiveData)
    }
}