//package com.example.banquemisr.models
//
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object SignUpAPIService {
//    private val retrofit= Retrofit.
//    Builder()
//        .baseUrl("https://speedo-transfer-437e318f5416.herokuapp.com/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val callable: SignUpAPICallable by lazy{
//        retrofit.create(SignUpAPICallable::class.java)
//    }
//}


package com.example.banquemisr.models

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SignUpAPIService {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://speedo-transfer-437e318f5416.herokuapp.com/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val callable: SignUpAPICallable by lazy {
        retrofit.create(SignUpAPICallable::class.java)
    }
}
