package com.example.spiderpicture.bean

import android.graphics.Bitmap
import androidx.annotation.Nullable

class ImageCoverBean(var thirdLevelInnerUrl: String, var coverImageUrl: String, @Nullable var bitmapCover: Bitmap?, var title: String, var maxPage: Int){

    constructor(thirdLevelInnerUrl: String, coverImageUrl: String, title: String): this(thirdLevelInnerUrl, coverImageUrl, null, title, -1)

    override fun toString(): String {

        return "the ImageLiDataBean is : \n httpUrl: ".plus(thirdLevelInnerUrl)
            .plus("\n coverImageUrl: ").plus(coverImageUrl)
            .plus("\n the bitmap is null ?").plus(bitmapCover == null)
            .plus("\n the tittle is ").plus(title)
    }
}