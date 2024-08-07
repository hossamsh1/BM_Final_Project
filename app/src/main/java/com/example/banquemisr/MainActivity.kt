package com.example.banquemisr


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.banquemisr.ui.theme.BanqueMisrTheme
import com.example.bm_app.approutes.AppNavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BanqueMisrTheme {

                AppNavHost()

            }
        }
    }

}