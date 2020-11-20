package com.example.httpweather.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.httpweather.R
import com.example.httpweather.bean.LocationBean
import com.example.httpweather.util.PinYinUtil
import com.example.httpweather.util.SortUtil
import com.example.httpweather.view.holder.LocationViewHolder
import com.example.httpweather.viewModel.FragmentLocationViewModel
import java.util.*
import kotlin.collections.ArrayList

class LocationAdapter(var viewModel: FragmentLocationViewModel,val fragment: Fragment): RecyclerView.Adapter<LocationViewHolder>() {

    private val TAG = "HttpWeather_LocationAdapter"
    private val A: Char = 'A'

    var mLocationData = ArrayList<LocationBean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.location_detail, parent, false))
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
//        var letter: Char = (A.toByte() + position).toChar()
        //TODO 这里获取数据需要消耗时间，需要改进
        //TODO 此处获取地址信息会存在问题，参考ID 101140205 英文名为 anty 但中文名为定日，排序存在问题，需要中文转拼音再进行排序
        //TODO 修改双层recyclerView结构，需要在右方添加scorllView，用以标定位置
        //TODO 11.20 决定还是使用双层RecyclerView结构····真香，方法设置为类似于下拉菜单一样的，参考QQ水浒的各武将装备栏强化栏情况
//        mLocationData?.let {
//            for (location in it){
//                if (location.cityEn.startsWith(letter) || location.cityEn.startsWith((letter.toByte() + 32).toChar())){
//                    letterData.add(location)
//                }
//            }
//        }
//        Log.i(TAG, "onBindViewHolder: the letterData size is " + letterData.size)
//        holder.bindView(letter, letterData)
        holder.bindView(mLocationData[position])
    }

    override fun getItemCount(): Int {
        return mLocationData.size
    }
}