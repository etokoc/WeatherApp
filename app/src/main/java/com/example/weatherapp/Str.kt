package com.example.weatherapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Str {
    @SerializedName("id")
    @Expose
    var id: Long? = null

    @SerializedName("weather_state_name")
    @Expose
    var weatherStateName: String? = null

    @SerializedName("weather_state_abbr")
    @Expose
    var weatherStateAbbr: String? = null

    @SerializedName("created")
    @Expose
    var created: String? = null

    @SerializedName("applicable_date")
    @Expose
    var applicableDate: String? = null

    @SerializedName("min_temp")
    @Expose
    var minTemp: Double? = null

    @SerializedName("max_temp")
    @Expose
    var maxTemp: Double? = null

    @SerializedName("the_temp")
    @Expose
    var theTemp: Double? = null


    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null

}