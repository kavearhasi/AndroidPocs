package com.example.workmanageronetime.data.remote

data class BitcoinRateDTO(
    val `24h_high`: String,
    val `24h_low`: String,
    val `24h_price_change`: String,
    val `24h_price_change_percent`: String,
    val `24h_volume`: String,
    val price: String,
    val timestamp: Long
)