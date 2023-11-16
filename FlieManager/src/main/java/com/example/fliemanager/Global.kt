package com.example.fliemanager

import android.os.Environment

object Global {

    object fileType{
        val unknown: String = "unknown"
        val jpg: String = "jpg"
        val JPG: String = "JPG"
        val png: String = "png"
        val PNG: String = "PNG"
        val mp4: String = "mp4"
        val MP4: String = "MP4"
        val mp3: String= "mp3"
        val MP3: String= "MP3"


        val picture: String = "picture"
        val video: String = "video"
        val music: String = "music"

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
    val rootPath: String = Environment.getExternalStorageDirectory().absolutePath
}