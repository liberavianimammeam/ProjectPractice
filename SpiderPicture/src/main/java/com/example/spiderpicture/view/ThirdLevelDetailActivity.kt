package com.example.spiderpicture.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spiderpicture.Global
import com.example.spiderpicture.R
import com.example.spiderpicture.model.ThirdLevelDetailViewModel
import com.example.spiderpicture.view.adapter.ThirdLevelDetailAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ThirdLevelDetailActivity: Activity() {

    private val TAG: String = "SpiderPicture_ThirdLevelDetailActivity"
    private var adapter = ThirdLevelDetailAdapter()

    private val viewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application).create(ThirdLevelDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_level_detail)

        var itemView: RecyclerView = findViewById<RecyclerView>(R.id.sp_activity_thirlvdetail_rv_items)
        itemView.layoutManager = LinearLayoutManager(this)
        itemView.adapter = adapter

        GlobalScope.launch(Dispatchers.Main) {
            intent.extras?.getString(Global.intentTagForThirdLevelDetail)?.let {
                adapter.data = viewModel.requestData(it)
                adapter.notifyDataSetChanged()
            }
        }

        Log.i(TAG, "onCreate: the intent is ${intent.extras?.get("test")}")

    }
}