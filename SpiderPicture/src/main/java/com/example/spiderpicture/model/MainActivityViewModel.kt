package com.example.spiderpicture.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.spiderpicture.Global
import com.example.spiderpicture.bean.ImageDataBean
import com.example.spiderpicture.network.IHttpServer
import com.example.spiderpicture.util.RequestUtil
import com.example.spiderpicture.util.ResolveUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    private val TAG: String = "SpiderPicture_MainActivityViewModel"

    fun getData(url: String){
        RequestUtil.getHttpData(url)
    }

    fun getRootData(){
        GlobalScope.launch(Dispatchers.Default) {
            Log.i(TAG, "getRootData: the root data is ${IHttpServer.server.requestUrlRoot()}")
            ResolveUtil.resolveRoot(IHttpServer.server.requestUrlRoot())
        }
    }

    fun getMNXZData(){
        GlobalScope.launch(Dispatchers.Default) {
            Log.i(TAG, "getMNXZData: the data is ${IHttpServer.server.requestUrlLevel2(Global.urlSecondLevel[0])}")
        }
    }

    fun getJDLYData(){
        GlobalScope.launch {
            Log.i(TAG, "getJDLYData: the data is ${IHttpServer.server.requestUrlLevel2(Global.urlSecondLevel[1])}")
        }
    }

    fun getFLJData(){
        GlobalScope.launch {
            Log.i(TAG, "getFLJData: the data flj is ${IHttpServer.server.requestUrlLevel2(Global.urlSecondLevel[2])}")
        }
    }

    fun getTAGSData(){
        GlobalScope.launch {
            Log.i(TAG, "getTAGSData: the data tags is ${IHttpServer.server.requestUrlLevel2(Global.urlSecondLevel[3])}")
        }
    }

    fun getImageData(url: String){
        RequestUtil.getImageData(url)

//        GlobalScope.launch {
//            IHttpServer.server.requestBitmap(url)
//        }

    }

    fun refreshCoverImageListData(list: ArrayList<ImageDataBean>){
        RequestUtil.requestCoverImageListData(list)
    }

}