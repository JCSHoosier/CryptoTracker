package com.taptaptap.cryptotracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taptaptap.cryptotracker.api.CoinMarketCap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TAG", "inside oncreate")
        testCall()
    }

    private fun testCall(){
        var coinMarketCap : CoinMarketCap = Retrofit.Builder()
                .baseUrl("https://api.coinmarketcap.com/v1/ticker/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CoinMarketCap::class.java)

        coinMarketCap.cryptoValue()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
            Log.i(TAG, response.toString())
        }
    }
}
