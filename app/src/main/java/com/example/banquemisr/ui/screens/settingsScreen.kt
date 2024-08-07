package com.example.banquemisr.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.banquemisr.ui.screens.profileScreen.color
import com.example.banquemisr.ui.screens.reusableUI.ScreenField
import com.example.banquemisr.ui.screens.reusableUI.ScreenHeader
import com.example.bm_app.approutes.AppRoutes.Change_Password_Route
import com.example.bm_app.approutes.AppRoutes.Edit_Profile_Route
import com.example.bm_app.approutes.AppRoutes.Profile_Rute

@Composable
fun SettingScreen(navController: NavController) {
    val background = Brush.verticalGradient(
        listOf(colorResource(id = R.color.Greadient2), colorResource(id = R.color.Gredient)),
        startY = 2000f,
        endY = 0f
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        ScreenHeader("Setting", onClick = { navController.popBackStack() })

        ScreenField("Change Password", "Change Password", leadingIcon = R.drawable.lock_lines, onClick = {navController.navigate("$Change_Password_Route")})
        ScreenField("Edit Profile","Edit your information", leadingIcon = R.drawable.edit, onClick = {navController.navigate("$Edit_Profile_Route")})
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyComposablePreview() {
    val navController = rememberNavController()
    SettingScreen(navController = navController)
}