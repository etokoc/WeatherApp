package com.example.weatherapp

import android.content.ContentProviderOperation.newCall
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://www.metaweather.com"
    private var list: ArrayList<WeatherModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()


    }


    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherApi::class.java)
        val call = service.queryWeather("44418","2013/4/27")
                call.enqueue(object : Callback<List<WeatherModel>> {
            override fun onResponse(
                call: Call<List<WeatherModel>>,
                response: Response<List<WeatherModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        list = ArrayList(it)
                        Toast.makeText(this@MainActivity,""+list!!.get(0),Toast.LENGTH_SHORT).show()
                        for (x in list!!) {
                            Log.i(
                                "response", x.id+" "+x.weather_state_name+" "+x.max_temp+" "+x.min_temp
                            )
                        }
                    }

                    if (response.body() == null)
                    {
                        Log.i("response", "response boş: ")
                    }


                }else
                    Log.i("response", "Cevap Yok!!: "+ (response.body()?.get(0)))
            }

            override fun onFailure(call: Call<List<WeatherModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}
