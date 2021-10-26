package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiConnect(var woeidID: String) {
    private val BASE_URL = "https://www.metaweather.com"
    private var mData: Int? = null
    private var data: List<Str>? = null


}
