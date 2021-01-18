package com.example.spiderpicture.view.holder

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.spiderpicture.Global
import com.example.spiderpicture.R
import com.example.spiderpicture.bean.ImageCoverBean
import com.example.spiderpicture.view.ThirdLevelDetailActivity

class SinglePageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val TAG: String = "SpiderPicture_SinglePageViewHolder"

    fun bindView(cover: ImageCoverBean, position: Int, pagePosition: Int){
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
        itemView.findViewById<TextView>(R.id.sp_viewholder_single_page_tittle).text = cover.title
//        itemView.findViewById<ImageView>(R.id.sp_viewholder_single_page_image).visibility = View.GONE
//        data.bitmapCover?.let {
//            itemView.findViewById<ImageView>(R.id.sp_viewholder_single_page_image).setImageBitmap(it)
//            itemView.findViewById<ImageView>(R.id.sp_viewholder_single_page_image).visibility = View.VISIBLE
//            Log.i(TAG, "bindView: the itemView height is ${itemView.height} and the itemView width is ${itemView.width}")
//        }
        itemView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                var intent = Intent(itemView.context, ThirdLevelDetailActivity::class.java)
                intent.putExtra(Global.intentTagUrl, cover.thirdLevelInnerUrl)
                intent.putExtra(Global.Pages.TAG, Global.urlSecondLevel[pagePosition])
                intent.putExtra(Global.intentTagTitle, cover.title)
                itemView.context.startActivity(intent)
            }

        })
        Glide.with(itemView).load(cover.coverImageUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerInside()
            .into( itemView.findViewById(R.id.sp_viewholder_single_page_image));

    }

}