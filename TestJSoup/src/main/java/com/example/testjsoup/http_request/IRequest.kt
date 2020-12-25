package com.example.testjsoup.http_request

import com.example.testjsoup.bean.*
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.lang.reflect.MalformedParameterizedTypeException

interface IRequest {

    @GET("/")
    fun getList(): Call<String>

    @GET("/v3/weather/now.json?key=rot2enzrehaztkdk&location=guangzhou&language=zh-Hans&unit=c")
    fun getWeather(): Call<WeatherBean>

    @POST("/checker/clear/idcardverify")
    fun testPost( @Body body: IDRequestBean): Call<IDCheckBean>

    @POST("{test}")
    fun testPostHeartBean(@Path("test")path: String, @Body body: HeartBeatBean): Call<HeartBeatResultBean>

    @POST("checker/clear/heartbeat")
    suspend fun test(@Body body: HeartBeatBean): HeartBeatResultBean
}