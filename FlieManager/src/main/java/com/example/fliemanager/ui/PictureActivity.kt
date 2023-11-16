package com.example.fliemanager.ui

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.fliemanager.Global
import com.example.fliemanager.R
import com.example.fliemanager.bean.FileNameBean
import com.example.fliemanager.databinding.ActivityPictureBinding
import com.example.fliemanager.manager.FileManager
import com.example.fliemanager.ui.adapter.PicturePagesAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PictureActivity: AppCompatActivity() {

    private val TAG: String = "FileManager_PictureActivity"
    private lateinit var pictureData: ArrayList<FileNameBean>
    private val positionLiveData = MutableLiveData<Int>()
    
    private lateinit var binding: ActivityPictureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)




        GlobalScope.launch {
            pictureData = FileManager.getPictureDataNow()

            binding.apPictureName.text = pictureData[intent.getIntExtra(Global.intentTag.clickPosition, 0)].name
            binding.apCount.text = (intent.getIntExtra(Global.intentTag.clickPosition, 0)+1).toString().plus("/").plus(pictureData.size.toString())

            var mAdapter = PicturePagesAdapter(
                FileManager.getPictureDataNow(),
                this@PictureActivity,
                metrics
            )
            
            binding.apPictureViews.adapter = mAdapter
            binding.apPictureViews.rotationY = 180f

            binding.apPictureViews.setCurrentItem(
                intent.getIntExtra(Global.intentTag.clickPosition, 0),
                false
            )
            binding.apPictureViews.registerOnPageChangeCallback(myCallBack(positionLiveData))
        }.start()

        positionLiveData.observe(this, Observer {
            binding.apPictureName.text = pictureData[it].name
            binding.apCount.text = (it+1).toString().plus("/").plus(pictureData.size.toString())
        })

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.keyCode == KeyEvent.KEYCODE_BACK){
            var intent: Intent = Intent()
            intent.putExtra("test_for_test", binding.apPictureViews.currentItem)
            setResult(5, intent)
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus){
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }

    }

    class myCallBack(val p: MutableLiveData<Int>):ViewPager2.OnPageChangeCallback(){

        private val TAG = "PictureActivity_CallBack"

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            Log.i(TAG, "onPageScrolled: ")
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            p.postValue(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            Log.i(TAG, "onPageScrollStateChanged: ")
        }
    }

}