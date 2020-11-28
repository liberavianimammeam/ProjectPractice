package com.example.spiderpicture.util

import android.content.Context
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.example.spiderpicture.Global
import com.example.spiderpicture.request.NormalRequest
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class RequestUtil(){

    companion object{
        private val TAG: String = "SpiderPicture_RequestUtil"
        private var _text: MutableLiveData<String> = MutableLiveData();
        var text: LiveData<String> = _text
        fun getHttpData(url: String, context: Context){
            var queue = Volley.newRequestQueue(context)
            var normalRequest = NormalRequest(
                Request.Method.GET,
                url,
                {
                    _text.postValue(it)
                    Log.i(TAG, "getHttpData: the response is $it")
//                    var doc: Document = Jsoup.parse(it)
//                    urlData.add(
//                        doc.getElementsByClass("bottem2")[0].getElementsByAttribute(
//                            "href"
//                        )[2].attr("href")
//                    )
//                    storyData.add(
//                        "    ".plus(
//                            doc.getElementById("content").text().replace(" ", "\n    ")
//                        )
//                    )
//                    _noteData.postValue(storyData)
//                    if (position != 0 && position % 10 == 0) shouldStop = true
//                    getData(context, position + 1)
//                    Log.i(TAG, "getData: the size of url is " + urlData.size + " \n  and the story size is " + storyData.size + "\n the new url is " +
//                            urlData[urlData.size -1] + "\n    and the position now is " + position)
                },
                {
                    Log.i(TAG, "onCreate: case error when get from http " + url)
                }
            )
            queue.add(normalRequest)

        }


    }
}