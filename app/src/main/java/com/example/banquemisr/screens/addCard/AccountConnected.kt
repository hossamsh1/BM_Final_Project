package com.example.banquemisr.screens.addCard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.bm_app.approutes.AppRoutes.HOME_ROUTE
import com.example.bm_app.approutes.AppRoutes.SELECT_CURRENCY_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountConnectedScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Bank Card OTP",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.W500
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        AccountConnectedContent(innerPadding,navController)
    }
}

@Composable
fun AccountConnectedContent(innerPadding: PaddingValues,navController: NavController) {
    var background = Brush.verticalGradient(
        listOf(colorResource(id = R.color.Greadient2), colorResource(id = R.color.Gredient)),
        startY = 2000f,
        endY = 0f
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(id = R.drawable.success), // replace with your icon resource
            contentDescription = null,
            modifier =Modifier.size(120.dp)


        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Account Connected\nSuccessfully!",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Feel free to connect another account\nat the same time.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.Gray_G700),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = { navController.navigate("$SELECT_CURRENCY_ROUTE") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.Beige) // replace with your color
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(55.dp),
            shape = RoundedCornerShape(9.dp),
        ) {
            Text(text = "Connect another account", color = Color.White,fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = { navController.navigate("$HOME_ROUTE")},
            border = BorderStroke(1.dp, colorResource(id = R.color.Beige)), // replace with your color
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(55.dp),
            shape = RoundedCornerShape(9.dp),
        ) {
            Text(text = "Back to home", color = colorResource(id = R.color.Beige), fontSize = 16.sp) // replace with your color
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountConnectedScreen() {
    AccountConnectedScreen(navController = rememberNavController())
}
