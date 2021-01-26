package com.example.fliemanager.ui.adapter

import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.fliemanager.bean.FileNameBean
import com.example.fliemanager.ui.fragment.SinglePictureFragment

class PicturePagesAdapter(var pathData: ArrayList<FileNameBean>, activity: FragmentActivity, var metrics: DisplayMetrics): FragmentStateAdapter(activity) {

    private val TAG: String = "FileManager_PicturePagesAdapter"

    override fun getItemCount(): Int {
        return pathData.size
    }

    override fun createFragment(position: Int): Fragment {
        return SinglePictureFragment(pathData[position], metrics)
    }

    override fun getItemId(position: Int): Long {
//        Log.i(TAG, "getItemId: clickposition is $clickPosition")

        return super.getItemId(position + 1)
    }



}