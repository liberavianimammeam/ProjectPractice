package com.example.fliemanager

import android.os.Environment

object Global {

    object fileType{
        val unknown: String = "unknown"
        val jpg: String = "jpg"
        val png: String = "png"
        val path: String = "path"
    }

    object intentTag{
        val jpgPath: String = "JPG_PATH"
        val clickPosition: String = "CLICK_POSITION"
    }

    var filePositionNow: String? = null
    val pages = arrayListOf<String>("文件")
    //图片页面返回时修改，改变当前图片位置颜色
    var positionReturn: Int = -1
    val defaultPath: String = Environment.getExternalStorageDirectory().absolutePath.plus("/aa我的文件夹")

}