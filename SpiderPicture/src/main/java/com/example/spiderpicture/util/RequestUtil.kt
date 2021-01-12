package com.example.spiderpicture.util

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.example.spiderpicture.bean.ImageCoverBean
import com.example.spiderpicture.bean.ImageDetailBean
import com.example.spiderpicture.request.NormalRequest

class RequestUtil(){

    companion object{
        private val TAG: String = "SpiderPicture_RequestUtil"
        private var _text: MutableLiveData<String> = MutableLiveData();
        var text: LiveData<String> = _text
        private var _bitmap: MutableLiveData<Bitmap> = MutableLiveData();
        var bitmap: LiveData<Bitmap> = _bitmap
        private var _listCoverBeanCover: MutableLiveData<ArrayList<ImageCoverBean>> = MutableLiveData()
        var listBeanCover: LiveData<ArrayList<ImageCoverBean>> = _listCoverBeanCover

        val imageDetailLiveData: MutableLiveData<ImageDetailBean> = MutableLiveData()
        val imageDetailErrorLiveData: MutableLiveData<ImageDetailBean> = MutableLiveData()

        private var queue: RequestQueue? = null

        fun init(context: Context){
            queue = Volley.newRequestQueue(context)
        }

        fun getHttpData(url: String){
            if (queue == null){
                throw Exception("RequestUtil is not init")
            }
            var normalRequest = NormalRequest(
                Request.Method.GET,
                url,
                Response.Listener{
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
                Response.ErrorListener{
                    Log.i(TAG, "onCreate: case error when get from http " + url)
                }
            )
            queue?.add(normalRequest)

        }

        fun getImageData(url: String ){
            Log.i(TAG, "getImageData: request the url " + url)
            if (queue == null){
                throw Exception("RequestUtil is not init")
            }
            var imageRequest: ImageRequest = ImageRequest(url, Response.Listener {
                _bitmap.postValue(it)
            }, 120,120, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565, Response.ErrorListener {
                Log.i(TAG, "getImageData: request bitmap error at $url")
            })
            queue?.add(imageRequest)
        }

        fun getImageDetailBean(url: String, position: Int){
            if (queue == null){
                throw Exception("RequestUtil is not init")
            }
            queue!!.cancelAll(url)
            Log.i(TAG, "getImageDetailBean: start imgeRequest at position $position")
            var imageRequest: ImageRequest = ImageRequest(
                url,
                Response.Listener {
                    imageDetailLiveData.postValue(
                        ImageDetailBean(
                            imageUrl = url,
                            bitmap = it,
                            position = position
                        ))
                },
                1000,
                1000,
                ImageView.ScaleType.CENTER,
                Bitmap.Config.ARGB_8888,
                Response.ErrorListener {
                    Log.i(TAG, "getImageDetailBean: request bitmap error at $position and the url is $url")
                    imageDetailErrorLiveData.postValue(
                        ImageDetailBean(
                            imageUrl = url,
                            bitmap = null,
                            position = position
                        )
                    )
                })
            imageRequest.tag = url
            queue!!.add(imageRequest)
        }

        fun getImageDetailBeanList(dataList: ArrayList<ImageDetailBean>){
            if (queue == null){
                throw Exception("RequestUtil is not init")
            }
            Log.i(TAG, "requestCoverImageListData: start request")
            if (dataList.size <= 0) return
            for (data in dataList){
                var imageRequest: ImageRequest = ImageRequest(
                    data.imageUrl,
                    Response.Listener {
                        imageDetailLiveData.postValue(
                            ImageDetailBean(
                                imageUrl = data.imageUrl,
                                bitmap = it,
                                position = data.position
                            ))
                    },
                    1000,
                    1000,
                    ImageView.ScaleType.CENTER,
                    Bitmap.Config.ARGB_8888,
                    Response.ErrorListener {
                        Log.i(TAG, "requestCoverImageListData: request bitmap error at ${data.position} and the url is ${data.imageUrl}")
                        imageDetailErrorLiveData.postValue(
                            ImageDetailBean(
                                imageUrl = data.imageUrl,
                                bitmap = null,
                                position = data.position
                            )
                        )
                    })
                imageRequest.tag = data.imageUrl
                queue!!.add(imageRequest)
            }
        }

        fun requestCoverImageListData(listBean: ArrayList<ImageCoverBean>){
            if (queue == null){
                throw Exception("RequestUtil is not init")
            }
            var list = listBean
            for (i in list){
                if (i.bitmapCover != null) continue
                var imageCoverRequest = ImageRequest(i.coverImageUrl, Response.Listener{
                    i.bitmapCover = it
                    Log.i(TAG, "requestCoverImageListData: bitmap width is " + it.width + "  and the height is " + it.height)
                    _listCoverBeanCover.postValue(list)
                }, 1000, 1000, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.ARGB_8888, Response.ErrorListener {
                    Log.i(TAG, "requestImageListData: get error of coverBitmap at position $i")
                })
                queue?.add(imageCoverRequest)

//                var imageInnerRequest = ImageRequest(listBean[i].innerUrl, Response.Listener{
//                    listBean[i].bitmapInner = it
//                }, 240, 240, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, Response.ErrorListener {
//                    Log.i(TAG, "requestImageListData: get error of innerBitmap at position $i")
//                })
            }
        }

    }
}