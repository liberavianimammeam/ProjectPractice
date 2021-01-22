package com.example.fliemanager.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fliemanager.Global
import com.example.fliemanager.R
import com.example.fliemanager.manager.FileManager
import com.example.fliemanager.ui.adapter.PicturePagesAdapter
import kotlinx.android.synthetic.main.activity_picture.*
import java.io.File

class PictureActivity: AppCompatActivity() {

    private val TAG: String = "FileManager_PictureActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        Log.i(TAG, "onCreate: and the data list now is ${FileManager.pathDataNow.value}")
//        intent.getStringExtra(Global.intentTag.jpgPath)?.let {
//            ap_imageview.setImageBitmap(BitmapFactory.decodeFile(it))
//        }
        var metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        Log.i(TAG, "onCreate: the width is ${metrics.widthPixels} and the height is ${metrics.heightPixels}")
        ap_picture_views.adapter = PicturePagesAdapter(FileManager.pathDataNow.value!!, this, metrics)


        
    }
}