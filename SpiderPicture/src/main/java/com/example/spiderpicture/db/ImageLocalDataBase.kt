package com.example.spiderpicture.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spiderpicture.bean.ImageLocalBean

@Database(entities = [ImageLocalBean::class], version = 1)
abstract class ImageLocalDataBase: RoomDatabase() {

    abstract fun dao(): ImageLocalDao

    companion object{
        private var instance: ImageLocalDataBase? = null
        fun getInstance(context: Context): ImageLocalDataBase{
            if (instance == null){
                instance = Room.databaseBuilder(context.applicationContext, ImageLocalDataBase::class.java, "image_local").build()
            }
            return instance!!
        }
    }

}