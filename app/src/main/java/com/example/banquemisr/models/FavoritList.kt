package com.example.banquemisr.models

import com.google.gson.annotations.SerializedName

data class Favorite(
    val id: Int,
    val fullName: String,
    val accountNumber: String,
    val addedAt: String
)

data class AddFavoriteRequest(
    val fullName: String,
    val accountNumber: String
)
