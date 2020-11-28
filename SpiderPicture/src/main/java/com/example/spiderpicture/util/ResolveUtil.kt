package com.example.spiderpicture.util

import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class ResolveUtil(){

    companion object{
        private val TAG: String = "SpiderPicture_ResolveUtil"
        fun resolveRoot(urlData: String){
            var doc: Document = Jsoup.parse(urlData)
            Log.i(TAG, "resolveRoot: the document by class is " + doc.getElementsByClass("post-list-cat-item b2-radius").size)
            for (document in doc.getElementsByClass("post-list-cat-item b2-radius")){
                Log.i(TAG, "resolveRoot: " + document.toString())
            }
            Log.i(TAG, "resolveRoot: the document by class is " + doc.getElementsByClass("post-info").size)
            for (document in doc.getElementsByClass("post-info")){
                Log.i(TAG, "resolveRoot: " + document.toString())
            }
        }

        fun resolveMnxz(urlData: String){

            var doc: Document = Jsoup.parse(urlData)
            var doc_divList = doc.getElementsByClass("post-info")   //第一次获取到24个指向资源的集合
            Log.i(TAG, "resolveMnxz: the post-info number is " + doc.getElementsByClass("post-info").size)
            for (i in doc_divList){
                var doc_divUrls = i.getElementsByTag("div")     //第二次获取到所有含有url的div,里面一般含有两个div，取第一个就好
                Log.i(TAG, "resolveMnxz: " + doc_divUrls[0])
            }
//            for (document in doc.getElementsByClass("post-info")){
//                Log.i(TAG, "resolveMnxz: " + document.getElementsByTag("div")[0].getElementsByTag("img").attr("data-src"))
//            }
        }


    }
}