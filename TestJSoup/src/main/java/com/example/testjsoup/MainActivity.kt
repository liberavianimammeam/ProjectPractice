package com.example.testjsoup

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testjsoup.model.MainActivityViewModel
import com.example.testjsoup.view.adapter.StoryRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = "TestJSoup_MainActivity"
    val viewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application).create(MainActivityViewModel::class.java)
    }

    private val adapter: StoryRecyclerViewAdapter = StoryRecyclerViewAdapter(viewModel, context = this)
    private val layoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_recyclerView.adapter = adapter
        activity_main_recyclerView.layoutManager = layoutManager

        if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.INTERNET), 0)
        }

        viewModel.startRefreshData(this)
        viewModel.spiderManager?.noteData?.observe(this, Observer {
            adapter.data = it
            adapter.notifyDataSetChanged()
            Log.i(TAG, "onCreate: case new data")
        })


    }
}
