package com.example.banquemisr.ui.screens.profileInformationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.banquemisr.R
import com.example.banquemisr.screens.signIn.PreferencesHelper
import com.example.banquemisr.ui.screens.profileScreen.color
import com.example.banquemisr.ui.screens.reusableUI.ProfileData
import com.example.banquemisr.ui.screens.reusableUI.ScreenHeader

@Preview
@ExperimentalMaterial3Api
@Composable
fun ProfileInformationScreen(navController: NavController) {
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
        val context = LocalContext.current
        val preferencesHelper = remember { PreferencesHelper(context) }
        ScreenHeader("Profile Information", onClick = {navController.popBackStack()})
        ProfileData("Full Name",preferencesHelper.getFullName())
        ProfileData("Email",preferencesHelper.getEmail())
        ProfileData("Date of Birth",preferencesHelper.getBirthDate())
        ProfileData("Country",preferencesHelper.getCountry())
        ProfileData("Bank Account",)
    }

}
