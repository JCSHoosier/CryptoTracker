package com.taptaptap.cryptotracker.api

import com.taptaptap.cryptotracker.model.CoinMarketCapModel
import io.reactivex.Observable
import retrofit2.http.GET

interface CoinMarketCap {
    @GET("bitcoin/?convert=USD")
    fun cryptoValue() : Observable<List<CoinMarketCapModel>>
}