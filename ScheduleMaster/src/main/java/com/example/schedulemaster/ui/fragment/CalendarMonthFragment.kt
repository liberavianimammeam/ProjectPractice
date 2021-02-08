package com.example.schedulemaster.ui.fragment

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.Global
import com.example.schedulemaster.R
import com.example.schedulemaster.model.CalendarMonthViewModel
import com.example.schedulemaster.ui.adapter.MonthAdapter
import com.example.schedulemaster.ui.adapter.PlanAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CalendarMonthFragment: Fragment() {

    private val TAG: String = "ScheduleMaster_CalendarMonthFragment"

    var monthRecyclerView: RecyclerView? = null
    var planRecyclerView: RecyclerView? = null

    var monthAdapter: MonthAdapter = MonthAdapter()
    val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(CalendarMonthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar_month, container, false)
        monthRecyclerView = view.findViewById<RecyclerView>(R.id.fcm_date_recyclerview)
        planRecyclerView = view.findViewById<RecyclerView>(R.id.fcm_plan_recyclerview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        planRecyclerView!!.adapter = PlanAdapter()
        planRecyclerView!!.layoutManager = LinearLayoutManager(context)

//        var layoutManager = GridLayoutManager(context, 7)
        monthRecyclerView!!.layoutManager = GridLayoutManager(context, 7)
//        monthRecyclerView!!.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        monthRecyclerView!!.adapter = monthAdapter

        GlobalScope.launch(Dispatchers.Main) {
            monthAdapter.data = viewModel.getCalendarDateList(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH))
            monthAdapter.choosenPosition.postValue(viewModel.positionToday)
            monthAdapter.notifyDataSetChanged()
        }

        monthAdapter.choosenPosition.observe(this, Observer {
            Log.i(TAG, "onViewCreated: it is $it")
            monthAdapter.notifyDataSetChanged()
        })

        var imageChangeState = view.findViewById<ImageView>(R.id.fcm_change_surface)

        imageChangeState.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                context?.let {
                    viewModel.changeDateViewMode(it, monthRecyclerView!!, view.findViewById<ConstraintLayout>(R.id.fcm_layout), monthAdapter, imageChangeState)
                }
            }
        }



    }

}