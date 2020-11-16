package com.example.httpweather.bean

data class LocationBean(
    val id: Int,
    val cityEn: String,
    val cityZh: String,
    val provinceEn: String,
    val provinceZh: String,
    val leaderEn: String,
    val leaderZh: String,
    val lat: Float,
    val lon: Float
)


data class test(
    val id: Int

)