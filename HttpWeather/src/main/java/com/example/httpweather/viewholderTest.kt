package com.example.httpweather

import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView

class viewholderTest(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindNormal(){

    }

    //TODO 为第一个绑定的界面添加额外的view
    /**
     * TODO 添加view的方法存在问题，问题如下：
     * 1、RecyclerView复用contentView之后，添加进去的view也会一起复用
     * 2、通过addView的方法控制布局，ConstraintLayout的高宽并不会随着放进去的View进行变化
     */
    fun bindFirst(){
        var view = LayoutInflater.from(itemView.context).inflate(R.layout.viewholder2, itemView as ConstraintLayout, false)
        itemView.minHeight = view.height
        var set: ConstraintSet = ConstraintSet()

        set.addToHorizontalChain(view.id, ConstraintSet.PARENT_ID, ConstraintSet.PARENT_ID)
        set.addToVerticalChain(view.id, ConstraintSet.PARENT_ID, ConstraintSet.PARENT_ID)
        (itemView as ConstraintLayout).addView(view)
        set.applyTo(itemView)
    }

}