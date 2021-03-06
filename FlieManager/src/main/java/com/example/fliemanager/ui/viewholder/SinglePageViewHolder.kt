package com.example.fliemanager.ui.viewholder

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.Global
import com.example.fliemanager.R
import com.example.fliemanager.bean.FileNameBean

class SinglePageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val TAG: String = "FileManager_SinglePageViewHolder"

    fun bindView(data: FileNameBean, choosePath: MutableLiveData<FileNameBean.FileNamePositionBean>, position: Int){
        itemView.findViewById<TextView>(R.id.hfd_filename).text = data.name
        Log.i(TAG, "bindView: the data is diirectory? ${data.isDirectory} and the name is ${data.name}  and the type is ${data.type}")
        val imageView: ImageView = itemView.findViewById<ImageView>(R.id.hfd_fileicon)

        when(data.type){
            Global.fileType.jpg, Global.fileType.png -> {
                imageView.setBackgroundResource(R.drawable.ic_file_picture)
            }
            Global.fileType.path -> {
                imageView.setBackgroundResource(R.drawable.ic_file_path)
            }
            else -> {
                imageView.setBackgroundResource(R.drawable.ic_file_unknown)
            }
        }

        itemView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                choosePath.postValue(FileNameBean.FileNamePositionBean(data, position))
            }
        })

    }

    fun changeBackground(haveBeenChosen: Boolean){
        if (haveBeenChosen){
            itemView.findViewById<ConstraintLayout>(R.id.hfd_background).setBackgroundColor(Color.parseColor("#8500ffff"))
        }else{
            itemView.findViewById<ConstraintLayout>(R.id.hfd_background).setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

}