package com.example.schedulemaster.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulemaster.Global
import com.example.schedulemaster.R
import com.example.schedulemaster.bean.ModeDataBean
import com.example.schedulemaster.manager.DataManager
import com.example.schedulemaster.ui.adapter.DateDetailRVAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SingleMonthDateFragment(val position: Int, val lineNumberLiveData: MutableLiveData<Int>, val modeLiveData: MutableLiveData<ModeDataBean>): Fragment() {

    private val TAG: String = "FileManager_SingleMonthDateFragment"
    private lateinit var dateRecyclerView: RecyclerView
    private val adapter = DateDetailRVAdapter()
    private val linearLayoutManager by lazy {
        LinearLayoutManager(context)
    }
    private val gridLayoutManager by lazy {
        GridLayoutManager(context, 7)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_single_month_date, container, false)
//        view.findViewById<TextView>(R.id.fsmd_test_text).text = position.toString()
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        dateRecyclerView = view.findViewById(R.id.fsmd_date_recyclerview)
        return view
    }


    override fun onStart() {
        super.onStart()
        dateRecyclerView.adapter = adapter
        if (modeLiveData.value == null){
            dateRecyclerView.layoutManager = gridLayoutManager
        }else{
            when(modeLiveData.value!!.mode){
                Global.DateViewMode.MODE_SINGLE_LINE -> dateRecyclerView.layoutManager = linearLayoutManager
                Global.DateViewMode.MODE_LINES -> dateRecyclerView.layoutManager = gridLayoutManager
                else -> dateRecyclerView.layoutManager = gridLayoutManager
            }
        }
        
        GlobalScope.launch(Dispatchers.Main) {
            adapter.data = DataManager.getDateList(Global.StartTime.startYear + position/12, Global.StartTime.startMonth + position % 12)
            adapter.notifyDataSetChanged()
        }
        adapter.chosenDateLiveData.observe(this, Observer {
            DataManager.chosenDate = it
            adapter.notifyDataSetChanged()
        })
        modeLiveData.observe(this, Observer {
            Log.i(TAG, "onStart: case the value is $it")
            when (it.mode) {
                Global.DateViewMode.MODE_LINES -> {
                    dateRecyclerView.layoutManager = gridLayoutManager
                    if (it.position == position){
                        lineNumberLiveData.postValue(adapter.data.size / 7)
                    }
                }
                Global.DateViewMode.MODE_SINGLE_LINE -> {
                    dateRecyclerView.layoutManager = linearLayoutManager
                }
                else -> {
                    dateRecyclerView.layoutManager = gridLayoutManager
                }
            }
        })
    }

    override fun onResume() {
        Log.i(TAG, "onResume: at position $position")
        super.onResume()
        lineNumberLiveData.postValue(adapter.data.size/7)

        if (modeLiveData.value == null){
            dateRecyclerView.layoutManager = gridLayoutManager
        }else{
            when(modeLiveData.value!!.mode){
                Global.DateViewMode.MODE_SINGLE_LINE -> dateRecyclerView.layoutManager = linearLayoutManager
                Global.DateViewMode.MODE_LINES -> dateRecyclerView.layoutManager = gridLayoutManager
                else -> dateRecyclerView.layoutManager = gridLayoutManager
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: at position $position")
    }

}