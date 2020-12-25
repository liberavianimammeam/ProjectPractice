package com.example.spiderpicture.util

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spiderpicture.bean.ImageDataBean
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class ResolveUtil(){

    companion object {
        private val TAG: String = "SpiderPicture_ResolveUtil"

        private val _imageDataBeanList: MutableLiveData<ArrayList<ImageDataBean>> = MutableLiveData()
        val imageDataBeanList : LiveData<ArrayList<ImageDataBean>> = _imageDataBeanList

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

        fun resolveMnxz(urlData: String) {

            var dataList: ArrayList<ImageDataBean> = ArrayList()
            var doc: Document = Jsoup.parse(urlData)
            var doc_LiList = doc.getElementById("post-list").getElementsByTag("li")
            for (i in doc_LiList) {
                for (j in i.getElementsByClass("thumb-link")) {
//                    Log.i(TAG, "resolveMnxz: the j is " + j.getElementsByTag("img")[0].attr("data-src"))
                    dataList.add(ImageDataBean(j.attr("href"), j.getElementsByTag("img")[0].attr("data-src")))
                }
            }
            _imageDataBeanList.postValue(dataList)
            Log.i(TAG, "resolveMnxz: the datalist is " + dataList.toString())
        }
    }
}