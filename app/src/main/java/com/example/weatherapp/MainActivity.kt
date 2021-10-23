package com.example.weatherapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://www.metaweather.com"
    private var list: ArrayList<WeatherModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // loadData()
        getPermission()
        var fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
        }
        fusedLocationClient.lastLocation.addOnCompleteListener {
            var location = it.result
            if (location != null){
              var txt1 = findViewById<TextView>(R.id.txt)
              var txt2 = findViewById<TextView>(R.id.textView)
               txt1.setText("altitude: "+location.latitude)
               txt2.setText("longtude: "+location.longitude)
            }
        }
        getWoeid()

    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun getPermission() {
        try {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    101
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherApi::class.java)
        val call = service.queryWeather("2343732", "2021/10/22")

        call.enqueue(object : Callback<List<WeatherModel>> {
            override fun onResponse(
                call: Call<List<WeatherModel>>,
                response: Response<List<WeatherModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        list = ArrayList(it)
                        Toast.makeText(this@MainActivity, "" + list!!.get(0), Toast.LENGTH_SHORT)
                            .show()
                        for (x in list!!) {
                            Log.i(
                                "response",
                                x.id + " " + x.weather_state_name + " " + x.max_temp + " " + x.min_temp
                            )
                        }
                    }

                    if (response.body() == null) {
                        Log.i("response", "response boş: ")
                    }


                } else
                    Log.i("response", "Cevap Yok!!: " + (response.body()?.get(0)))
            }

            override fun onFailure(call: Call<List<WeatherModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    private fun getWoeid() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherApi::class.java)
        val call = service.getData("30,0")

        call.enqueue(object : Callback<List<WeatherModel>> {
            override fun onResponse(
                call: Call<List<WeatherModel>>,
                response: Response<List<WeatherModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        list = ArrayList(it)
                        Toast.makeText(this@MainActivity, "" + list!!.get(0), Toast.LENGTH_SHORT)
                            .show()
                        for (x in list!!) {
                            Log.i(
                                "response",
                                x.woeid + "" + x.title
                            )
                        }
                    }

                    if (response.body() == null) {
                        Log.i("response", "response boş: ")
                    }


                } else
                    Log.i("response", "Cevap Yok!!: " + (response.body()?.get(0)))
            }

            override fun onFailure(call: Call<List<WeatherModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}
