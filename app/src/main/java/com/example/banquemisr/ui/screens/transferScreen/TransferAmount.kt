package com.example.banquemisr.ui.screens.transferScreen

import com.example.banquemisr.constants.Constants.AMOUNT_EGP
import java.net.URL

data class TransferAmount(
    val usdBalance: Double,
    val transferAmountUSD: Double,
    val transferAmountEGP: Double,
    val toName: String,
    val toAccount: Int,
    val currency: String
)
