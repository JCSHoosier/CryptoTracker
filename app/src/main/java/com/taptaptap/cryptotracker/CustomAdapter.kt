package com.taptaptap.cryptotracker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.taptaptap.cryptotracker.model.CoinMarketCapModel

class CustomAdapter(val coinList: List<CoinMarketCapModel>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.coinName?.text = coinList[position].name
        holder?.coinPrice?.text = coinList[position].price_usd.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val coinName = itemView.findViewById<TextView>(R.id.coinName)
        val coinPrice = itemView.findViewById<TextView>(R.id.coinPrice)
    }
}