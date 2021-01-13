package com.example.spiderpicture.bean

import android.graphics.Bitmap
import android.util.Log

class ImageDetailBean(var imageUrl: String,
                      var bitmap: Bitmap? = null,
                      var position: Int,
                      var needRequest: Boolean = bitmap == null
) {

    init {
        Log.i("ImageDetailBeanCreate", "init the imagedetailBean: " + toString())
    }

    override fun toString(): String {
        return "\n".plus(
            """
            ------
            the imageurl is $imageUrl
            the position is $position
            the bitmap is null? ${bitmap == null}
            need request ${needRequest}
            ------
        """.trimIndent()
        )
    }
}