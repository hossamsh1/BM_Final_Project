package com.example.banquemisr.models

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoriteApiService {
    @GET("/api/favorites")
    fun getFavorites(@Header("Authorization") token: String): Call<List<Favorite>>

    @POST("/api/favorites")
    fun addFavorite(@Header("Authorization") token: String, @Body request: AddFavoriteRequest): Call<Void>

    @DELETE("/api/favorites/{favouriteId}")
    fun deleteFavorite(@Header("Authorization") token: String, @Path("favouriteId") id: Int): Call<Void>
}