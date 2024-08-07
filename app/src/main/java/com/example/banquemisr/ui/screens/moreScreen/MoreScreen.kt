package com.example.banquemisr.ui.screens.moreScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.banquemisr.ui.screens.ContactOptions
import com.example.banquemisr.ui.screens.profileScreen.color
import com.example.banquemisr.ui.screens.reusableUI.MoreField
import com.example.banquemisr.ui.screens.reusableUI.ScreenHeader
import com.example.bm_app.approutes.AppRoutes.FAVORITSCREEN
import com.example.bm_app.approutes.AppRoutes.Profile_Rute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreen(navController: NavController) {

    val background = Brush.verticalGradient(
        listOf(colorResource(id = R.color.Greadient2), colorResource(id = R.color.Gredient)),
        startY = 2000f,
        endY = 0f
    )

    val context = LocalContext.current
    var sheetstate = rememberModalBottomSheetState()
    var isSheetOpen by remember { mutableStateOf(false) }
    if (isSheetOpen) {

        ModalBottomSheet(
            onDismissRequest = { isSheetOpen = !isSheetOpen },
            sheetState = sheetstate,
            dragHandle = { },
        ) {
            ContactOptions(onDismiss = { isSheetOpen = !isSheetOpen })
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        ScreenHeader("More", onClick = {navController.popBackStack()})
        MoreField(
            "Transfer from website",
            leadingIcon = R.drawable.web_site,
        )
        MoreField(
            "Favourites",
            leadingIcon = R.drawable.star,
            onClick = { navController.navigate("$FAVORITSCREEN") }
        )
        MoreField(
            "Profile",
            leadingIcon = R.drawable.user,
            onClick = { navController.navigate("$Profile_Rute") }
        )
        MoreField(
            "Help",
            leadingIcon = R.drawable.question
            ,onClick = { isSheetOpen = true }
        )
        MoreField(
            "Logout",
            leadingIcon = R.drawable.log_out
        )
    }

}



@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyComposablePreview() {
    val navController = rememberNavController()
    MoreScreen(navController = navController)
}