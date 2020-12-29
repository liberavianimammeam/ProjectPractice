package com.example.spiderpicture.view.holder

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.spiderpicture.R

class RootViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindView(bitmap: Bitmap){
        itemView.findViewById<ImageView>(R.id.holder_imageloader_imageview).setImageBitmap(bitmap)
    }

}