package com.example.banquemisr.ui.screens.transferScreen

data class TransferConfirmation(
    val TransferAmountUSD: Double,
    val TransferAmountEGP: Double,
    val userName: String,
    val userAccount: Int,
    val recipientName: String,
    val recipientAccount: Int,
)
