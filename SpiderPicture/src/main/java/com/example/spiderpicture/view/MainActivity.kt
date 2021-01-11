package com.example.spiderpicture.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.spiderpicture.R
import com.example.spiderpicture.model.MainActivityViewModel
import com.example.spiderpicture.view.fragment.FragmentPages

class MainActivity : AppCompatActivity() {

    private val TAG: String = "SpiderPicture_MainActivity"

    private val viewmodel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application).create(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.sp_activity_main_framelayout, FragmentPages()).commit()

//        GlobalScope.launch(Dispatchers.Main) {
//            ResolveUtil.resolveTAGS(IHttpServer.server.requestUrlLevel2(Global.urlSecondLevel[3]))
//        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        RequestUtil.init(this)
//        viewmodel.getData(Global.urlRoot.plus(Global.urlSecondLevel[0]))
//
//        var recyclerview = findViewById<RecyclerView>(R.id.activity_main_recycler_Image)
//        recyclerview.adapter = adapter
//
////        RequestUtil.bitmap.observe(this, Observer {
////            var imageView: ImageView = findViewById(R.id.activity_main_test_imageview)
////            Log.i(TAG, "onCreate: get bitmap from requestutil ")
////            imageView.setImageBitmap(it)
////        })
//        RequestUtil.text.observe(this, Observer {
//            ResolveUtil.resolveMnxz(it)
//        })
//        ResolveUtil.imageDataBeanList.observe(this, Observer{
//            viewmodel.refreshCoverImageListData(it)
//        })
//        RequestUtil.listBeanData.observe(this, Observer {
//            adapter.data = it
//            adapter.notifyDataSetChanged()
//        })
//
//
////        adapter.clickEventLiveData.observe(this, Observer {
////            //请求点击的 itemview 中的数据
////            viewmodel.getData()
////        })
//
//
//    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        Log.i(TAG, "onCreate: the width of the recyclerview is ${findViewById<RecyclerView>(R.id.activity_main_recycler_Image).layoutManager?.width}")
    }

    override fun onStart() {
        super.onStart()
    }
}