package com.example.spiderpicture.view.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.spiderpicture.Global
import com.example.spiderpicture.R
import com.example.spiderpicture.view.adapter.PagersAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FragmentPages: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pages, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ViewPager2>(R.id.sp_fragment_pages_pages).adapter = PagersAdapter(this)
        view.findViewById<TabLayout>(R.id.sp_fragment_pages_table).isTabIndicatorFullWidth = true
        TabLayoutMediator(view.findViewById(R.id.sp_fragment_pages_table), view.findViewById(R.id.sp_fragment_pages_pages),
            object :TabLayoutMediator.TabConfigurationStrategy{
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = Global.urlSecondLevel[position]
                }
            }).attach()
//        view.findViewById<TabLayout>(R.id.sp_fragment_pages_table).setupWithViewPager(view.findViewById(R.id.sp_fragment_pages_pages))


    }
}