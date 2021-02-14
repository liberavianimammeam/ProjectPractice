package com.example.fliemanager.ui.viewholder

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.R
import kotlinx.android.synthetic.main.holder_filepath_path.view.*

class PathDetailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    val TAG: String = "FileManager_PathDetailViewHolder"

    fun bindView(path: String){
        Log.i(TAG, "bindView: the path is $path")
        itemView.findViewById<TextView>(R.id.hfp_path).text = ">".plus(path)
    }

}