package com.example.spiderpicture.util

import android.util.Log
import com.example.spiderpicture.bean.ImageCoverBean
import com.example.spiderpicture.bean.ImageDetailBean
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

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

    fun resolveLevel2AtPosition(position: Int, urlResponse: String): ArrayList<ImageCoverBean>{
        when(position){
            0 -> return resolveMNXZ(urlResponse)
            1 -> return resolveJDLY(urlResponse)
            2 -> return resolveFLJ(urlResponse)
            3 -> return resolveTAGS(urlResponse)
            else -> throw Exception("there is no resolve method for the position $position")
        }
    }


    fun resolveMNXZ(urlResponse: String): ArrayList<ImageCoverBean> {
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
        
        var coverList: ArrayList<ImageCoverBean> = ArrayList()
        var rootDoc: Document = Jsoup.parse(urlResponse)
        var items = rootDoc.getElementById("page")
            .getElementById("content")
            .getElementById("primary-home")
            .getElementById("post-list")
            .getElementsByClass("item-in b2-radius")
        for (item in items){
            val coverPictureUrl: String = item.getElementsByClass("post-module-thumb b2-radius")[0].getElementsByTag("img")[0].attr("data-src")
            val title: String = item.getElementsByClass("post-info")[0].getElementsByTag("h2")[0].getElementsByTag("a")[0].text().trim()
            val thirdLevelInnerUrl: String = item.getElementsByClass("post-info")[0].getElementsByTag("h2")[0].getElementsByTag("a")[0].attr("href")

            coverList.add(ImageCoverBean(thirdLevelInnerUrl = thirdLevelInnerUrl, title = title, coverImageUrl = coverPictureUrl))
        }
        return coverList
    }




    fun resolveJDLY(urlResponse: String): ArrayList<ImageCoverBean>{
        var coverList: ArrayList<ImageCoverBean> = ArrayList()
        var rootDoc: Document = Jsoup.parse(urlResponse)
        var items = rootDoc
            .getElementById("page")
            .getElementById("content")
            .getElementById("primary-home")
            .getElementById("post-list")
            .getElementsByClass("item-in b2-radius")
        for (item in items){
            var title: String = item.getElementsByClass("post-info")[0].getElementsByTag("h2")[0].getElementsByTag("a")[0].text().trim()
            var thirdLevelInnerUrl: String = item.getElementsByClass("post-info")[0].getElementsByTag("h2")[0].getElementsByTag("a")[0].attr("href")
            var coverPictureUrl: String = item.getElementsByClass("post-module-thumb b2-radius")[0].getElementsByTag("img")[0].attr("data-src")

            coverList.add(ImageCoverBean(thirdLevelInnerUrl = thirdLevelInnerUrl, coverImageUrl = coverPictureUrl, title = title))
        }
        return coverList
    }
    fun resolveFLJ(urlResponse: String): ArrayList<ImageCoverBean>{
        var coverList: ArrayList<ImageCoverBean> = ArrayList()
        var rootDoc: Document = Jsoup.parse(urlResponse)
        var items = rootDoc
            .getElementById("page")
            .getElementById("content")
            .getElementById("primary-home")
            .getElementById("post-list")
            .getElementsByClass("item-in b2-radius")
        for (item in items){
            var title: String = item.getElementsByClass("post-info")[0].getElementsByTag("h2")[0].getElementsByTag("a")[0].text().trim()
            var thirdLevelInnerUrl: String = item.getElementsByClass("post-info")[0].getElementsByTag("h2")[0].getElementsByTag("a")[0].attr("href")
            var coverPictureUrl: String = item.getElementsByClass("post-module-thumb b2-radius")[0].getElementsByTag("img")[0].attr("data-src")

            coverList.add(ImageCoverBean(title = title, thirdLevelInnerUrl = thirdLevelInnerUrl, coverImageUrl = coverPictureUrl))
        }

        return coverList
    }
    fun resolveTAGS(urlResponse: String): ArrayList<ImageCoverBean>{
        var coverList: ArrayList<ImageCoverBean> = ArrayList()
        val rootDoc: Document = Jsoup.parse(urlResponse)
        val items = rootDoc
            .getElementById("page")
            .getElementById("content")
            .getElementById("tags")
            .getElementById("main")
            .getElementsByTag("li")
        for (item in items){
            val title = item.getElementsByTag("p")[0].text()
            val thirdLevelInnerUrl = item.getElementsByTag("a").attr("href")
            val coverPictureUrl = item.getElementsByTag("img")[0].attr("src")

            coverList.add(ImageCoverBean(thirdLevelInnerUrl = thirdLevelInnerUrl, coverImageUrl = coverPictureUrl, title = title))
        }
        return coverList
    }

    fun resolveThirdLevelDetail(urlResponse: String): ArrayList<ImageDetailBean>{
        var coverList: ArrayList<ImageDetailBean> = ArrayList()
        try{
            val rootDoc: Document = Jsoup.parse(urlResponse)
            val items = rootDoc
                    .getElementById("page")
                    .getElementById("content")
                    .getElementById("primary-home")
                    .getElementsByTag("article")[0]
                    .getElementsByTag("p")
            for(item in items){
                coverList.add(ImageDetailBean(imageUrl = item.getElementsByTag("img")[0].attr("src")))
            }
        }catch (e: Exception){
            Log.i(TAG, "resolveThirdLevelDetail: ${e.message}")
        }
        return coverList
    }
}