package com.example.httpweather.view.holder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.httpweather.bean.LocationBean
import kotlinx.android.synthetic.main.location_detail_holder.view.*

class LocationDetailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val TAG = "HttpWeather_LocationDetailViewHolder"
    fun bindView(locationBean: LocationBean){
        itemView.location_detail_holder_location.text = locationBean.cityZh
        itemView.location_detail_holder_id.text = "${locationBean.id}"
    }

}