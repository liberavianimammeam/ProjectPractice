package com.example.schedulemaster.ui.fragment

import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.R
import com.example.schedulemaster.ui.adapter.DateDetailRVAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
// just for example now
class DateFragment: Fragment() {

//    private val TAG: String = "ScheduleMaster_DateFragment"
//    lateinit var dateRecyclerView: RecyclerView
//    var dateAdapter = DateDetailRVAdapter()
//
//    private val viewModel by lazy {
////        ViewModelProvider.NewInstanceFactory().create(DateViewModel::class.java)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_date, container, false)
//
//        GlobalScope.launch(Dispatchers.Main) {
//            dateAdapter.data = viewModel.getCalendarDateList(
//                Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(
//                    Calendar.MONTH))
//            dateAdapter.chosenPosition = viewModel.datePosition + viewModel.dateLastMonth - 1
//            dateAdapter.notifyDataSetChanged()
//        }
//
//        dateRecyclerView = view.findViewById(R.id.fd_recyclerview)
//        return view
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        dateRecyclerView.layoutManager = GridLayoutManager(context, 7)
//        dateRecyclerView.adapter = dateAdapter
//
//        dateAdapter.chosenDateLiveData.observe(this, Observer {
//            viewModel.datePosition = it
//            dateAdapter.chosenPosition = viewModel.datePosition+ viewModel.dateLastMonth- 1
//            dateAdapter.notifyDataSetChanged()
//        })
//
//
//    }
}