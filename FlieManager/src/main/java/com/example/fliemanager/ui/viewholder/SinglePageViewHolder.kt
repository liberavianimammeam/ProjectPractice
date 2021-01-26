package com.example.fliemanager.ui.viewholder

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.R
import com.example.fliemanager.bean.FileNameBean

class SinglePageViewHolder(var itemView: View): RecyclerView.ViewHolder(itemView) {

    private val TAG: String = "FileManager_SinglePageViewHolder"

    fun bindView(data: FileNameBean, choosePath: MutableLiveData<FileNameBean.FileNamePositionBean>, position: Int){
        itemView.findViewById<TextView>(R.id.hfd_filename).text = data.name
        Log.i(TAG, "bindView: the data is diirectory? ${data.isDirectory} and the name is ${data.name}  and the type is ${data.type}")

        itemView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                choosePath.postValue(FileNameBean.FileNamePositionBean(data, position))
            }
        })

    }

}