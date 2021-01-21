package com.example.fliemanager.manager

import android.util.Log
import com.example.fliemanager.Global
import com.example.fliemanager.bean.FileNameBean
import java.io.File

object FileManager {

    private val TAG: String = "FileManager"

    fun getAll(path: String): ArrayList<FileNameBean>{
        var files = File(path)
        Log.i(TAG, "getAll: start the getfiles at path $path and the files is exit? ${files.exists()} and files is directory? ${files.isDirectory}")
        var fileNames = ArrayList<FileNameBean>()
        for (file: File in files.listFiles()){
            Log.i(TAG, "getAll: the file name is ${file.name} and the file is file? ${file.isFile}  and the file is directory ${file.isDirectory}")
            fileNames.add(FileNameBean(name = file.name, isDirectory = file.isDirectory, type = judgeFileType(file.name)))
        }
        return fileNames
    }

    fun judgeFileType(fileName: String): String{
        var namePaths = fileName.split(".")
        when(namePaths[namePaths.size - 1]){
            ".jpg", ".JPG" -> return Global.fileType.unknown
        }
        return Global.fileType.unknown
    }

}