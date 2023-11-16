package com.example.fliemanager.manager

import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fliemanager.Global
import com.example.fliemanager.bean.FileNameBean
import com.example.fliemanager.bean.FilePathBean
import java.io.File
import java.io.FilenameFilter
import java.util.ArrayList

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
    private var pathData = ArrayList<FileNameBean>()
    private var pathPictureData = ArrayList<FileNameBean>()

    fun judgeFileType(f: File): String{
        if (f.isDirectory) return Global.fileType.path

        var namePath = f.name.split(("."))
        when(namePath[namePath.size - 1]){
            Global.fileType.jpg, Global.fileType.JPG -> return Global.fileType.picture
            "png", "PNG" -> return Global.fileType.picture
            "mp4", "MP4" -> return Global.fileType.video
            "mp3", "MP3" -> return Global.fileType.music
        }
        return Global.fileType.unknown
    }

    fun getPictureDataNow(): ArrayList<FileNameBean>{

        return pathPictureData
    }

    fun getPathList(path: String): ArrayList<String>{
        var data: ArrayList<String> = ArrayList()

        val pathWithOutExternalPath = path.substring(Environment.getExternalStorageDirectory().absolutePath.length)
        data.add("内部存储空间")
        val pathArray = pathWithOutExternalPath.split("/".toCharArray()[0])
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

    fun returnToRootPath(){
        pathNow?.let {
            pathNow = Global.rootPath
        }
    }

    //2023.05.28 获取当前文件路径下的分支
    suspend fun getPathDataNow(): ArrayList<FileNameBean> {
        return getDataAtPath(getPathNow())
    }
    //获取指定路径的文件列表,不更新数据
    suspend fun getDataAtPath(path: String?): ArrayList<FileNameBean>{
        var files = File(path)
        var fileNames = ArrayList<FileNameBean>()
        pathPictureData.clear()
        files.listFiles()?.let{
            for (f in it){
                val fileName = FileNameBean(f.name, f.isDirectory, judgeFileType(f), pathNow.plus("/").plus(f.name))
                fileNames.add(fileName)
                if (fileName.type == Global.fileType.picture){
                    fileName.picturePosition = pathPictureData.size
                    pathPictureData.add(fileName)
                }

            }
        }

        pathPictureData = SortUtil.sortFileList(pathPictureData)
        pathData = SortUtil.sortFileList(fileNames)
        return pathData
    }

    fun getPathNow():String{
        if (pathNow == null){
            initialize()
        }
        return pathNow!!
    }

    
}