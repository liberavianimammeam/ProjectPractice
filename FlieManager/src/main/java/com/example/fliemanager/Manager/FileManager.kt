package com.example.fliemanager.Manager

import android.util.Log
import java.io.File

class FileManager {

    private val TAG: String = "FileManager"

    companion object{

        private var instance: FileManager = FileManager()
        @JvmName("getInstance1")
        fun getInstance(): FileManager{
            if (instance == null){
                instance = FileManager()
            }
            return instance
        }
    }

    fun getAll(path: String){
        var files = File(path)
        Log.i(TAG, "getAll: get all list is " + files.list().toString())
        for (file: File in files.listFiles()){

        }
    }

}