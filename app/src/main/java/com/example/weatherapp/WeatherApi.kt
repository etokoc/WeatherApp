package com.example.weatherapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface WeatherApi {
    @GET("api/location/search/?")
    fun getData(@Query("lattlong") lat: String): Call<List<WeatherModel>>

    @GET("/api/location/{woeid}/")
     fun queryWeather(
        @Path("woeid") woeid: String
    ): Call<Example>

}