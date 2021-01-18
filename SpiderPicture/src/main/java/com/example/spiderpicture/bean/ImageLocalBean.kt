package com.example.spiderpicture.bean

import androidx.room.PrimaryKey

class ImageLocalBean(
    @PrimaryKey
    var id: Int,
    var localPath: String?,
    var localName: String?,
    var httpUrl: String
)