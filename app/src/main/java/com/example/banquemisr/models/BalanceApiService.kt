package com.example.banquemisr.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BalanceApiService {
    private val retrofit= Retrofit.
    Builder()
        .baseUrl("https://speedo-transfer-437e318f5416.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val callable: BalanceAPICallable by lazy{
        retrofit.create(BalanceAPICallable::class.java)
    }
}