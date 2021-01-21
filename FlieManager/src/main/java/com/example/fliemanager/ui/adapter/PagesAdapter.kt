package com.example.fliemanager.ui.adapter

import android.provider.Settings
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fliemanager.Global
import com.example.fliemanager.ui.fragment.SinglePageFragment

class PagesAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return Global.pages.size
    }

    override fun createFragment(position: Int): Fragment {
        return SinglePageFragment(position)
    }
}