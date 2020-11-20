package com.example.httpweather.view.holder

import android.location.Location
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.httpweather.bean.LocationBean
import kotlinx.android.synthetic.main.location_detail.view.*

class LocationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val TAG = "HttpWeather_LocationViewHolder"

    fun bindView(locationData: LocationBean){
        itemView.location_detail_cityNameZh.text = locationData.cityZh
    }

}