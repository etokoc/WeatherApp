package com.example.weatherapp.RecylerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        val ikon = itemView.findViewById<ImageView>(R.id.imageView5)

        private fun durumAlma(detay: Str): String =
            when (detay.weatherStateAbbr) {
                "c" -> "Güneşli"
                "sn" -> "Karlı"
                "sl" -> "Yağmurla Kar"
                "h" -> "Dolu"
                "t" -> "Fırtına"
                "hr" -> "Sağanak Yağışlı"
                "lr" -> "Hafif Yağmur"
                "s" -> "Güneş ve Yağmur"
                "hc" -> "Bulutlu"
                "lc" -> "Parçalı Bulutlu"
                else -> "Güneşli"
            }

        private fun ikonAlma(detay: Str): Int =
            when (detay.weatherStateAbbr) {
                "c" -> R.drawable.c
                "sn" -> R.drawable.sn
                "sl" -> R.drawable.sl
                "h" -> R.drawable.h
                "t" -> R.drawable.t
                "hr" -> R.drawable.hr
                "lr" -> R.drawable.lr
                "s" -> R.drawable.s
                "hc" -> R.drawable.hc
                "lc" -> R.drawable.lc
                else -> 0
            }

        fun binding(list: Str) {
            day.setText(list.applicableDate)
            temp.setText(list.theTemp?.toInt().toString() + "°")
            max.setText("" + list.maxTemp?.toInt().toString() + "°")
            min.setText("" + list.minTemp?.toInt().toString() + "°")
            durum.setText(durumAlma(list))
            ikon.setImageResource(ikonAlma(list))
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