package com.example.schedulemaster.ui.fragment

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.schedulemaster.Global
import com.example.schedulemaster.R
import com.example.schedulemaster.bean.ModeDataBean
import com.example.schedulemaster.manager.DataManager
import com.example.schedulemaster.ui.adapter.PlanRVAdapter
import com.example.schedulemaster.ui.adapter.SingleMonthDateAdapter
import kotlinx.coroutines.*

class ScheduleMonthFragment: Fragment() {

    private val TAG: String = "ScheduleMaster_ScheduleMonthFragment"

    lateinit var planRecyclerView: RecyclerView
    lateinit var dateViewpager: ViewPager2
    lateinit var imageChangeState: ImageView

    private val lineNumberLiveData: MutableLiveData<Int> = MutableLiveData()
    private var viewPagerHeightJob: Job? = null
    private val modeLiveData: MutableLiveData<ModeDataBean> = MutableLiveData()

    private val animationNum: Int = 25
    private val animationFrameTime: Long = 20

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

        planRecyclerView.adapter = PlanRVAdapter()
        planRecyclerView.layoutManager = LinearLayoutManager(context)

        dateViewpager.adapter = SingleMonthDateAdapter(this, lineNumberLiveData, modeLiveData)
        dateViewpager.currentItem = (Calendar.getInstance().get(Calendar.YEAR) - Global.StartTime.startYear) * 12 + Calendar.getInstance().get(Calendar.MONTH) - Global.StartTime.startMonth
        view.findViewById<TextView>(R.id.fsm_date).text = "${Global.StartTime.startYear + dateViewpager.currentItem/12} 年 ${Global.StartTime.startMonth + dateViewpager.currentItem % 12 + 1} 月"
        dateViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                view.findViewById<TextView>(R.id.fsm_date).text = "${Global.StartTime.startYear + position/12} 年 ${Global.StartTime.startMonth + position % 12 + 1} 月"
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

        lineNumberLiveData.observe(this, Observer {
            Log.i(TAG, "onViewCreated: case the line number is $it and the currentItem is ${dateViewpager.currentItem}")
            var heightNow = dateViewpager.height
            var heightAfter = it * Global.singleLineHeight
            if (heightAfter != 0 && modeLiveData.value?.mode != Global.DateViewMode.MODE_SINGLE_LINE) {
                viewPagerHeightJob?.cancel()
                viewPagerHeightJob = GlobalScope.launch(Dispatchers.Main) {
                    var heightValue = heightAfter - heightNow
                    val constraintSet: ConstraintSet = ConstraintSet()
                    constraintSet.clone(view.findViewById<ConstraintLayout>(R.id.fsm_layout))
                    imageChangeState.isClickable = false
                    for (i in 0..animationNum) {
                        constraintSet.constrainHeight(
                            R.id.fsm_date_viewpager,
                            heightNow + heightValue / animationNum * i
                        )
                        constraintSet.applyTo(view.findViewById<ConstraintLayout>(R.id.fsm_layout))
                        delay(animationFrameTime)
                    }
                    constraintSet.constrainHeight(
                        R.id.fsm_date_viewpager,
                        it * Global.singleLineHeight
                    )
                    constraintSet.applyTo(view.findViewById<ConstraintLayout>(R.id.fsm_layout))
//                    Log.i(TAG, "onViewCreated: the viewpager height now is ${dateViewpager.height} and the singleLineHeight is ${Global.singleLineHeight} and the height set is ${it * Global.singleLineHeight}")
                    imageChangeState.isClickable = true
                }
            }
        })

        imageChangeState.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                context?.let {
                    if (Global.singleLineHeight != 0){
                        val heightNow = dateViewpager.height
                        if (heightNow != Global.singleLineHeight){
                            imageChangeState.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                            imageChangeState.isClickable = false
                            GlobalScope.launch(Dispatchers.Main) {
                                val constraintSet = ConstraintSet()
                                val heightValue = heightNow - Global.singleLineHeight
                                constraintSet.clone(view.findViewById<ConstraintLayout>(R.id.fsm_layout))
                                for (i in animationNum downTo 0){
                                    constraintSet.constrainHeight(R.id.fsm_date_viewpager, heightValue * i / animationNum + Global.singleLineHeight)
                                    constraintSet.applyTo(view.findViewById(R.id.fsm_layout))
                                    delay(animationFrameTime)
                                }
                                imageChangeState.isClickable = true
                            }
                            modeLiveData.postValue(ModeDataBean(Global.DateViewMode.MODE_SINGLE_LINE, dateViewpager.currentItem) )
                        }else{
//                            modeLiveData.postValue(Global.DateViewMode.MODE_LINES)

                            modeLiveData.postValue(ModeDataBean(Global.DateViewMode.MODE_LINES, dateViewpager.currentItem) )
                            imageChangeState.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                        }

                    }
                }
            }
        }

    }

}