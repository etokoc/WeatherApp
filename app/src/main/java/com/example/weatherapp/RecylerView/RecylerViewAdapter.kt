package com.example.weatherapp.RecylerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.WeatherModel

class RecylerViewAdapter(list: ArrayList<WeatherModel>, title: String) :
    RecyclerView.Adapter<RecylerViewAdapter.MyViewHolder>() {
    var list: List<WeatherModel> = list


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val row_header = itemView.findViewById<TextView>(R.id.row_baslik)
        val row_max = itemView.findViewById<TextView>(R.id.row_max)
        val row_min = itemView.findViewById<TextView>(R.id.row_min)

        fun binding(item: WeatherModel) {
            row_header.setText("ANkara")
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
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}