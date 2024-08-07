package com.example.banquemisr.models

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CardAPICallable {

    @POST("/api/auth/account")
    suspend fun addCard(@Body request: Card): Response<Unit>

}