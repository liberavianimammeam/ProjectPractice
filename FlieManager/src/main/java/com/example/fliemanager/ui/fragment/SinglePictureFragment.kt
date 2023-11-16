package com.example.fliemanager.ui.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.fliemanager.Global
import com.example.fliemanager.R
import com.example.fliemanager.bean.FileNameBean
import com.example.fliemanager.databinding.FragmentSinglePictureBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SinglePictureFragment(var data: FileNameBean, var metrics: DisplayMetrics): Fragment(){

    private val TAG: String = "FileManager_SinglePictureFragment"
    private val bitmapLiveData = MutableLiveData<Boolean>()
    private var mBitmap: Bitmap? = null
    private lateinit var binding: FragmentSinglePictureBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSinglePictureBinding.inflate(layoutInflater)
//        return inflater.inflate(R.layout.fragment_single_picture, container, false)
        return binding.root
    }

    init {
        Log.i(TAG, "init: the FileNameBean is $data")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fspSinglePicture.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                //TODO 双击放大图片，不能通过imageview.setheight 以及 imageview.setwidth 来进行调整
                //TODO 最好有一段时间的过渡

            }
        })

    }

    override fun onStart() {
        super.onStart()

        GlobalScope.launch(Dispatchers.IO) {
            var option = BitmapFactory.Options()
            option.inJustDecodeBounds = true
            var bitmap = BitmapFactory.decodeFile(data.path, option)

            var bitmapHeight = option.outHeight
            var bitmapWidth = option.outWidth

            if (bitmapHeight > metrics.heightPixels || bitmapWidth > metrics.widthPixels){
                var heightRatio: Int = Math.round((bitmapHeight / metrics.heightPixels).toDouble()).toInt()
                var widthRatio: Int = Math.round((bitmapWidth/ metrics.widthPixels).toDouble()).toInt()
                option = BitmapFactory.Options()
                option.inJustDecodeBounds = false
                option.inSampleSize = if (heightRatio > widthRatio) widthRatio else heightRatio
                mBitmap = BitmapFactory.decodeFile(data.path, option)
                bitmapLiveData.postValue(true)
                Log.i(TAG, "onStart: mBitmap is null ? ${mBitmap == null} \n" + "and the decood file path is " + data.path)
            }else{
                mBitmap = BitmapFactory.decodeFile(data.path)
                bitmapLiveData.postValue(true)
                Log.i(TAG, "onStart: mBitmap is null ? ${mBitmap == null}")
            }
        }

        bitmapLiveData.observe(this, Observer{
            if (it){
                binding.fspSinglePicture.setImageBitmap(mBitmap)
            }
        })

    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)


    }
}