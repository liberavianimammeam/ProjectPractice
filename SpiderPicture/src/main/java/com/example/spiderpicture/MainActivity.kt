package com.example.spiderpicture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageRequest
import com.example.spiderpicture.model.MainActivityViewModel
import com.example.spiderpicture.request.NormalRequest
import com.example.spiderpicture.util.RequestUtil
import com.example.spiderpicture.util.ResolveUtil
import com.example.spiderpicture.view.adapter.PictureAdapter

class MainActivity : AppCompatActivity() {

    private val TAG: String = "SpiderPicture_MainActivity"
    private var adapter = PictureAdapter()

    private val viewmodel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application).create(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RequestUtil.init(this)
        viewmodel.getData(Global.urlRoot.plus(Global.urlSecondLevel[0]))

        var recyclerview = findViewById<RecyclerView>(R.id.activity_main_recycler_Image)
        recyclerview.adapter = adapter

//        RequestUtil.bitmap.observe(this, Observer {
//            var imageView: ImageView = findViewById(R.id.activity_main_test_imageview)
//            Log.i(TAG, "onCreate: get bitmap from requestutil ")
//            imageView.setImageBitmap(it)
//        })
        RequestUtil.text.observe(this, Observer {
            ResolveUtil.resolveMnxz(it)
        })
        ResolveUtil.imageDataBeanList.observe(this, Observer{
            viewmodel.refreshCoverImageListData(it)
        })
        RequestUtil.listBeanData.observe(this, Observer {
            adapter.data = it
            adapter.notifyDataSetChanged()
        })
    }



}