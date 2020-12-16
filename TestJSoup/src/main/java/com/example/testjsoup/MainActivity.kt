package com.example.testjsoup

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TimeUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testjsoup.bean.*
import com.example.testjsoup.http_request.IRequest
import com.example.testjsoup.model.MainActivityViewModel
import com.example.testjsoup.view.adapter.StoryRecyclerViewAdapter
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.xml.bind.JAXBElement

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG: String = "TestJSoup_MainActivity"
    val viewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application).create(MainActivityViewModel::class.java)
    }

    lateinit var mRetrofit: Retrofit
    lateinit var mRequest: IRequest
    private val adapter: StoryRecyclerViewAdapter = StoryRecyclerViewAdapter(viewModel, context = this)
    private val layoutManager = LinearLayoutManager(this)

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        activity_main_recyclerView.adapter = adapter
//        activity_main_recyclerView.layoutManager = layoutManager
//
//        if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
//            requestPermissions(arrayOf(Manifest.permission.INTERNET), 0)
//        }
//
//        viewModel.startRefreshData(this)
//        viewModel.spiderManager?.noteData?.observe(this, Observer {
//            adapter.data = it
//            adapter.notifyDataSetChanged()
//            Log.i(TAG, "onCreate: case new data")
//        })
//
//
//    }

    //TODO retrofit 获取小说
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        Log.i(TAG, "onCreate: start request")
//        var retrofit = Retrofit.Builder()
//            .baseUrl("https://api.thinkpage.cn")
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//        var mRequest: IRequest = retrofit.create(IRequest::class.java)
//        var test = mRequest.getWeather()
//        test.enqueue(object : Callback<WeatherBean> {
//            override fun onResponse(call: Call<WeatherBean>, response: Response<WeatherBean>) {
//                var weather: WeatherBean? = response.body()
//                Log.i(TAG, """onResponse: the response is $response
//                    | and the response body is $weather
//                    | and the response head is ${response.headers()}
//                    | and the response code is ${response.code()}
//                    | and the response message is ${response.message()}
//                """.trimMargin())
//
//            }
//
//            override fun onFailure(call: Call<WeatherBean>, t: Throwable) {
//                Log.i(TAG, "onFailure: the request faill")
//            }
//
//        })
//        Log.i(TAG, "onCreate: after request")
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_button1.text = "身份证检票接口"

        activity_main_button1.setOnClickListener(this)
        activity_main_button2.setOnClickListener(this)


        var client = OkHttpClient.Builder()
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
            .build()

        mRetrofit = Retrofit.Builder()
                .baseUrl("https://pdev.cleartv.cn")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
        mRequest = mRetrofit.create(IRequest::class.java)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.activity_main_button1 -> {
                GlobalScope.launch {
                    runBlocking {
                        var test = mRequest.test(
//                    "/checker/clear/heartbeat",
                            HeartBeatBean(
                                "AABBCCDDEEFF",
                                "AA:BB:CC:DD:EE:FF",
                                "192.168.17.100",
                                1,
                                "1.0"
                            )
                        )
                        Log.i(TAG, "onClick: test is " + test)
                    }
                }

            }
            R.id.activity_main_button2 -> {
                var test = mRequest.testPost(
//                    "/checker/clear/idcardverify",
                    IDRequestBean(
                        "AABBCCDDEEFF",
                        "AA:BB:CC:DD:EE:FF",
                        "02139193419238931",
                        "0",
                        "times"
                    )
                )
                test.enqueue(object : Callback<IDCheckBean> {
                    override fun onResponse(
                        call: Call<IDCheckBean>,
                        response: Response<IDCheckBean>
                    ) {
                        Log.i(
                            TAG, """the response head is ${response.headers()}
                            |and the response code is ${response.code()}
                            |and the response body is ${response.body()}
                            |and the response error body is ${response.errorBody()}
                        """.trimIndent()
                        )
                    }

                    override fun onFailure(call: Call<IDCheckBean>, t: Throwable) {
                        Log.i(TAG, "onFailure: request id faill")
                    }

                })
            }
        }
    }

}
