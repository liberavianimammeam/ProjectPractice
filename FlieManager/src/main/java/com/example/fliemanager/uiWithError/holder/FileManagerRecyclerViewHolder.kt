package com.example.fliemanager.uiWithError.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.bean.FileNameBean

class FileManagerRecyclerViewHolder(var itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindView(data: FileNameBean){
//        itemView.findViewById<TextView>(R.id.viewholder_filename).text = data.name
    }

}