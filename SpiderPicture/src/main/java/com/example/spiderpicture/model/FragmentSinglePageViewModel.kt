package com.example.spiderpicture.model

import androidx.lifecycle.ViewModel
import com.example.spiderpicture.Global
import com.example.spiderpicture.bean.ImageCoverBean
import com.example.spiderpicture.network.IHttpServer
import com.example.spiderpicture.util.ResolveUtil

class FragmentSinglePageViewModel: ViewModel() {

    suspend fun requestDataInPosition(position: Int): ArrayList<ImageCoverBean>{
        return ResolveUtil.resolveLevel2AtPosition(position, IHttpServer.server.requestUrlLevel2(Global.urlSecondLevel[position]))
    }

}