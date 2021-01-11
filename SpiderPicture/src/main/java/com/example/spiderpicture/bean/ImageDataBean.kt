package com.example.spiderpicture.bean

import android.graphics.Bitmap
import androidx.annotation.Nullable

class ImageDataBean(var innerUrl: String, var coverImageUrl: String, @Nullable var bitmapCover: Bitmap?, var tittle: String){

    constructor(innerUrl: String, coverImageUrl: String, tittle: String): this(innerUrl, coverImageUrl, null, tittle)

    override fun toString(): String {

        return "the ImageLiDataBean is : \n httpUrl: ".plus(innerUrl)
            .plus("\n coverImageUrl: ").plus(coverImageUrl)
            .plus("\n the bitmap is null ?").plus(bitmapCover == null)
            .plus("\n the tittle is ").plus(tittle)
    }
}