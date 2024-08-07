package com.example.banquemisr.models

data class Card(

    val accountNumber: String,
    val balance: Int,
    val userId: Int,
    val currency: String,
    val cardNumber: String,
    val cardholderName: String,
    val cvv: Int,
    val expirationDate: String

)
