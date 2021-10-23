package com.example.weatherapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherModel(
    var id: String,
    var weather_state_name: String,
    var max_temp: String,
    var min_temp: String,
    var woeid: String,
    var title: String,
    var distance: String
)

