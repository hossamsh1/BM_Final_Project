package com.example.banquemisr.ui.screens.transferScreen

data class TransferPayment (
    val toName: String,
    val toAccount: Int,
    val userName: String,
    val userAccount: Int,
    val amountEGP: Double
)