package com.example.banquemisr.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.bm_app.approutes.AppRoutes.SIGN_UP_ROUTE
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.Beige))
        ) {

            Text(
                text = "Speedo Transfer",
                fontSize = 32.sp,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                color = Color.White
            )
        }
    }

    // Navigate to the home screen after a delay
    LaunchedEffect(Unit) {
       delay(2000) // 3 seconds delay
        navController.navigate("$SIGN_UP_ROUTE")
    }
}

@Preview
@Composable
fun splashPreview(){
    SplashScreen(navController = rememberNavController())
}
