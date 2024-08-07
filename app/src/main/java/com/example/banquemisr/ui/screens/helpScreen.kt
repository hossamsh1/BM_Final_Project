package com.example.banquemisr.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.banquemisr.R

@Preview
@Composable
fun ContactOptions(onDismiss: () -> Unit) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val context = LocalContext.current

                        ContactCard(
                            icon = R.drawable.email,
                            text = "Send Email",
                            onClick = {
                                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:example@example.com")
                                    putExtra(Intent.EXTRA_SUBJECT, "Subject")
                                    putExtra(Intent.EXTRA_TEXT, "Body")
                                }
                                context.startActivity(emailIntent)
                            }
                        )

                        ContactCard(
                            icon = R.drawable.telephone, // Replace with your phone icon resource
                            text = "Call Us",
                            phoneNumber = "0000000",
                            onClick = {
                                val callIntent = Intent(Intent.ACTION_DIAL).apply {
                                    data =
                                        Uri.parse("tel:0000000") // Replace with actual phone number
                                }
                                context.startActivity(callIntent)
                            }
                        )
                    }
                }
            }
        }


@Composable
fun ContactCard(icon: Int, text: String, phoneNumber: String? = null, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(120.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        backgroundColor = Color(0xFFF8F5F5) // Adjust the background color as needed
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color(0xFF8A1538), // Adjust the icon color as needed
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
                color = Color(0xFF8A1538) // Adjust the text color as needed
            )
            if (phoneNumber != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = phoneNumber,
                    style = MaterialTheme.typography.body2.copy(fontSize = 14.sp),
                    color = Color(0xFF8A1538) // Adjust the text color as needed
                )
            }
        }
    }
}
