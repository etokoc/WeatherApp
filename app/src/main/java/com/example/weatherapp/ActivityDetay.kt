package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.weatherapp.databinding.ActivityDetayBinding

class ActivityDetay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding: ActivityDetayBinding
        binding = ActivityDetayBinding.inflate(LayoutInflater.from(this))
        var view: View = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        binding.detayTxtTitle.setText(intent.extras!!.get("title").toString())
        binding.detayTxtMax.setText(intent.extras!!.get("temp").toString())

    }
}