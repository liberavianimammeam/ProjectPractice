package com.example.fliemanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.R
import com.example.fliemanager.ui.viewholder.PathDetailViewHolder

class PathAdapter: RecyclerView.Adapter<PathDetailViewHolder>() {

    var pathList: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathDetailViewHolder {
        return PathDetailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_filepath_path, parent, false))
    }

    override fun getItemCount(): Int {
        return pathList.size
    }

    override fun onBindViewHolder(holder: PathDetailViewHolder, position: Int) {
        if (position < pathList.size){
            holder.bindView(pathList[position])
        }
    }
}