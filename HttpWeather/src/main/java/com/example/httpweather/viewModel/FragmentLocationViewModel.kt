package com.example.httpweather.viewModel

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.httpweather.bean.LocationBean
import com.example.httpweather.consts.Consts
import com.example.httpweather.util.GsonUtil
import com.example.httpweather.util.PinYinUtil
import com.example.httpweather.util.SortUtil
import com.example.httpweather.util.ioThread
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

class FragmentLocationViewModel: ViewModel() {

    private var _locationDetail: MutableLiveData<ArrayList<LocationBean>> = MutableLiveData<ArrayList<LocationBean>>()
    var locationDetail: LiveData<ArrayList<LocationBean>> = _locationDetail
    companion object{

    }


    fun postDataChange(locationData: ArrayList<LocationBean>){
        _locationDetail.postValue(locationData)
    }

    fun resolveJsonData(context: Context){
        ioThread {
            var data: ArrayList<LocationBean> = GsonUtil.getLocationBeanArrayList(JSONArray("${GsonUtil.resolveLocalJsonData(context, Consts.LOCAL_JSON_PATH)}"))
            Collections.sort(data, SortUtil.LocationSortUtil())
            _locationDetail.postValue(data)
        }
    }

//    fun getLocationDataOfLetter(letter: Char): LiveData<ArrayList<LocationBean>>{
//        var _locationLetterData = MutableLiveData<ArrayList<LocationBean>>()
//        var locationLetterData = _locationLetterData
//        ioThread {
//            var data: ArrayList<LocationBean> = ArrayList()
//            for (location in locationData){
//                if (PinYinUtil.getPinYin(location.cityZh).startsWith(letter) || PinYinUtil.getPinYin(location.cityZh).startsWith((letter.toByte() + 32).toChar())){
//                    data.add(location)
//                }
//                Collections.sort(data, SortUtil.LocationSortUtil.getInstance())
//                _locationLetterData.postValue(data)
//            }
//        }
//        return locationLetterData
//    }

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