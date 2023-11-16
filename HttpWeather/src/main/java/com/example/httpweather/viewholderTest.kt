package com.example.httpweather

import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.httpweather.bean.LocationBean
import com.example.httpweather.util.GsonUtil
//import kotlinx.android.synthetic.main.viewholder1.view.*
import org.json.JSONObject
import java.lang.Exception

class viewholderTest(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val TAG: String = "HttpWeather-viewholderTest"
    var view = LayoutInflater.from(itemView.context).inflate(R.layout.viewholder2, itemView as ConstraintLayout, false)

    fun bindNormal(locationBean: LocationBean){

        init()

//        itemView.viewHolder_id.text = "ID: ${locationBean.id}"
//        itemView.viewHolder_lon.text = "经度: ${locationBean.lon}"
//        itemView.viewHolder_lat.text = "纬度: ${locationBean.lat}"
//        itemView.viewHolder_cityName.text = "城市: ${locationBean.cityZh}"
//        itemView.viewHolder_province.text = "省份: ${locationBean.provinceZh}"
//        itemView.viewholder_leader.text = "领导?: ${locationBean.leaderZh}"
//
//        itemView.viewHolder_id.visibility = View.VISIBLE
//        itemView.viewHolder_province.visibility = View.VISIBLE
//        itemView.viewHolder_lat.visibility = View.VISIBLE
//        itemView.viewHolder_lon.visibility = View.VISIBLE
//        itemView.viewHolder_cityName.visibility = View.VISIBLE
//        itemView.viewholder_leader.visibility = View.VISIBLE

    }

    //TODO 为第一个绑定的界面添加额外的view
    /**
     * TODO 添加view的方法存在问题，问题如下：
     * 1、RecyclerView复用contentView之后，添加进去的view也会一起复用
     * 2、通过addView的方法控制布局，ConstraintLayout的高宽并不会随着放进去的View进行变化
     */
    fun bindFirst(){
        itemView.minimumHeight = view.height
        var set: ConstraintSet = ConstraintSet()

        set.addToHorizontalChain(view.id, ConstraintSet.PARENT_ID, ConstraintSet.PARENT_ID)
        set.addToVerticalChain(view.id, ConstraintSet.PARENT_ID, ConstraintSet.PARENT_ID)
        if (view.parent == null){
            (itemView as ConstraintLayout).addView(view)
            set.applyTo(itemView)
        }
    }

    fun init(){
//        itemView.viewHolder_id.visibility = View.GONE
//        itemView.viewHolder_province.visibility = View.GONE
//        itemView.viewHolder_cityName.visibility = View.GONE
//        itemView.viewHolder_lat.visibility = View.GONE
//        itemView.viewHolder_lon.visibility = View.GONE
//        itemView.viewholder_leader.visibility = View.GONE
    }

}