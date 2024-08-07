package com.example.banquemisr.models


data class SignUpRequest(
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val username: String,
    val password: String,
    val confirmPassword: String,
    val gender: String,
    val country: String,
    val birthDate: String

)
data class SignUpResponse(
    val userId: String,
    val fullName:String,
    val email:String,
    val phoneNumber:String,
    val gender:String,
    val birthDate:String,
    val username: String
)

