package com.example.fliemanager.manager

import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import androidx.core.graphics.createBitmap
import androidx.lifecycle.MutableLiveData
import com.example.fliemanager.Global
import com.example.fliemanager.bean.FileNameBean
import java.io.File

object FileManager {

    private val TAG: String = "ProjectPractice_FileManager"
    var pathNow: String? = null

    val pathDataNow: MutableLiveData<ArrayList<FileNameBean>> = MutableLiveData()

    fun getDataAtPath(path: String?){
        if (path == null && pathNow == null){
            pathNow = Environment.getExternalStorageDirectory().absolutePath
        }else if (path == null && pathNow != null){
        }else if (path != null){
            pathNow = path
        }
        var files = File(pathNow)
        Log.i(TAG, "getAll: start the getfiles at pathNow $pathNow and the files is exit? ${files.exists()} and files is directory? ${files.isDirectory}")
        var fileNames = ArrayList<FileNameBean>()
        files.listFiles()?.let {
            for (file: File in it){
//                Log.i(TAG, "getAll: the file name is ${file.name} and the file is file? ${file.isFile}  and the file is directory ${file.isDirectory}")
                fileNames.add(FileNameBean(name = file.name, isDirectory = file.isDirectory, type = judgeFileType(file.name), path = pathNow.plus("/").plus(file.name)))
            }
        }
        pathDataNow.postValue(SortUtil.sortFileList(fileNames))
    }

    fun judgeFileType(fileName: String): String{
        var namePaths = fileName.split(".")
        when(namePaths[namePaths.size - 1]){
            "jpg", "JPG" -> return Global.fileType.jpg
        }
        return Global.fileType.unknown
    }
    
    fun backUp(): Boolean{
        Log.i(TAG, "backUp: pathNow is $pathNow")
        pathNow?.let { 
            if (!it.equals(Environment.getExternalStorageDirectory().absolutePath)){
                for (i in (it.length-1) downTo 0){
//                    Log.i(TAG, "backUp: pathNow is and the char is ${it[i]}  and it equals /? ${it[i].equals("/".toCharArray()[0])}")
                    if (it[i].equals("/".toCharArray()[0])){
                        pathNow = it.substring(0, i)
                        getDataAtPath(pathNow!!)
                        return true
                    }
                }
            }
        }
        return false
    }

    fun getPictureFromPath(path: String){

    }

    
}