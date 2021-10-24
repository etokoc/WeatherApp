package com.example.weatherapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Example {
    @SerializedName("consolidated_weather")
    @Expose
    var consolidatedWeather: List<Str>? = null
}