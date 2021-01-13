package com.example.spiderpicture.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.Response
import com.example.spiderpicture.bean.ImageDetailBean
import com.example.spiderpicture.network.IHttpServer
import com.example.spiderpicture.util.RequestUtil
import com.example.spiderpicture.util.ResolveUtil
import com.example.spiderpicture.view.adapter.ThirdLevelDetailAdapter
import com.google.android.material.tabs.TabLayout

class ThirdLevelDetailViewModel: ViewModel() {

    private val TAG: String = "SpiderPicture_ThirdLevelDetailViewModel"

//    suspend fun requestUrlData(url: String): ArrayList<ImageDetailBean>{
//        return ResolveUtil.resolveMNXZThirdLevel(IHttpServer.server.requestAbsoluteUrl(url))
//    }

    suspend fun startRefreshPictureData(baseUrl: String, adapter: ThirdLevelDetailAdapter){

        adapter.data = ResolveUtil.resolveMNXZThirdLevel(IHttpServer.server.requestAbsoluteUrl(baseUrl))
        adapter.notifyDataSetChanged()

        if (adapter.data.size == 0) {
            Log.i(TAG, "requestPictureData: the adapter data size is ${adapter.data.size}")
            return
        }
        for (singleData in adapter.data){
            if (singleData.bitmap != null){
                Log.i(TAG, "startRefreshPictureData: the bitmap is ${singleData.bitmap == null}")
                continue
            }
            requestSinglePictureData(singleData, adapter)
        }
    }

    fun requestSinglePictureData(singleData: ImageDetailBean, adapter: ThirdLevelDetailAdapter){
        Log.i(TAG, "requestSinglePictureData: start the request and the bitmap is ${singleData.bitmap == null}  the position is ${singleData.position}")
        RequestUtil.requestImageDetailBean(
            singleData.imageUrl,
            singleData.position,
            {
                Log.i(
                    TAG,
                    "requestSinglePictureData: get response success at ${singleData.position} and the url is ${singleData.imageUrl}"
                )
                adapter.data[singleData.position].bitmap = it
                adapter.notifyDataSetChanged()
            },
            {
                Log.i(TAG, "requestSinglePictureData: get error response at ${singleData.position} and the url is ${singleData.imageUrl}")
            })
    }

}