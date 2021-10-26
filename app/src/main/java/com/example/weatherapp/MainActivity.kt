package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.RecylerView.RecylerViewAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var fusedLocationClient: FusedLocationProviderClient
    private val BASE_URL = "https://www.metaweather.com"

    private lateinit var cs: String
    var list: ArrayList<WeatherModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        setContentView(R.layout.activity_main)

        getPermission()
        getLocation()

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
//                            val recylerViewAdapter = RecylerViewAdapter(
//                                list!!,
//                                x.title + "", x.max_temp, x.min_temp,
//                                this@MainActivity.applicationContext
                            Log.i("gola", ": " + list!!.get(0).title)
//                            )
                            loadData(x.woeid)
                        }
                    }
                }
                Log.i("gandalf", "woeid: " + list!!.get(0).woeid)
            }

            override fun onFailure(call: Call<List<WeatherModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    private var data: List<Str>? = null
    var depo: Str? = Str()
    fun loadData(woeidID: String): String {
        lateinit var call: Call<Example>
        lateinit var service: WeatherApi
        lateinit var retrofit: Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(WeatherApi::class.java)
        call = service.queryWeather(woeidID)
        call.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                if (response.isSuccessful) {
                    data = response.body()!!.consolidatedWeather
                    var recylerView: RecyclerView = findViewById(R.id.recyclerView)
                    recylerView.adapter = RecylerViewAdapter(list!!, data, this@MainActivity)
                    recylerView.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.i("response", "onFailure: " + t.message)
            }

        })
        Log.i("depo", "loadData: " + depo!!.theTemp)
        return depo!!.weatherStateName.toString()
    }
}
