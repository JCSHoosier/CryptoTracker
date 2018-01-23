package com.taptaptap.cryptotracker.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/*
Response Example:
    "id": "ethereum",
    "name": "Ethereum",
    "symbol": "ETH",
    "rank": "2",
    "price_usd": "997.348",
    "price_btc": "0.0926697",
    "24h_volume_usd": "3799600000.0",
    "market_cap_usd": "96891615113.0",
    "available_supply": "97149255.0",
    "total_supply": "97149255.0",
    "max_supply": null,
    "percent_change_1h": "0.98",
    "percent_change_24h": "-5.58",
    "percent_change_7d": "-22.17",
    "last_updated": "1516667651"
 */

@JsonIgnoreProperties(ignoreUnknown = true)
data class CoinMarketCapModel(
        @JsonProperty("id")
        val id : String,
        @JsonProperty("name")
        val name : String,
        @JsonProperty("symbol")
        val symbol : String,
        @JsonProperty("rank")
        val rank : Int,
        @JsonProperty("price_usd")
        val value : Double,
        @JsonProperty("percent_change_1h")
        val deltaHour : Double,
        @JsonProperty("percent_change_24h")
        val deltaDay : Double,
        @JsonProperty("percent_change_7d")
        val deltaWeek : Double
)

