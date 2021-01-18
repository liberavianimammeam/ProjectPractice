package com.example.spiderpicture.model

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.Response
import com.example.spiderpicture.bean.ImageDetailBean
import com.example.spiderpicture.network.IHttpServer
import com.example.spiderpicture.util.FileUtil
import com.example.spiderpicture.util.RequestUtil
import com.example.spiderpicture.util.ResolveUtil
import com.example.spiderpicture.view.adapter.ThirdLevelDetailAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ThirdLevelDetailViewModel: ViewModel() {

    private val TAG: String = "SpiderPicture_ThirdLevelDetailViewModel"

//    suspend fun requestUrlData(url: String): ArrayList<ImageDetailBean>{
//        return ResolveUtil.resolveMNXZThirdLevel(IHttpServer.server.requestAbsoluteUrl(url))
//    }

    suspend fun startRefreshPictureData(context: Context, title: String, baseUrl: String, adapter: ThirdLevelDetailAdapter){

        adapter.data = ResolveUtil.resolveNormalThirdLevel(IHttpServer.server.requestAbsoluteUrl(baseUrl))
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
            requestSinglePictureData(context, title, singleData, adapter)
        }
    }

    fun requestSinglePictureData(context: Context, title: String, singleData: ImageDetailBean, adapter: ThirdLevelDetailAdapter){
        Log.i(TAG, "requestSinglePictureData: start the request and the bitmap is ${singleData.bitmap == null}  the position is ${singleData.position}")
        RequestUtil.requestImageDetailBean(
            singleData.imageUrl,
            singleData.position,
            {
                Log.i(
                    TAG,
                    "requestSinglePictureData: get response success at ${singleData.position} and the url is ${singleData.imageUrl} and the bitmap size is ${it.rowBytes * it.height}"
                )
                adapter.data[singleData.position].bitmap = it
                adapter.notifyDataSetChanged()
                //TODO 将图片保存至本地，但目前下载质量太差，注释掉
//                val pictureNames = singleData.imageUrl.split("/")
//                GlobalScope.launch(Dispatchers.IO) {
//                    FileUtil.savePictureInPath(
//                        pictureNames[pictureNames.size - 1],
//                        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!.absolutePath.plus("/").plus(title),
//                        it)
//                }
            },
            {
                Log.i(TAG, "requestSinglePictureData: get error response at ${singleData.position} and the url is ${singleData.imageUrl}")
            })
    }

}