package com.example.schedulemaster.ui.fragment

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.schedulemaster.R
import com.example.schedulemaster.model.CalendarMonthViewModel
import com.example.schedulemaster.ui.adapter.MonthAdapter
import com.example.schedulemaster.ui.adapter.PlanAdapter
import com.example.schedulemaster.ui.adapter.SingleMonthDateAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ScheduleMonthFragment: Fragment() {

    private val TAG: String = "ScheduleMaster_ScheduleMonthFragment"

     lateinit var planRecyclerView: RecyclerView
     lateinit var dateViewpager: ViewPager2
     lateinit var imageChangeState: ImageView

    val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(CalendarMonthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule_month, container, false)
        planRecyclerView = view.findViewById<RecyclerView>(R.id.fsm_plan_recyclerview)
        imageChangeState = view.findViewById<ImageView>(R.id.fsm_change_surface)
        dateViewpager = view.findViewById(R.id.fsm_date_viewpager)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        planRecyclerView.adapter = PlanAdapter()
        planRecyclerView.layoutManager = LinearLayoutManager(context)

        dateViewpager.adapter = SingleMonthDateAdapter(this)

        imageChangeState.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                context?.let {

                }
            }
        }



    }

}