package com.example.httpweather

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.httpweather.consts.Consts
import com.example.httpweather.util.GsonUtil
import com.example.httpweather.util.ioThread
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

class MainActivityViewModel: ViewModel() {

    private val TAG: String = "HttpWeather-MainActivityViewModel"
    private var _jsonArray = MutableLiveData<JSONArray>()
    var jsonArray: LiveData<JSONArray> = _jsonArray

    fun resolveJsonData(context: Context){
        ioThread {
            _jsonArray.postValue(JSONArray("${GsonUtil.resolveLocalJsonData(context, Consts.LOCAL_JSON_PATH)}"))
        }
    }

    //TODO 左方地点的RecyclerView显示动画
    fun startFrameAnimation(model: String){
        when(model){
            Consts.APPEAR -> {

            }
            Consts.DISAPPEAR -> {

            }
        }

    }

}