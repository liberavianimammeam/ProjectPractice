package com.example.spiderpicture.util

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spiderpicture.bean.ImageDataBean
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.text.FieldPosition

object ResolveUtil{
    private val TAG: String = "SpiderPicture_ResolveUtil"

    fun resolveRoot(urlData: String) {
        var doc: Document = Jsoup.parse(urlData)
        Log.i(
            TAG,
            "resolveRoot: the document by class is " + doc.getElementsByClass("post-list-cat-item b2-radius").size
        )
        for (document in doc.getElementsByClass("post-list-cat-item b2-radius")) {
            Log.i(TAG, "resolveRoot: " + document.toString())
        }
        Log.i(
            TAG,
            "resolveRoot: the document by class is " + doc.getElementsByClass("post-info").size
        )
        for (document in doc.getElementsByClass("post-info")) {
            Log.i(TAG, "resolveRoot: " + document.toString())
        }
    }

    fun resolveLevel2AtPosition(position: Int, urlResponse: String): ArrayList<ImageDataBean>{
        when(position){
            0 -> return resolveMNXZ(urlResponse)
            1 -> return resolveJDLY(urlResponse)
            2 -> return resolveFLJ(urlResponse)
            3 -> return resolveTAGS(urlResponse)
//            1 -> return resolveMNXZ(urlResponse)
//            2 -> return resolveMNXZ(urlResponse)
//            3 -> return resolveMNXZ(urlResponse)
            else -> throw Exception("there is no resolve method for the position $position")
        }
    }


    fun resolveMNXZ(urlResponse: String): ArrayList<ImageDataBean> {
//        var dataList: ArrayList<ImageDataBean> = ArrayList()
//        var doc: Document = Jsoup.parse(urlResponse)
//        var doc_LiList = doc.getElementById("post-list").getElementsByTag("li")
//        for (i in doc_LiList) {
//            for (j in i.getElementsByClass("thumb-link")) {
////                    Log.i(TAG, "resolveMnxz: the j is " + j.getElementsByTag("img")[0].attr("data-src"))
//                dataList.add(ImageDataBean(j.attr("href"), j.getElementsByTag("img")[0].attr("data-src")))
//            }
//        }
//        Log.i(TAG, "resolveMnxz: the datalist is " + dataList.toString())
//        return dataList

        var dataList: ArrayList<ImageDataBean> = ArrayList()

        var rootDoc: Document = Jsoup.parse(urlResponse)
        var items = rootDoc.getElementById("page")
                .getElementById("content")
                .getElementById("primary-home")
                .getElementById("post-list")
                .getElementsByTag("li")
        for (item in items){
            var tittleItem = item.getElementsByClass("post-info")
            var pictureItem = item.getElementsByClass("post-module-thumb b2-radius")

            var coverPictureUrl = pictureItem[0].getElementsByTag("img")[0].attr("data-src")
            var innerPictureUrl = pictureItem[0].attr("href")
            var tittle = tittleItem[0].getElementsByTag("h2")
        }

        return dataList
    }




    fun resolveJDLY(urlResponse: String): ArrayList<ImageDataBean>{
        TODO("not done as well")
    }
    fun resolveFLJ(urlResponse: String): ArrayList<ImageDataBean>{
        TODO("not done as well")
    }
    fun resolveTAGS(urlResponse: String): ArrayList<ImageDataBean>{
        TODO("not done as well")
    }
}