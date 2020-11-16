package com.example.httpweather.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.httpweather.R
import com.example.httpweather.bean.LocationBean
import com.example.httpweather.view.holder.LocationDetailViewHolder

class LocationDetailAdapter(var locationData: ArrayList<LocationBean>): RecyclerView.Adapter<LocationDetailViewHolder>() {

    private val TAG = "HttpWeather_LocationDetailAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationDetailViewHolder {
        return LocationDetailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.location_detail_holder, parent, false))
    }

    override fun onBindViewHolder(holder: LocationDetailViewHolder, position: Int) {
        holder.bindView(locationData[position])
        Log.i(TAG, "onBindViewHolder: " + locationData.size)
    }

    override fun getItemCount(): Int {
        return locationData.size
    }
}