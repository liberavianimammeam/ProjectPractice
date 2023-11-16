package com.example.fliemanager.ui.viewholder

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.Global
import com.example.fliemanager.R
import com.example.fliemanager.bean.FileNameBean
import com.example.fliemanager.manager.FileManager
import com.example.fliemanager.ui.PictureActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SinglePageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


    fun bindView(dataBean: FileNameBean, data: ArrayList<FileNameBean>){

        //设置文件名称
        itemView.findViewById<TextView>(R.id.hfd_filename).text = dataBean.name

        //根据类型不同修改标志类型
        when(dataBean.type){
            //TODO 拉起图片查看页面
            //TODO 此处拉起图片的position不完善，可能出现bug
            Global.fileType.picture ->{
                itemView.findViewById<ImageView>(R.id.hfd_fileicon).setBackgroundResource(R.drawable.ic_file_picture)
                itemView.setOnClickListener(View.OnClickListener {
                    GlobalScope.launch {
                        var intent = Intent(itemView.context, PictureActivity::class.java)
                        intent.putExtra(Global.intentTag.jpgPath, dataBean.path)
                        intent.putExtra(Global.intentTag.clickPosition, checkPictureClickPosition(data))
                        ContextCompat.startActivity(itemView.context, intent, Bundle.EMPTY)
                    }.start()
                    changeBackgroundColor()
                })
            }
            Global.fileType.path ->{
                itemView.findViewById<ImageView>(R.id.hfd_fileicon).setBackgroundResource(R.drawable.ic_file_path)
                itemView.setOnClickListener(View.OnClickListener {
                    FileManager.addPath(dataBean.name)
                    changeBackgroundColor()
                })
            }
            //TODO 增加播放视频
        }

    }


    //TODO 点击之后将其他的背景变化重置
    private fun changeBackgroundColor(){
        itemView.findViewById<ConstraintLayout>(R.id.hfd_background).setBackgroundColor(Color.parseColor("#8500ffff"))
    }

    fun resetBackgroundColor(){
        itemView.findViewById<ConstraintLayout>(R.id.hfd_background).setBackgroundColor(Color.parseColor("#FFFFFF"))
    }


    //确定当前点击的图片在当前的图片列表中是第几个（全列表中包含其他的站位信息）
    fun checkPictureClickPosition(data: ArrayList<FileNameBean>): Int{
        var pictureCount: Int = 0
        for (i in 0..adapterPosition){
            if (data[i].type == Global.fileType.picture){
                pictureCount ++
            }
        }
        return pictureCount-1
    }


}