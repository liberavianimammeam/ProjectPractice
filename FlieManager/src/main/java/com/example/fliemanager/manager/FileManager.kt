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
        set(value) {
            field = value
            pathNowLiveData.postValue(value)
        }
    val pathNowLiveData: MutableLiveData<String> = MutableLiveData()

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
                if (file.isDirectory){
                    fileNames.add(FileNameBean(name = file.name, isDirectory = file.isDirectory, type = Global.fileType.path, path = pathNow.plus("/").plus(file.name)))
                }else{
                    fileNames.add(FileNameBean(name = file.name, isDirectory = file.isDirectory, type = judgeFileType(file.name), path = pathNow.plus("/").plus(file.name)))
                }
            }
        }
        pathDataNow.postValue(SortUtil.sortFileList(fileNames))
    }

    fun judgeFileType(fileName: String): String{
        var namePaths = fileName.split(".")
        when(namePaths[namePaths.size - 1]){
            "jpg", "JPG" -> return Global.fileType.jpg
            "png", "PNG" -> return Global.fileType.png
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

    fun getPictureDataNow(): ArrayList<FileNameBean>{
        var pictureData: ArrayList<FileNameBean> = ArrayList()
        pathDataNow.value?.let {
            for (pathData in it){
                if (pathData.type.equals(Global.fileType.jpg) || pathData.type.equals(Global.fileType.png)){
                    pictureData.add(pathData)
                }
            }
        }
        return pictureData
    }

    fun getPathList(path: String): ArrayList<String>{
        var data: ArrayList<String> = ArrayList()

        val pathWithOutExternalPath = path.substring(Environment.getExternalStorageDirectory().absolutePath.length)
        data.add("内部存储空间")
        val pathArray = pathWithOutExternalPath.split("/".toCharArray()[0])
        Log.i(TAG, "getPathList: the path split with / is ${pathWithOutExternalPath.split(System.getProperty("file.separator").toCharArray()[0])[0].equals("")}")
        for (pathDetail in pathArray){
            if (!pathDetail.equals("") && !pathDetail.equals(null)){
                data.add(pathDetail)
            }
        }

        return data
    }

    
}