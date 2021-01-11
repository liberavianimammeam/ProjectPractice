package com.example.spiderpicture.network

import android.graphics.Bitmap
import android.util.Log
import com.example.spiderpicture.Global
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

interface IHttpServer {

    @GET("/")
    suspend fun requestUrlRoot(): String

    @GET("/{level2}")
    suspend fun requestUrlLevel2(@Path(value = "level2") level2: String): String

    @GET
    suspend fun requestAbsoluteUrl(@Url url: String): String

    @GET
    suspend fun requestBitmap(@Url url: String): Bitmap


    companion object{
        private val TAG = "SpiderPicture_IHttpServer"
        private val  client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            var original = chain.request()
            var request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .build()
            chain.withConnectTimeout(1000, TimeUnit.MILLISECONDS)
                .withReadTimeout(1000, TimeUnit.MILLISECONDS)
                .withWriteTimeout(1000, TimeUnit.MILLISECONDS)
                .proceed(request)
        }.addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.i(TAG, "log: retrofit back = $message")
            }
        }).setLevel(HttpLoggingInterceptor.Level.HEADERS))
        .connectTimeout(3000, TimeUnit.MILLISECONDS)
        .build()

        var server: IHttpServer = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(Global.urlRoot)
            .client(client)
            .build().create(IHttpServer::class.java)

    }
}