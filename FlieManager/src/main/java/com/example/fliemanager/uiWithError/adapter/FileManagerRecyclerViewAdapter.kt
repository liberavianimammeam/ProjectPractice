package com.example.fliemanager.uiWithError.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.R
import com.example.fliemanager.uiWithError.holder.FileManagerRecyclerViewHolder

class FileManagerRecyclerViewAdapter: RecyclerView.Adapter<FileManagerRecyclerViewHolder>() {

    private val TAG: String = "FileManager_FileManagerRecyclerViewAdapter"

//    var data: ArrayList<FileNameBean> = ArrayList()
//        set(value){
//            field = value
//            notifyDataSetChanged()
//            Log.i(TAG, "the data is $data: ")
//        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FileManagerRecyclerViewHolder {
        return FileManagerRecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.test_list_with_error, null))
    }

    override fun onBindViewHolder(holder: FileManagerRecyclerViewHolder, position: Int) {
//        Log.i(TAG, "onBindViewHolder: the data size is ${data.size}")
//        if (position < data.size){
//            holder.bindView(data[position])
//        }
    }

    override fun getItemCount(): Int {
//        return data.size
        return 10
    }

}