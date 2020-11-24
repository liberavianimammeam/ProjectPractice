package com.example.testjsoup.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testjsoup.R
import com.example.testjsoup.model.MainActivityViewModel
import com.example.testjsoup.view.viewholder.StoryViewHolder

class StoryRecyclerViewAdapter(val viewmodel: MainActivityViewModel,var context: Context): RecyclerView.Adapter<StoryViewHolder>(){

    var data: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_activity_main, parent, false))
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bindView(data.get(position))
        if (position == data.size - 1){
            viewmodel.startRefreshData(context)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}