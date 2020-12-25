package com.example.testjsoup.manager

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.testjsoup.http_request.NormalRequestTest
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class SpiderManager {

    private val TAG: String = "TestJSoup_SpiderManager"
    private val HTTP_TAG = "story_TAG"

    private val URL_START = "/15_15338/8549128.html"
    private val URL_ROOT = "https://www.xsbiquge.com"

    private val _noteData: MutableLiveData<ArrayList<String>> = MutableLiveData()
    var noteData: LiveData<ArrayList<String>> = _noteData

    var urlData: ArrayList<String> = arrayListOf(URL_START)
    var storyData: ArrayList<String> = ArrayList()

    lateinit var queue: RequestQueue

    var shouldStop: Boolean = true

    companion object{
        private var instance: SpiderManager? = null
        fun getInstance(): SpiderManager?{
            if (instance == null){
                instance = SpiderManager()
            }
            return instance
        }
    }

    fun start(context: Context){
        Log.i(TAG, "start: ")
        if (shouldStop){
            shouldStop = false
            queue = Volley.newRequestQueue(context)
            getData(context, storyData.size)
        }
    }

    fun stop(){
        Log.i(TAG, "stop: ")
        shouldStop = true
        queue.cancelAll(HTTP_TAG)
    }

    fun getData(context: Context, position: Int){
        Log.i(TAG, "getData: start the position is " + position + "  and the url is  " + urlData.get(position))
        if (!shouldStop){
            var stringRequest = NormalRequestTest(
                Request.Method.GET,
                URL_ROOT.plus(urlData[position]),
                Response.Listener{
                    var doc: Document = Jsoup.parse(it)
                    urlData.add(
                        doc.getElementsByClass("bottem2")[0].getElementsByAttribute(
                            "href"
                        )[2].attr("href")
                    )
                    storyData.add(
                        "    ".plus(
                            doc.getElementById("content").text().replace(" ", "\n    ")
                        )
                    )
                    _noteData.postValue(storyData)
                    if (position != 0 && position % 10 == 0) shouldStop = true
                    getData(context, position + 1)
                    Log.i(TAG, "getData: the size of url is " + urlData.size + " \n  and the story size is " + storyData.size + "\n the new url is " +
                            urlData[urlData.size -1] + "\n    and the position now is " + position)
                },
                Response.ErrorListener{
                    Log.i(TAG, "onCreate: case error when get from http")
                }
            ).setTag(HTTP_TAG)
            queue.add(stringRequest)
        }else return
    }
}