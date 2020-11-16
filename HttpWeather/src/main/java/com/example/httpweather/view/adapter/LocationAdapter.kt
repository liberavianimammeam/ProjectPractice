package com.example.httpweather.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.httpweather.R
import com.example.httpweather.bean.LocationBean
import com.example.httpweather.view.holder.LocationViewHolder

class LocationAdapter(): RecyclerView.Adapter<LocationViewHolder>() {

    private val TAG = "HttpWeather_LocationAdapter"
    private val A: Char = 'A'
    private var mLocationData: ArrayList<LocationBean>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.location_detail, parent, false))
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        var letter: Char = (A.toByte() + position).toChar()
        var letterData: ArrayList<LocationBean> = ArrayList()
        //TODO 这里获取数据需要消耗时间，需要改进
        //TODO 此处获取地址信息会存在问题，参考ID 101140205 英文名为 anty 但中文名为定日，排序存在问题，需要中文转拼音再进行排序
        mLocationData?.let {
            for (location in it){
                if (location.cityEn.startsWith(letter) || location.cityEn.startsWith((letter.toByte() + 32).toChar())){
                    letterData.add(location)
                }
            }
        }
        Log.i(TAG, "onBindViewHolder: the letterData size is " + letterData.size)
        holder.bindView(letter, letterData)
    }

    override fun getItemCount(): Int {
        return 26
    }

    fun setLocationData(locationdata: ArrayList<LocationBean>){
        this.mLocationData = locationdata
        Log.i(TAG, "setLocationData: the data length is " + locationdata.size)
    }

}