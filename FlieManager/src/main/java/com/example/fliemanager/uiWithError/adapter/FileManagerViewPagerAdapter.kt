package com.example.fliemanager.uiWithError.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fliemanager.uiWithError.fragment.FileManagerFragment

class FileManagerViewPagerAdapter(fragmentActivity: FragmentActivity,var list: List<String>): FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return FileManagerFragment(list, position)
    }


}