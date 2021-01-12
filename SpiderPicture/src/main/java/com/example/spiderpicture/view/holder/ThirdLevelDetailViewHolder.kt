package com.example.spiderpicture.view.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.spiderpicture.R
import com.example.spiderpicture.bean.ImageDetailBean

class ThirdLevelDetailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindView(data: ImageDetailBean){

        Glide.with(itemView).load(data.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerInside()
                .into( itemView.findViewById(R.id.sp_viewholder_third_level_detail_image))
    }

}