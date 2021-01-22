package com.example.fliemanager.ui.adapter

import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fliemanager.bean.FileNameBean
import com.example.fliemanager.ui.fragment.SinglePageFragment
import com.example.fliemanager.ui.fragment.SinglePictureFragment

class PicturePagesAdapter(var pathData: ArrayList<FileNameBean>, activity: FragmentActivity, var metrics: DisplayMetrics): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return pathData.size
    }

    override fun createFragment(position: Int): Fragment {
        return SinglePictureFragment(pathData[position], metrics)
    }
}