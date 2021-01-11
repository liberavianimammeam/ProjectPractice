package com.example.spiderpicture.view.holder

import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.spiderpicture.Global
import com.example.spiderpicture.R
import com.example.spiderpicture.bean.ImageDataBean
import com.example.spiderpicture.network.IHttpServer
import com.example.spiderpicture.util.RequestUtil
import com.example.spiderpicture.view.ThirdLevelDetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SinglePageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val TAG: String = "SpiderPicture_SinglePageViewHolder"

    fun bindView(data: ImageDataBean, position: Int){
        //TODO 如何使用 retrofit 请求网络图片
//        if (data.bitmapCover == null){
//            GlobalScope.launch(Dispatchers.Main) {
////                var bitmapCover = IHttpServer.server.requestBitmap(data.coverImageUrl)
////                requestBitmapLiveData.postValue(bitmapCover)
////                itemView.findViewById<ImageView>(R.id.holder_imageloader_imageview).setImageBitmap()
//                RequestUtil.getImageData(data.coverImageUrl)
//                RequestUtil.bitmap
//            }
//        }else{
//            itemView.findViewById<ImageView>(R.id.holder_imageloader_imageview).setImageBitmap(data.bitmapCover)
//        }
        itemView.findViewById<TextView>(R.id.sp_viewholder_single_page_tittle).text = data.title
//        itemView.findViewById<ImageView>(R.id.sp_viewholder_single_page_image).visibility = View.GONE
//        data.bitmapCover?.let {
//            itemView.findViewById<ImageView>(R.id.sp_viewholder_single_page_image).setImageBitmap(it)
//            itemView.findViewById<ImageView>(R.id.sp_viewholder_single_page_image).visibility = View.VISIBLE
//            Log.i(TAG, "bindView: the itemView height is ${itemView.height} and the itemView width is ${itemView.width}")
//        }
        itemView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                var intent = Intent(itemView.context, ThirdLevelDetailActivity::class.java)
                intent.putExtra(Global.intentTagForThirdLevelDetail, data.thirdLevelInnerUrl)
                itemView.context.startActivity(intent)
            }

        })
        Glide.with(itemView).load(data.coverImageUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerInside()
            .into( itemView.findViewById(R.id.sp_viewholder_single_page_image));

    }

}