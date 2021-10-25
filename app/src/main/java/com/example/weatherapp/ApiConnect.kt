package com.example.weatherapp

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnect {
     var cevap=""
    private val BASE_URL = "https://www.metaweather.com"

    fun loadData(woeidID: String) :String{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherApi::class.java)
        val call = service.queryWeather(woeidID)

        call.enqueue(object : Callback<Example> {
            override fun onResponse(
                call: Call<Example>,
                response: Response<Example>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val detay: List<Str>? = it.consolidatedWeather
                        cevap=detay!!.get(0).theTemp.toString()
                    }
                }

            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.i("response", "onFailure: " + t.message)
            }

        })
        Log.i("woeid", "onResponse: "+cevap)

        return cevap
    }
}
