package com.example.spiderpicture.view

import android.Manifest
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.KeyEvent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.example.spiderpicture.Global
import com.example.spiderpicture.R
import com.example.spiderpicture.model.MainActivityViewModel
import com.example.spiderpicture.util.FileUtil
import com.example.spiderpicture.util.RequestUtil
import com.example.spiderpicture.view.fragment.FragmentPages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Files

class MainActivity : AppCompatActivity() {

    private val TAG: String = "SpiderPicture_MainActivity"

    private val viewmodel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application).create(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.sp_activity_main_framelayout, FragmentPages()).commit()

        Log.i(TAG, """
            |cacheDir is ${applicationContext.cacheDir}
            |codeCAcheDir is ${applicationContext.codeCacheDir}
            |dataDir is ${applicationContext.dataDir}
            |externalCacheDir is ${applicationContext.externalCacheDir}
            |filesDir is ${applicationContext.filesDir}
            |noBackupFilesDir is ${applicationContext.noBackupFilesDir}
            |obbDir is ${applicationContext.obbDir}
            |externalCacheDirs is ${applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)}
        """.trimIndent())
        var file: File = File(applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "test.txt")
//        Log.i(TAG, "onCreate: the file is exit ${file.exists()}")
//        if (!file.exists()){
//            Log.i(TAG, "onCreate: mkdir success ${file.mkdir()} and the dir is ${file.path}")
//        }

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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.i(TAG, "onKeyDown: the keyevent is $keyCode and the event is $event")
        if (keyCode == KeyEvent.KEYCODE_BACK){
            //TODO 完成显示dialog
            return true
        }else{
            return super.onKeyDown(keyCode, event)
        }
    }

    override fun onStart() {
        super.onStart()
//        GlobalScope.launch(Dispatchers.IO) {
//            test()
//        }
    }


    suspend fun test(){
        val url: String = "https://www.gteman.com/wp-content/uploads/2021/01/125c80634d00b4_1_post.jpg"

        var imgUrl: URL
        var mBitmap: Bitmap? = null
        try {
            imgUrl = URL(url)
            var conn: HttpURLConnection = imgUrl.openConnection() as HttpURLConnection
            conn.doInput = true
            conn.connect()
            var input = conn.inputStream
            mBitmap = BitmapFactory.decodeStream(input)
        }catch (e: Exception){
            Log.i(TAG, "test: get bitmap wrong at $url")
        }
        mBitmap?.let {
            FileUtil.savePictureInPath("test", this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!.absolutePath, mBitmap)
        }
    }

}