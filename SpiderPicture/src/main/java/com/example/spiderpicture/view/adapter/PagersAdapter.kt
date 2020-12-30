package com.example.spiderpicture.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.spiderpicture.Global
import com.example.spiderpicture.view.fragment.FragmentSinglePage

class PagersAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return Global.urlSecondLevel.size
    }

    override fun createFragment(position: Int): Fragment {
        return FragmentSinglePage(position)
    }

}