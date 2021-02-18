package com.example.schedulemaster.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.R
import com.example.schedulemaster.ui.adapter.DateMonthRVAdapter
import com.example.schedulemaster.ui.adapter.PlanRVAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Deprecated("暂不使用，如需使用需要添加调整高度的逻辑")
class ScheduleFragment: Fragment() {

    private val TAG: String = "ScheduleMaster_CalendarMonthFragment"

    lateinit var planRecyclerView: RecyclerView
    lateinit var dateRecyclerView: RecyclerView

    private var touchX: Float = -1f
    private var touchTime: Float = -1f
    private var moveX: Float = -1f
    private var moveTime: Float = -1f
    private var position: Int = 10

//    var monthAdapter: MonthAdapter = MonthAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule_month, container, false)
//        planRecyclerView = view.findViewById<RecyclerView>(R.id.fcm_plan_recyclerview)
//        dateRecyclerView = view.findViewById<RecyclerView>(R.id.fcm_date_recyclerview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        planRecyclerView.adapter = PlanRVAdapter()
        planRecyclerView.layoutManager = LinearLayoutManager(context)

        val dateLayoutManager = LinearLayoutManager(context)
        dateLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        dateRecyclerView.layoutManager = dateLayoutManager
        dateRecyclerView.adapter = DateMonthRVAdapter(this)


//        var layoutManager = GridLayoutManager(context, 7)

        var imageChangeState = view.findViewById<ImageView>(R.id.fsm_change_surface)

        imageChangeState.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                context?.let {
                    //TODO
                }
            }
        }
        dateRecyclerView.smoothScrollToPosition(10)
        dateRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        dateRecyclerView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                event?.let {
                    if (it.action == 2) {
                        if (touchX == -1f || touchTime == -1f) {
                            touchX = it.x
                            touchTime = it.eventTime.toFloat()
                            return false
                        } else {
                            moveX = it.x - touchX
                            moveTime = it.eventTime - touchTime
                            return false
                        }
                    } else if (it.action == 1) {
                        Log.i(TAG, "onTouch: the moveX is $moveX and the movex/movetime is ${moveX/moveTime}")
                        if (moveX >= dateRecyclerView.width / 5 || moveX / moveTime >= 2f) {
                            position -= 1
                            dateRecyclerView.smoothScrollToPosition(position)
                            touchX = -1f
                            touchTime = -1f
                            return true
                        } else if (moveX <= -dateRecyclerView.width / 5 || moveX / moveTime <= -2f) {
                            position += 1
                            dateRecyclerView.smoothScrollToPosition(position)
                            touchX = -1f
                            touchTime = -1f
                            return true
                        } else {
                            dateRecyclerView.smoothScrollToPosition(position)
                            touchX = -1f
                            moveX = -1f
                            touchTime = -1f
                            return true
                        }
                    }
                }
                return false
            }
        })


    }

}