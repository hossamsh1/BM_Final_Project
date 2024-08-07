package com.example.banquemisr.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface BalanceAPICallable {
    @GET("/api/account/balance")
    fun getBalance(@Header("Authorization") token: Int): CurrentBalance
}