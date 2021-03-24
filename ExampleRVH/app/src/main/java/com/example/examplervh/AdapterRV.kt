package com.example.examplervh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class AdapterRV(
    val listObj:ArrayList<String>
): RecyclerView.Adapter<AdapterRV.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRV.ViewHolder {
        val v=LayoutInflater.from(parent.context)
            .inflate(R.layout.rvh,parent,false)
            return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AdapterRV.ViewHolder, position: Int) {
        holder.tv.text=listObj[position]
    }

    override fun getItemCount(): Int {
        return listObj.size
    }


    inner class ViewHolder internal constructor(
        itemView: View
    ):RecyclerView.ViewHolder(itemView){
        val tv:TextView=itemView.findViewById(R.id.tv1)
    }
}