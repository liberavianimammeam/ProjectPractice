package com.example.testjsoup.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.testjsoup.manager.SpiderManager

class MainActivityViewModel: ViewModel(){   
    
    private val TAG = "TestJSoup_MainActivityViewModel"

    var spiderManager = SpiderManager.getInstance()

    fun startRefreshData(context: Context){
        Log.i(TAG, "startRefreshData: ")
        spiderManager?.start(context)
    }

    fun stopRefreshData(){
        spiderManager?.stop()
    }
}