package com.example.fliemanager.viewmodel

import android.os.Environment
import android.provider.Settings
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fliemanager.Global
import com.example.fliemanager.bean.FileNameBean
import com.example.fliemanager.manager.FileManager
import java.io.File

class SinglePageViewModel: ViewModel() {

    var FilePathLiveData: MutableLiveData<ArrayList<FileNameBean>> = FileManager.pathDataNow

    fun startGetData(){
        var filePath: File = File(Global.defaultPath)
        if (filePath.exists()){
            FileManager.getDataAtPath(Global.defaultPath)
        }else{
            FileManager.getDataAtPath(null)
        }
    }

    fun refreshPathData(choosenPath: String){
        FileManager.getDataAtPath(FileManager.pathNow?.plus("/").plus(choosenPath))
    }

}