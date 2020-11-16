package com.example.httpweather.util

import android.content.Context
import android.util.Log
import com.example.httpweather.bean.LocationBean
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

class GsonUtil {
    companion object{
        private val TAG: String = "GsonUtil"
        fun getLocationBean(jsonObject: JSONObject): LocationBean{
            var gson = Gson()
            return  gson.fromJson(jsonObject.toString(), LocationBean::class.java)
        }

        fun getLocationBeanArrayList(jsonArray: JSONArray): ArrayList<LocationBean>{
            var locationBeanArrayList: ArrayList<LocationBean> = ArrayList()
            for (i in 0 until jsonArray.length()){
                Log.i(TAG, "getLocationBeanArrayList: the i is " + i)
                locationBeanArrayList.add(getLocationBean(jsonArray.getJSONObject(i)))
            }
            return locationBeanArrayList
        }

        fun resolveLocalJsonData(context: Context, path: String): StringBuffer{
            var assetManager = context.assets
            var bf: BufferedReader = BufferedReader(InputStreamReader(assetManager.open(path)))
            var line: String
            var i = 0
            var stringBuild: StringBuffer = StringBuffer()
            while (i == 0){
                try {
                    line = bf.readLine()
                    line?.let {
                        stringBuild.append(it)
                        Log.i(TAG, "the line is " + it)
                    }
                }catch (e: Exception){
                    i = 1
                }
            }
            return stringBuild
        }

    }

}