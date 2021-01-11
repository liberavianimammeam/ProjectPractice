package com.example.spiderpicture.model

import android.provider.Settings
import androidx.lifecycle.ViewModel
import com.example.spiderpicture.Global
import com.example.spiderpicture.bean.ImageDataBean
import com.example.spiderpicture.network.IHttpServer
import com.example.spiderpicture.util.ResolveUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentSinglePageViewModel: ViewModel() {

    suspend fun requestDataInPosition(position: Int): ArrayList<ImageDataBean>{
        return ResolveUtil.resolveLevel2AtPosition(position, IHttpServer.server.requestUrlLevel2(Global.urlSecondLevel[position]))
    }

}