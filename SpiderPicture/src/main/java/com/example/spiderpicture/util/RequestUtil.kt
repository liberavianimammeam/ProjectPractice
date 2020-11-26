package com.example.spiderpicture.util

import android.provider.Settings
import android.util.Log
import com.android.volley.Request
import com.example.spiderpicture.Global
import com.example.spiderpicture.request.NormalRequest
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class RequestUtil(){

    companion object{
        private val TAG: String = "SpiderPicture_RequestUtil"
        fun getHttpDataFromRoot(){

            var NormalRequest = NormalRequest(
                Request.Method.GET,
                Global.urlRoot,
                {
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
                    Log.i(TAG, "onCreate: case error when get from http")
                }
            )

        }


    }
}