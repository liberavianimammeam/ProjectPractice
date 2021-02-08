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
import com.example.schedulemaster.Global

class RadioTextView: AppCompatTextView{

    private val TAG: String = "ScheduleMaster_RadioTextView"
    private var beenChosen = false
    private var color: Int = Color.parseColor("#5900FFFF")

    val roundPaint: Paint = Paint()

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        Log.i(TAG, "onDraw: the width is $width and the height is $height")

        roundPaint.strokeWidth = 2f
        if (beenChosen){
            roundPaint.style = Paint.Style.FILL
        }else{
            roundPaint.style = Paint.Style.STROKE
        }

        if (Global.RadioTextViewInfo.state.equals(Global.ChangeState.STATE_CHANGING)){
            roundPaint.color = Color.parseColor("#00000000")
        }else{
            roundPaint.color = color
        }

        canvas?.drawArc(0f, 0f, height.toFloat(), width.toFloat(), 0f, 360f, true, roundPaint)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        Log.i(TAG, "onMeasure: the widthMeasureSpec is $widthMeasureSpec and the heightMeasureSpec is $heightMeasureSpec" )
        Global.RadioTextViewInfo.radioTextViewHeight = Math.min(1080/7 - marginBottom - marginTop, 1080/7 - marginLeft - marginRight)
        Global.RadioTextViewInfo.radioTextViewWidth = Math.min(1080/7 - marginBottom - marginTop, 1080/7 - marginLeft - marginRight)
        Global.RadioTextViewInfo.marginTop = marginTop
        Global.RadioTextViewInfo.marginBottom = marginBottom
        //TODO 根据屏幕高宽以及textview的margin信息修改其高度和宽度
        setMeasuredDimension(Math.min(1080/7 - marginBottom - marginTop, 1080/7 - marginLeft - marginRight),
            Math.min(1080/7 - marginBottom - marginTop, 1080/7 - marginLeft - marginRight))
    }

    fun changeChosenState(beenChosen: Boolean){
        this.beenChosen = beenChosen
        invalidate()
    }

    fun changeRoundColor(color: Int){
        this.color = color
        invalidate()
    }
}