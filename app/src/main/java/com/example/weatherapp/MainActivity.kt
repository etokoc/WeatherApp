package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.RecylerView.RecylerViewAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var fusedLocationClient: FusedLocationProviderClient
    private val BASE_URL = "https://www.metaweather.com"

    private lateinit var cs: String
    var location: Location = Location("")
    var list: ArrayList<WeatherModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getPermission()
        //val data  = CoordinateSystem(this.applicationContext)
        getLocation()

//        Log.i("akif", "onCreate: " +)
    }

    fun getLocation() {
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(this.applicationContext)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 123)
        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                cs = location.latitude.toString() + "," + location.longitude
                Log.i(
                    "veri",
                    "fonksiyon içi: " + location.latitude.toString() + "," + location.longitude
                )
                Log.i("cs", "cs: " + cs)
                getWoeid(cs)
            }
        }
        Log.i("gandalf", "getLocation: " + location.latitude.toString() + "," + location.longitude)
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


//    fun loadData(woeidID: String) {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val service = retrofit.create(WeatherApi::class.java)
//        val call = service.queryWeather(woeidID, "2021/10/22")
//
//        call.enqueue(object : Callback<List<WeatherModel>> {
//            override fun onResponse(
//                call: Call<List<WeatherModel>>,
//                response: Response<List<WeatherModel>>
//            ) {
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        list = ArrayList(it)
//                        for (x in list!!) {
//
//
//                            Log.i(
//                                "response",
//                                x.id + " " + x.weather_state_name + " " + x.max_temp + " " + x.min_temp
//                            )
//                        }
//                    }
//                }
//
//            }
//
//            override fun onFailure(call: Call<List<WeatherModel>>, t: Throwable) {
//                t.printStackTrace()
//            }
//
//        })
//    }

    fun getWoeid(woeidD: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherApi::class.java)
        val call = service.getData(woeidD)

        call.enqueue(object : Callback<List<WeatherModel>> {
            override fun onResponse(
                call: Call<List<WeatherModel>>,
                response: Response<List<WeatherModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        list = ArrayList(it)
                        for (x in list!!) {
                            val recylerView: RecyclerView = findViewById(R.id.recyclerView)
                            val recylerViewAdapter = RecylerViewAdapter(
                                list!!,
                                x.title + "",
                                this@MainActivity.applicationContext
                            )
                            recylerView.adapter = recylerViewAdapter
                            recylerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        }
                        Log.i(
                            "woeid alma",
                            list!!.get(0).title + " woeid:" + list!!.get(0).woeid + " distance: " + list!!.get(0).distance+" max:"+list!!.get(0).max_temp
                        )
//                        loadData(list!!.get(0).woeid)
                    }
                }
                Log.i("gandalf", "woeid: " + list!!.get(0).woeid)
            }

            override fun onFailure(call: Call<List<WeatherModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}
