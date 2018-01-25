package com.taptaptap.cryptotracker.api

import com.taptaptap.cryptotracker.model.CoinMarketCapModel
import io.reactivex.Observable
import retrofit2.http.GET

interface CoinMarketCap {
    @GET("?limit=10")
    fun cryptoValue() : Observable<List<CoinMarketCapModel>>
}