package com.example.banquemisr.ui.screens.reusableUI


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banquemisr.R

@Preview
@Composable
fun MoreField(
    headLine: String = "",
    leadingIcon: Int = 0,
    trailingIcon: Int = R.drawable.next,
    color: Color = Color.Transparent,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClick()},
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = color)
                    .size(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Text(text = headLine, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
        Icon(
            painter = painterResource(id = trailingIcon),
            contentDescription = "",
            modifier = Modifier.size(25.dp)
        )

    }
    HorizontalDivider(
        thickness = 1.dp,
        color = Color.Gray,
        modifier = Modifier.padding(horizontal = 10.dp)
    )
}

