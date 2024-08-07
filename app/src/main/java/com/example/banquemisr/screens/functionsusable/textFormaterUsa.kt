package com.example.banquemisr.screens.functionsusable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.*

@Composable
fun TextFormaterUSA(
    balance: Comparable<*>
    , modifier: Modifier= Modifier,
    fontSize: Int,
    color: Color, fontWeight: FontWeight ) {
    val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("en", "US"))
    val formattedBalance = balance.let { currencyFormatter.format(it) } ?: "$ "

    Text(
        text = formattedBalance,
        fontSize = fontSize.sp,
        color = color,
        fontWeight = fontWeight,
        modifier = Modifier
            .padding(top = 8.dp, bottom = 20.dp, start = 10.dp)
    )
}

@Composable
fun TextFormaterEGP(balance: Double? , modifier: Modifier= Modifier ,fontSize: Int,color: Color,fontWeight: FontWeight ) {
    val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("en", "EG"))
    val formattedBalance = balance.let { currencyFormatter.format(it) } ?: "EGP"

    Text(
        text = formattedBalance,
        fontSize = fontSize.sp,
        color = color,
        fontWeight = fontWeight,
        modifier = Modifier
            .padding(top = 8.dp, bottom = 20.dp, start = 10.dp)
    )
}