package com.example.httpweather.view.holder

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.httpweather.bean.LocationBean
import com.example.httpweather.view.adapter.LocationDetailAdapter
import kotlinx.android.synthetic.main.location_detail.view.*

class LocationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val TAG = "HttpWeather_LocationViewHolder"

    fun bindView(letter: Char, locationData: ArrayList<LocationBean>){
        Log.i(TAG, "bindView: locationData size is " + locationData.size)
        if (locationData.size == 0){
            itemView.visibility = View.GONE
        }else{
            itemView.visibility = View.VISIBLE
        }

        itemView.location_detail_head.text = "" + letter
        itemView.location_detail_RecyclerView.adapter = LocationDetailAdapter(locationData)
        itemView.location_detail_RecyclerView.layoutManager = LinearLayoutManager(itemView.context)
        itemView.location_detail_RecyclerView.addItemDecoration(DividerItemDecoration(itemView.context, DividerItemDecoration.VERTICAL))
    }

}