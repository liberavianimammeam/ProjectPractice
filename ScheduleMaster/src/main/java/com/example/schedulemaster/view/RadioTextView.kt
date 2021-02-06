package com.example.schedulemaster.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

class RadioTextView: AppCompatTextView{

    private val TAG: String = "ScheduleMaster_RadioTextView"

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        Log.i(TAG, "onDraw: the width is $width and the height is $height")

        var paint: Paint = Paint()
        paint.color = Color.parseColor("#00ffff")
        paint.strokeWidth = 2f
        paint.style = Paint.Style.STROKE

        canvas?.drawArc(0f, 0f, height.toFloat(), width.toFloat(), 0f, 360f, true, paint)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        Log.i(TAG, "onMeasure: the widthMeasureSpec is $widthMeasureSpec and the heightMeasureSpec is $heightMeasureSpec" )
        //TODO 根据屏幕高宽以及textview的margin信息修改其高度和宽度
        setMeasuredDimension(1080/7 - marginBottom - marginTop, 1080/7 - marginLeft - marginRight)
    }
}