package com.example.weatherapp.RecylerView

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.*

class RecylerViewAdapterDetay(list: ArrayList<Str>, context: Context) :
    RecyclerView.Adapter<RecylerViewAdapterDetay.MyViewHolder>() {
    var list: List<Str> = list
    init {
        RecylerViewAdapterDetay.list = list
        RecylerViewAdapterDetay.context = context
    }

    companion object {
        lateinit var list: List<Str>
        lateinit var data: List<Str>
        lateinit var context: Context

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val day = itemView.findViewById<TextView>(R.id.row_gun)
        val temp = itemView.findViewById<TextView>(R.id.row_theTemp)
        val max = itemView.findViewById<TextView>(R.id.row_max)
        val min = itemView.findViewById<TextView>(R.id.row_min)
        val durum = itemView.findViewById<TextView>(R.id.row_durum)

        fun binding(list: Str) {
            day.setText(list.applicableDate)
            temp.setText(""+list.theTemp)
            max.setText(""+list.maxTemp)
            min.setText(""+list.minTemp)
            durum.setText(list.weatherStateAbbr)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_design_detay, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding(list.get(position))

    }

    override fun getItemCount(): Int {
        return list.count()
    }
}