package com.example.weatherapp.RecylerView

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.ActivityDetay
import com.example.weatherapp.R
import com.example.weatherapp.WeatherModel

class RecylerViewAdapter(list: ArrayList<WeatherModel>, title: String, context: Context) :
    RecyclerView.Adapter<RecylerViewAdapter.MyViewHolder>() {
    var list: List<WeatherModel> = list

    init {
        RecylerViewAdapter.title = title
        RecylerViewAdapter.list = list
        RecylerViewAdapter.context = context
    }

    companion object {
        lateinit var title: String
        lateinit var list: List<WeatherModel>
        lateinit var context: Context

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val row_header = itemView.findViewById<TextView>(R.id.row_baslik)
        val row_max = itemView.findViewById<TextView>(R.id.row_max)
        val row_min = itemView.findViewById<TextView>(R.id.row_min)

        fun binding(item: WeatherModel) {
            row_header.setText("" + item.title)
            row_max.setText(item.max_temp)
            row_min.setText(item.min_temp)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_design_menu, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding(list.get(position))
        holder.itemView.setOnClickListener {
            Toast.makeText(context,"Tıklandı"+" "+ list.get(position).title+" "+list.get(position).max_temp,Toast.LENGTH_SHORT).show()
            val intent = Intent(it.context,ActivityDetay::class.java)
            intent.putExtra("title",list.get(position).title)
            intent.putExtra("temp",list.get(position).max_temp)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}