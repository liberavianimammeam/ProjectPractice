package com.example.spiderpicture.model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.spiderpicture.bean.ImageDataBean
import com.example.spiderpicture.util.RequestUtil

class MainActivityViewModel: ViewModel() {

    fun getData(url: String){
        RequestUtil.getHttpData(url)
    }

    fun getImageData(url: String){
        RequestUtil.getImageData(url)
    }

    fun refreshCoverImageListData(list: ArrayList<ImageDataBean>){
        RequestUtil.requestCoverImageListData(list)
    }

}