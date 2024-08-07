package com.example.banquemisr.models


import android.util.Log
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpAPICallable {

    @POST("/api/auth/register")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>
}

