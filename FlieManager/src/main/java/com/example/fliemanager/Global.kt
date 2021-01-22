package com.example.fliemanager

object Global {

    object fileType{
        val unknown: String = "unknown"
        val jpg: String = "jpg"
    }

    object intentTag{
        val jpgPath: String = "JPG_PATH"
    }

    var filePositionNow: String? = null
    val pages = arrayListOf<String>("文件", "测试")

}