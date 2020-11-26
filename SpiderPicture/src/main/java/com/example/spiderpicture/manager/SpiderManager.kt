package com.example.spiderpicture.manager

class SpiderManager {

    companion object{
        private var instance: SpiderManager? = null
        fun getInstance(): SpiderManager?{
            if(instance == null){
                instance = SpiderManager()
            }
            return instance
        }
    }

    fun startGetPictureFromRoot(){

    }


}