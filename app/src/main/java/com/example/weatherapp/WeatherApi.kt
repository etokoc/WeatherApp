package com.example.weatherapp

import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface WeatherApi {
    val lattDeger: String
    val longDeger: String
    var apiStr: String

//    fun setService(latt: String, long: String) {
//        apiStr = "api/location/search/?lattlong=36.96,-122.02"
//    }

//    @GET("api/location/search/?")
//    fun getData(@Query("min_temp") lat: String): Call<List<WeatherModel>>

    @GET("/api/location/{woeid}/{date}/")
    fun queryWeather(
        @Path("woeid") woeid: String,
        @Path("date") date: String
    ): Call<List<WeatherModel>>

}