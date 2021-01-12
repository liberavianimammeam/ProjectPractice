package com.example.spiderpicture.bean

import android.graphics.Bitmap

class ImageDetailBean(var imageUrl: String, var bitmap: Bitmap? = null, var position: Int) {
    override fun toString(): String {
        return "\n".plus(
            """
            ------
            the imageurl is $imageUrl
            the position is $position
            the bitmap is null? ${bitmap == null}
            ------
        """.trimIndent()
        )
    }
}