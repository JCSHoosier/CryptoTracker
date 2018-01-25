package com.taptaptap.cryptotracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.taptaptap.cryptotracker.api.CoinMarketCap
import com.taptaptap.cryptotracker.model.CoinMarketCapModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mRecyclerView : RecyclerView = findViewById(R.id.coinList)

        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val coinMarketCapBuilder : CoinMarketCap = Retrofit.Builder()
                .baseUrl("https://api.coinmarketcap.com/v1/ticker/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CoinMarketCap::class.java)

        val observable = coinMarketCapBuilder.cryptoValue()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())

        observable.subscribe( object: Observer<List<CoinMarketCapModel>> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(coinList: List<CoinMarketCapModel>) {
                val coins = ArrayList<CoinMarketCapModel>()
                coinList.mapTo(coins) {
                    CoinMarketCapModel(
                            id = it.id,
                            name = it.name,
                            symbol = it.symbol,
                            rank = it.rank,
                            price_usd = it.price_usd,
                            percent_change_1h = it.percent_change_1h,
                            percent_change_7d = it.percent_change_7d,
                            percent_change_24h = it.percent_change_24h
                    )
                }

                val adapter = CustomAdapter(coins)
                mRecyclerView.adapter = adapter
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        } )

    }
}
