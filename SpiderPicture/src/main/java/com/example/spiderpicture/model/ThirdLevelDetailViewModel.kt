package com.example.spiderpicture.model

import androidx.lifecycle.ViewModel
import com.example.spiderpicture.bean.ImageDetailBean
import com.example.spiderpicture.network.IHttpServer
import com.example.spiderpicture.util.ResolveUtil

class ThirdLevelDetailViewModel: ViewModel() {

    suspend fun requestData(url: String): ArrayList<ImageDetailBean>{
        return ResolveUtil.resolveThirdLevelDetail(IHttpServer.server.requestAbsoluteUrl(url))
    }

}