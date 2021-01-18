package com.example.spiderpicture.util

import android.graphics.Bitmap
import android.util.Log
import java.io.File
import java.io.FileOutputStream

object FileUtil {

    private val TAG: String = "SpiderPicture_FileUtil"

    suspend fun savePictureInPath(name: String, path: String, bitmap: Bitmap): String{
        Log.i(TAG, "savePictureInPath: start save picture in name $name  path ${path}")
        var file: File = File(path)
        if (!file.exists()){
            file.mkdirs()
        }
        var imgFile = File(file, name)

        try {
            var outputStream = FileOutputStream(imgFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        }catch (e: Exception){
            Log.i(TAG, "savePictureInPath: save picture wrong at path ${imgFile.absolutePath}")
        }
        return ""
    }


}