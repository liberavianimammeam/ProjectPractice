package com.example.fliemanager.ui

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.fliemanager.Global
import com.example.fliemanager.R
import com.example.fliemanager.manager.FileManager
import com.example.fliemanager.ui.adapter.PicturePagesAdapter
import kotlinx.android.synthetic.main.activity_picture.*

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
        Log.i(
            TAG,
            "onCreate: the width is ${metrics.widthPixels} and the height is ${metrics.heightPixels}"
        )


        var mAdapter = PicturePagesAdapter(
            FileManager.getPictureDataNow(),
            this,
            metrics
        )
        ap_picture_views.adapter = mAdapter

        Log.i(TAG, "onCreate: the pictureDataNow is ${FileManager.getPictureDataNow()}")
        
        //TODO 该Activity下添加沉浸模式

        ap_picture_views.setCurrentItem(
            intent.getIntExtra(Global.intentTag.clickPosition, 0),
            false
        )
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.keyCode == KeyEvent.KEYCODE_BACK){
            var intent: Intent = Intent()
            intent.putExtra("test_for_test", ap_picture_views.currentItem)
            setResult(5, intent)
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus){
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }

    }
}