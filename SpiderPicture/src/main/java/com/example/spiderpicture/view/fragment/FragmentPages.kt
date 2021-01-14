package com.example.spiderpicture.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.spiderpicture.Global
import com.example.spiderpicture.R
import com.example.spiderpicture.view.adapter.PagersAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FragmentPages: Fragment() {
    private val TAG: String = "SpiderPicture_FragmentPages"
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
        view.findViewById<ViewPager2>(R.id.sp_fragment_pages_pages).registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    Log.i(
                        TAG,
                        "onPageScrolled: cause the page scrolled event and the position: $position   positionOffSet: $positionOffset   positionOffsetPixels: $positionOffsetPixels"
                    )
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.i(
                        TAG,
                        "onPageSelected: case onPageSelected event and the position: $position"
                    )
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    Log.i(TAG, "onPageScrollStateChanged: cause the scrollStateChang event and the state: $state")
                    if (state == ViewPager2.SCROLL_STATE_IDLE){
                    }else{
                    }
                }
            })
    }



}