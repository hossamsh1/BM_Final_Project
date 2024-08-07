package com.example.banquemisr.ui.screens.reusableUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ProfileData(headLine: String? = null, supportingText: String? = null) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {

        // Handle null values with default text
        Text(
            text = headLine ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(5.dp)
        )

        Text(
            text = supportingText ?: "",
            color = Color.Gray,
            fontSize = 17.sp,
            modifier = Modifier.padding(5.dp)
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}
