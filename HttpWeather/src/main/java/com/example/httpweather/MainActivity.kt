package com.example.httpweather

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.util.Xml
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.httpweather.util.ioThread
import com.example.httpweather.view.FragmentLocation
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val TAG: String = "HttpWeather-MainActivity"
    private val url = "http://v.juhe.cn/weather/index"
    private val testurl = "https://v.juhe.cn/weather/index?format=2&cityname=%E4%B8%8A%E6%B5%B7&key=e512763b6f35bec3a44b3da9153c20a2"
    private val testurl2 = "https://api.thinkpage.cn/v3/weather/now.json?key=rot2enzrehaztkdk&location=guangzhou&language=zh-Hans&unit=c"
    private val key = "key=e512763b6f35bec3a44b3da9153c20a2"

    private val urlTest = "https://tianqiapi.com/api"
    private val version: String = "version=v6"
    private val appid = "appid=96116628"
    private val appSecret = "appsecret=Vilm13yL"
    //cityid 请参考根目录下 city_ids_weatherApi.json 中所列举的数据
    private val cityid = "cityid=101240601"
    //可以单独只传入 city 进行查询
    private val city = "city=吉安"
    private val ip = "ip=192.168.10.1"

    lateinit var fragmentLocation: FragmentLocation

    val viewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainActivityViewModel::class.java)
    }

    private lateinit var jsonArray: JSONArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var char: Char = 'A'
        var string: String = "A"
        Log.i(TAG, "onCreate: the 15 is " + (string.toCharArray()[0].toByte() + 1).toChar())

        if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.INTERNET), 0)
        }

        var queue: RequestQueue = Volley.newRequestQueue(this)

        var jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            urlTest.plus("?").plus(version).plus("&").plus(appid).plus("&").plus(appSecret)
                .plus("&").plus(cityid),
            null,
            {
//                Log.i(TAG, "onCreate: the result json is " + jsonObjectTest.toString())
            },
            {
                Log.i(TAG, "onCreate: case error when get jsonObject")
            }
        )
        queue.add(jsonObjectRequest)

        var adapter = adapterTest()
        httpWeather_activityMain_recyclerviewTest.layoutManager = LinearLayoutManager(this)
        httpWeather_activityMain_recyclerviewTest.adapter = adapter
        httpWeather_activityMain_recyclerviewTest.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        var set: ConstraintSet = ConstraintSet()
        set.clone(activityMain_constrainLayout)
        set.constrainPercentWidth(activityMain_frameLayout_locations.id, 0.3f)
        fragmentLocation = FragmentLocation()
        supportFragmentManager.beginTransaction().replace(activityMain_frameLayout_locations.id, fragmentLocation, "location").commit()
        Log.i(TAG, "onCreate: the fragmentLocation id is " + fragmentLocation.id +" and the fragmentLocation tag is " + fragmentLocation.tag)
        set.applyTo(activityMain_constrainLayout)

        viewModel.resolveJsonData(this)
        viewModel.jsonArray.observe(this, Observer {
            Log.i(TAG, "onCreate: the json array's first jsonObject is ${it[0]}")
            adapter.setJsonArray(it)
            adapter.notifyDataSetChanged()
            fragmentLocation

        })
//        supportFragmentManager.findFragmentById()
//        val jsonRequest = JsonRequest(Request.Method.GET,
//            url,
//            requestBody = null,
//            Response.Listener { response ->
//                Log.i(TAG, "onCreate: test ")
//            },
//            Response.ErrorListener {
//                Log.i(TAG, "onCreate: wrong")
//            }
//        )

//        val stringRequest = StringRequest(Request.Method.GET, url,
//            Response.Listener { response ->
//                // Display the first 500 characters of the response string.
//            },
//            Response.ErrorListener {  })

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i(TAG, "onRequestPermissionsResult: the requestCode is " + requestCode)
    }
}
