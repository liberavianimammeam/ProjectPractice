package com.example.spiderpicture.model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.spiderpicture.util.RequestUtil

class MainActivityViewModel: ViewModel() {

    fun getData(url: String, context: Context){
        RequestUtil.getHttpData(url, context)
    }

}