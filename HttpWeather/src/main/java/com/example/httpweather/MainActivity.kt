package com.example.httpweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        httpWeather_activityMain_recyclerviewTest.layoutManager = LinearLayoutManager(this)
        httpWeather_activityMain_recyclerviewTest.adapter = adapterTest()
    }
}
