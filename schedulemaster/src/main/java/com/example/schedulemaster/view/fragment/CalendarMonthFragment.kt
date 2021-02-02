package com.example.schedulemaster.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.schedulemaster.R
import com.example.schedulemaster.view.adapter.MonthAdapter

class CalendarMonthFragment: Fragment() {

    var monthRecyclerView: RecyclerView? = null
    var planRecyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar_month, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        monthRecyclerView = view.findViewById<RecyclerView>(R.id.fcm_month_recyclerview)
        planRecyclerView = view.findViewById<RecyclerView>(R.id.fcm_plan_recyclerview)

        var layoutManager = GridLayoutManager(context, 7)
        monthRecyclerView!!.layoutManager = StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
        monthRecyclerView!!.adapter = MonthAdapter()

    }

}