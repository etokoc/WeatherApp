package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.weatherapp.databinding.ActivityDetayBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class ActivityDetay : AppCompatActivity() {
    private val BASE_URL = "https://www.metaweather.com"
    lateinit var binding: ActivityDetayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetayBinding.inflate(LayoutInflater.from(this))
        var view: View = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        binding.detayTxtTitle.setText(intent.extras!!.get("title").toString())
        binding.detayTxtMax.setText(intent.extras!!.get("temp").toString())
        loadData(intent.extras!!.get("woeid").toString())
    }

    fun loadData(woeidID: String) {
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
                        for (x in detay!!) {
                            Log.i("response", "" + x.theTemp)
                        }
                        binding.detayTxtMax.setText(
                            detay.get(0).maxTemp!!.toDouble().toInt().toString()
                        )
                        binding.detayTxtMin.setText(
                            detay.get(0).minTemp!!.toDouble().toInt().toString()
                        )
                        binding.textViewtheTemp.setText(
                            detay.get(0).theTemp!!.toDouble().toInt().toString()
                        )
                        when (detay.get(0).weatherStateAbbr) {
                            "c" -> binding.imageView3.setImageResource(R.drawable.c)
                            "sn" -> binding.imageView3.setImageResource(R.drawable.sn)
                            "sl" -> binding.imageView3.setImageResource(R.drawable.sl)
                            "h" -> binding.imageView3.setImageResource(R.drawable.h)
                            "t" -> binding.imageView3.setImageResource(R.drawable.t)
                            "hr" -> binding.imageView3.setImageResource(R.drawable.hr)
                            "lr" -> binding.imageView3.setImageResource(R.drawable.lr)
                            "s" -> binding.imageView3.setImageResource(R.drawable.s)
                            "hc" -> binding.imageView3.setImageResource(R.drawable.hc)
                            "lc" -> binding.imageView3.setImageResource(R.drawable.lc)
                            else -> ""
                        }
                    }
                }

            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.i("response", "onFailure: " + t.message)
            }

        })
    }

}
