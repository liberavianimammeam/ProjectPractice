package com.example.spiderpicture.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spiderpicture.Global
import com.example.spiderpicture.R
import com.example.spiderpicture.bean.ImageDetailBean
import com.example.spiderpicture.model.ThirdLevelDetailViewModel
import com.example.spiderpicture.util.RequestUtil
import com.example.spiderpicture.view.adapter.ThirdLevelDetailAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ThirdLevelDetailActivity: AppCompatActivity() {

    private val TAG: String = "SpiderPicture_ThirdLevelDetailActivity"
    private var adapter = ThirdLevelDetailAdapter()
    private var imageDetailBeanData: ArrayList<ImageDetailBean> = ArrayList()
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
                imageDetailBeanData = viewModel.requestData(it)
                adapter.data = imageDetailBeanData
                Log.i(TAG, "onCreate: the data is $imageDetailBeanData")
                adapter.notifyDataSetChanged()
                RequestUtil.getImageDetailBeanList(dataList = imageDetailBeanData)
            }
        }

        RequestUtil.imageDetailLiveData.observe(this, Observer {
            if (it.position < imageDetailBeanData.size){
                imageDetailBeanData[it.position] = it
                adapter.notifyDataSetChanged()
            }
        })

        RequestUtil.imageDetailErrorLiveData.observe(this, Observer {
            Log.i(TAG, "onCreate: get detailErrorLiveData in position ${it.position}")
            if (it.position >= imageDetailBeanData.size) return@Observer
            if (imageDetailBeanData[it.position].bitmap != null) return@Observer
            RequestUtil.getImageDetailBean(it.imageUrl, it.position)

        })

        Log.i(TAG, "onCreate: the intent is ${intent.extras?.get("test")}")

    }
}