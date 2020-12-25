package com.example.spiderpicture.bean

import android.graphics.Bitmap
import androidx.annotation.Nullable

class ImageDataBean(var innerUrl: String, var coverImageUrl: String, @Nullable var bitmapCover: Bitmap?, @Nullable var bitmapInner: Bitmap?){

    constructor(innerUrl: String, coverImageUrl: String): this(innerUrl, coverImageUrl, null, null)

    override fun toString(): String {

        return "the ImageLiDataBean is : \n httpUrl: ".plus(innerUrl)
            .plus("\n coverImageUrl: ").plus(coverImageUrl)
            .plus("\n the bitmap is null ?").plus(bitmapCover == null)
            .plus("\n the bitmap inner is null ?").plus(bitmapInner == null)

    }
}