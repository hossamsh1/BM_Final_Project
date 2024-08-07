package com.example.banquemisr.models

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInAPICallable {

    @POST("/api/auth/login")
    suspend fun signIn(@Body request: SignIn.SignInRequest): Response<Unit>




}