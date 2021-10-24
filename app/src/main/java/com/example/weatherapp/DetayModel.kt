package com.example.weatherapp

data class DetayModel(
    var consolidated_weather:String,
    var weather_state_name:String,
    var applicable_date:String,
    var created:String,
    var max_temp:String,
    var the_temp:String,
    var min_temp:String
)
