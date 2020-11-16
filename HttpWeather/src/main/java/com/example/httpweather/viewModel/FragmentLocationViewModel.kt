package com.example.httpweather.viewModel

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.httpweather.bean.LocationBean
import com.example.httpweather.consts.Consts
import com.example.httpweather.util.GsonUtil
import com.example.httpweather.util.ioThread
import org.json.JSONArray

class FragmentLocationViewModel: ViewModel() {

    private var _locationDetail: MutableLiveData<Array<LocationBean>> = MutableLiveData<Array<LocationBean>>()
    var locationDetail: LiveData<Array<LocationBean>> = _locationDetail
    companion object{

    }


    fun postDataChange(locationData: Array<LocationBean>){
        _locationDetail.postValue(locationData)
    }


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