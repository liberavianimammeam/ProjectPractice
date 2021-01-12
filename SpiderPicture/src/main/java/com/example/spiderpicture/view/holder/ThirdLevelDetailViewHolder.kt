package com.example.spiderpicture.view.holder

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.spiderpicture.R
import com.example.spiderpicture.bean.ImageDetailBean
import com.example.spiderpicture.util.RequestUtil

class ThirdLevelDetailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindView(data: ArrayList<ImageDetailBean>, position: Int){
        itemView.findViewById<TextView>(R.id.sp_viewholder_third_level_detail_title).text = data[position].imageUrl


//        Glide.with(itemView).load(data[position].imageUrl)
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                .centerInside()
//                .into( itemView.findViewById(R.id.sp_viewholder_third_level_detail_image))


        if (data[position].bitmap == null){
//            if ()
//            RequestUtil.getImageDetailBean(data[position].imageUrl, position)
        }else{
            itemView.findViewById<ImageView>(R.id.sp_viewholder_third_level_detail_image).setImageBitmap(data[position].bitmap)
        }

    }

}