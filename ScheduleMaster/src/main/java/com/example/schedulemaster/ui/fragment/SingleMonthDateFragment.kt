package com.example.schedulemaster.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.schedulemaster.R

class SingleMonthDateFragment(val position: Int): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_single_month_date, container, false)
        view.findViewById<TextView>(R.id.fsmd_test_text).text = position.toString()
        return view
    }
}