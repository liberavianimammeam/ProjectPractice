package com.example.fliemanager.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.fliemanager.FileManagerFragment

class FileManagerViewPagerAdapter(fragmentActivity: FragmentActivity,list: List<String>) :
    androidx.viewpager2.adapter.FragmentStateAdapter(fragmentActivity) {

    lateinit var list: List<String>

    companion object{



    }

    init {
        this.list = list
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return FileManagerFragment(list, position)
    }


}