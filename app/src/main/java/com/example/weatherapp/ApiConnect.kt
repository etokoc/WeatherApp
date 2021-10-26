package com.example.weatherapp

import retrofit2.converter.gson.GsonConverterFactory


class ApiConnect(var woeidID: String) {
    private val BASE_URL = "https://www.metaweather.com"
    private var mData: Int? = null
    private var data: List<WeatherTypes>? = null


}
