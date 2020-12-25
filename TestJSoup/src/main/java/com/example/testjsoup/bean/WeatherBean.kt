package com.example.testjsoup.bean

import java.util.*

data class WeatherBean(var results: List<Results>) {

    data class Results(var last_update:String,
                       var location: Location,
                       var now: Now)

    data class Location(var id: String, var name: String,
                        var country: String, var path: String,
                        var timeZone: String,
                        var timeZone_offSet: String)

    data class Now(var text: String,
                   var code: String,
                   var temperature: String)

}