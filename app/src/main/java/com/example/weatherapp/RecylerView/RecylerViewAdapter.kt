package com.example.weatherapp.RecylerView

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.ActivityDetay
import com.example.weatherapp.R
import com.example.weatherapp.Str
import com.example.weatherapp.WeatherModel

class RecylerViewAdapter(list: ArrayList<WeatherModel>, data: List<Str>?, context: Context) :
    RecyclerView.Adapter<RecylerViewAdapter.MyViewHolder>() {
    var list: List<WeatherModel> = list
    init {
        RecylerViewAdapter.list = list
        RecylerViewAdapter.data = data!!
        RecylerViewAdapter.context = context
    }

    companion object {
        lateinit var list: List<WeatherModel>
        lateinit var data: List<Str>
        lateinit var context: Context

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val row_header = itemView.findViewById<TextView>(R.id.row_baslik)
        val row_distance = itemView.findViewById<TextView>(R.id.row_distance)

        fun binding(list: WeatherModel, title: String) {
            row_header.setText(title)
            row_distance.setText(((list.distance.toInt())/1000).toString()+" km")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_design_menu, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding(list.get(position), list.get(position).title)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ActivityDetay::class.java)
            intent.putExtra("title", list.get(position).title)
            intent.putExtra("woeid", list.get(position).woeid)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}