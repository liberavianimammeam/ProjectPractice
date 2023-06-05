package com.example.fliemanager.manager

import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fliemanager.Global
import com.example.fliemanager.bean.FileNameBean
import com.example.fliemanager.bean.FilePathBean
import java.io.File
import java.io.FilenameFilter

object FileManager {

    private val TAG: String = "ProjectPractice_FileManager"
    //存储当前路径信息，减少运算
    private var pathNow: String? = null
        set(value) {
            field = value
            pathNowLiveData.postValue(value)
        }
    val pathNowLiveData: MutableLiveData<String> = MutableLiveData()
    //创建filePathBean，判断前进或者后退
    val pathBeanLiveData: MutableLiveData<FilePathBean> = MutableLiveData()

    val pathDataNow: MutableLiveData<ArrayList<FileNameBean>> = MutableLiveData()

    fun judgeFileType(fileName: String): String{
        var namePaths = fileName.split(".")
        when(namePaths[namePaths.size - 1]){
            "jpg", "JPG" -> return Global.fileType.jpg
            "png", "PNG" -> return Global.fileType.png
        }
        return Global.fileType.unknown
    }
    fun judgeFileType(f: File): String{
        if (f.isDirectory) return Global.fileType.path

        var namePath = f.name.split(("."))
        when(namePath[namePath.size - 1]){
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


    fun initialize(){
        if(pathNow == null){
            pathNow = Global.defaultPath
        }
    }


    //添加下级路径
    fun addPath(newPath: String){
        var path = pathNow + "/" + newPath
        var file = File(path)
        if (file.exists()){
            pathNow = path
        }
        pathNow?.let {
            pathBeanLiveData.postValue(FilePathBean(it, true))
        }
    }

    //删除最后一级
    fun deleteLastPath(){
        Log.i(TAG, "deleteLastPath:  start")
        pathNow?.let {
            var pathNew = ""
            var pathArray: List<String> = it.split("/")
            if (pathArray.size > 1){
                for (i in 1..(pathArray.size-2)){
                    pathNew = pathNew.plus("/").plus(pathArray[i])
                }
            }
            pathBeanLiveData.postValue(FilePathBean(pathNew, false))
            pathNow = pathNew
        }
    }

    //2023.05.28 获取当前文件路径下的分支，并通知当前数据变更
    fun getPathDataNow(): ArrayList<FileNameBean> {

        //TODO 转化为存储的数据（忘了叫啥····）
        if (pathNow == null) pathNow = Global.defaultPath

        var files = File(pathNow)
        var fileNames = ArrayList<FileNameBean>()

        files.listFiles()?.let{
            for (f in it){
                fileNames.add(FileNameBean(f.name, f.isDirectory, judgeFileType(f), pathNow.plus("/").plus(f.name)))
            }
        }
        fileNames = SortUtil.sortFileList(fileNames)
        pathDataNow.postValue(fileNames)
        return fileNames
    }
    //获取指定路径的文件列表,不更新数据
    fun getDataAtPath(path: String?): ArrayList<FileNameBean>{
        var files = File(path)
        var fileNames = ArrayList<FileNameBean>()

        files.listFiles()?.let{
            for (f in it){
                fileNames.add(FileNameBean(f.name, f.isDirectory, judgeFileType(f), pathNow.plus("/").plus(f.name)))
            }
        }
        return SortUtil.sortFileList(fileNames)
    }

    fun getPathNow():String{
        if (pathNow == null){
            initialize()
        }
        return pathNow!!
    }

    
}