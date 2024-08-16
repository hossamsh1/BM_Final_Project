package com.example.bm_app.approutes


import DynamicLoadingScreen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.banquemisr.models.SignUpViewModel
import com.example.banquemisr.screens.HomeScreen
import com.example.banquemisr.screens.addCard.AccountConnectedScreen
import com.example.banquemisr.screens.addCard.AddCardScreen
import com.example.banquemisr.screens.addCard.OTPScreen
import com.example.banquemisr.screens.addCard.SelectCurrencyScreen
import com.example.banquemisr.screens.navigation.BottomNavigationBar
import com.example.banquemisr.screens.signIn.SignInScreen
import com.example.banquemisr.screens.signUp.SignUpScreen
import com.example.banquemisr.screens.signUp.SignUpScreen2
import com.example.banquemisr.screens.splash.SplashScreen
import com.example.banquemisr.ui.screens.ChangePasswordScreen
import com.example.banquemisr.ui.screens.EditProfileScreen
import com.example.banquemisr.ui.screens.SettingScreen
import com.example.banquemisr.ui.screens.moreScreen.FavoritesScreen
import com.example.banquemisr.ui.screens.moreScreen.MoreScreen
import com.example.banquemisr.ui.screens.notification.NotificationScreen
import com.example.banquemisr.ui.screens.profileInformationScreen.ProfileInformationScreen
import com.example.banquemisr.ui.screens.profileScreen.ProfileScreen
import com.example.banquemisr.ui.screens.transactionScreen.SuccessfulTransactionScreenRote
import com.example.banquemisr.ui.screens.transactionScreen.TransActionScreen
import com.example.banquemisr.ui.screens.transferScreen.TransferAmountScreen
import com.example.banquemisr.ui.screens.transferScreen.TransferConfirmationScreen
import com.example.banquemisr.ui.screens.transferScreen.TransferPaymentScreen
import com.example.bm_app.approutes.AppRoutes.ACCOUNT_CONNECTED_ROUTE
import com.example.bm_app.approutes.AppRoutes.ADD_CARD_ROUTE
import com.example.bm_app.approutes.AppRoutes.CARD_ROUTE
import com.example.bm_app.approutes.AppRoutes.CONNECTING_SCREEN_ROUTE
import com.example.bm_app.approutes.AppRoutes.Change_Password_Route
import com.example.bm_app.approutes.AppRoutes.Edit_Profile_Route
import com.example.bm_app.approutes.AppRoutes.FAVORITSCREEN
import com.example.bm_app.approutes.AppRoutes.HOME_ROUTE
import com.example.bm_app.approutes.AppRoutes.MORE_ROUTE
import com.example.bm_app.approutes.AppRoutes.OTP_ROUTE
import com.example.bm_app.approutes.AppRoutes.Profile_Information_Route
import com.example.bm_app.approutes.AppRoutes.Profile_Rute
import com.example.bm_app.approutes.AppRoutes.SELECT_CURRENCY_ROUTE
import com.example.bm_app.approutes.AppRoutes.SIGNIN_ROUTE
import com.example.bm_app.approutes.AppRoutes.SIGN_UP_COMPLETE_ROUTE
import com.example.bm_app.approutes.AppRoutes.SPLASH_ROUTE
import com.example.bm_app.approutes.AppRoutes.SUCCESSFUL_TRANSACTIONSCREEN_ROUTE
import com.example.bm_app.approutes.AppRoutes.Setting_Route
import com.example.bm_app.approutes.AppRoutes.TRANSACTION_ROUTE
import com.example.bm_app.approutes.AppRoutes.TRANSFERAMOUNT_ROUTE


object AppRoutes {
    const val SIGN_UP_ROUTE = "signUp"
    const val SIGN_UP_COMPLETE_ROUTE = "SignUpComplete"
    const val SIGNIN_ROUTE = "signIn"
    const val SPLASH_ROUTE = "splash"
    const val HOME_ROUTE = "home"
    const val TRANSFERAMOUNT_ROUTE = "transferAmount"
    const val TRANSFERCONFIRMATION_ROUTE = "transferConfirmation"
    const val TRANSFERPAYMENT_ROUTE = "transferPayment"
    const val TRANSACTION_ROUTE = "transaction"
    const val CARD_ROUTE = "card"
    const val MORE_ROUTE = "more"
    const val SUCCESSFUL_TRANSACTIONSCREEN_ROUTE = "successfulTransaction"

    const val ADD_CARD_ROUTE = "addCard"
    const val ACCOUNT_CONNECTED_ROUTE = "accountConnected"
    const val CONNECTING_SCREEN_ROUTE = "connectingScreen"
    const val OTP_ROUTE = "otp"
    const val SELECT_CURRENCY_ROUTE = "selectCurrency"

    const val Profile_Rute = "profile"
    const val Profile_Information_Route = "ProfileInformation"
    const val Setting_Route = "Setting"
    const val Change_Password_Route ="ChangePassword"
    const val Edit_Profile_Route ="editProfile"
    // const val FIELD_TRANSACTION_ROUTE = "fieldTransaction"

    const val NOTIFICATION_ROUTE = "notification"
    const val FAVORITSCREEN = "favorit"

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    // val addCardViewModel: AddCardViewModel = viewModel()
    val signUpViewModel: SignUpViewModel = viewModel()
    val bottomBarRoutes= listOf(
        AppRoutes.HOME_ROUTE,
        AppRoutes.TRANSFERAMOUNT_ROUTE,
        AppRoutes.TRANSACTION_ROUTE,
        AppRoutes.CARD_ROUTE,
        AppRoutes.MORE_ROUTE
    )

    val currentRoute=currentRoute(navController = navController)
    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes)
            BottomNavigationBar(navController = navController) // Pass it as a composable lambda
        }
    ) { _ ->
        NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
            composable(route = AppRoutes.SIGN_UP_ROUTE) { SignUpScreen(navController) }
            composable(
                route = "${SIGN_UP_COMPLETE_ROUTE}/{fullName}/{email}/{password}",
                arguments = listOf(
                    navArgument("fullName") { type = NavType.StringType },
                    navArgument("email") { type = NavType.StringType },
                    navArgument("password") { type = NavType.StringType }
                )
            ) {
                val fullName = it.arguments?.getString("fullName") ?: ""
                val email = it.arguments?.getString("email") ?: ""
                val password = it.arguments?.getString("password") ?: ""

                SignUpScreen2(navController, fullName, email, password)
            }

            composable(route = ADD_CARD_ROUTE) {
                AddCardScreen(navController = navController)
            }
            composable(route = ACCOUNT_CONNECTED_ROUTE) {
                AccountConnectedScreen(navController)
            }
            composable(route = OTP_ROUTE) {
                OTPScreen(navController)
            }
            composable(route = CONNECTING_SCREEN_ROUTE) {
                DynamicLoadingScreen(navController)
            }
            composable(route = SELECT_CURRENCY_ROUTE) {
                SelectCurrencyScreen(navController)
            }
            composable(route = SPLASH_ROUTE) {
                SplashScreen(navController)
            }
            composable(route = HOME_ROUTE) {
                HomeScreen(navController, viewModel = viewModel())
            }
            composable(route = SUCCESSFUL_TRANSACTIONSCREEN_ROUTE) {
                SuccessfulTransactionScreenRote(navController = navController)
            }
            composable(route = TRANSFERAMOUNT_ROUTE) { TransferAmountScreen(navController) }
            composable(route = TRANSACTION_ROUTE) {
                TransActionScreen(navController = navController)
            }
            composable(route = CARD_ROUTE) {
                SelectCurrencyScreen(navController)
            }
            composable(route = MORE_ROUTE) {
                MoreScreen(navController)
            }

            composable(route = Profile_Rute) {
                ProfileScreen(navController = navController)
            }
            composable(route = Profile_Information_Route){
                ProfileInformationScreen(navController)
            }
            composable(route = Setting_Route) {
                SettingScreen(navController = navController)
            }

            composable(route = SIGNIN_ROUTE) { SignInScreen(navController) }

            composable(
                route = "${AppRoutes.TRANSFERCONFIRMATION_ROUTE}/{amount}/{recipientName}/{recipientAccount}/{amountEgp}",
                arguments = listOf(
                    navArgument("amount") { type = NavType.StringType },
                    navArgument("recipientName") { type = NavType.StringType },
                    navArgument("recipientAccount") { type = NavType.StringType },
                    navArgument("amountEgp") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val amount = backStackEntry.arguments?.getString("amount") ?: ""
                val recipientName = backStackEntry.arguments?.getString("recipientName") ?: ""
                val recipientAccount = backStackEntry.arguments?.getString("recipientAccount") ?: ""
                val amountEgp = backStackEntry.arguments?.getString("amountEgp") ?: ""
                TransferConfirmationScreen(
                    navController,
                    amount.toDouble(),
                    recipientName,
                    recipientAccount,
                    amountEgp.toDouble()
                )
            }

            composable(
                route = "${AppRoutes.TRANSFERPAYMENT_ROUTE}/{amount}/{recipientName}/{recipientAccount}/{amountEgp}",
                arguments = listOf(
                    navArgument("amount") { type = NavType.StringType },
                    navArgument("recipientName") { type = NavType.StringType },
                    navArgument("recipientAccount") { type = NavType.StringType },
                    navArgument("amountEgp") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val amount = backStackEntry.arguments?.getString("amount") ?: ""
                val recipientName = backStackEntry.arguments?.getString("recipientName") ?: ""
                val recipientAccount = backStackEntry.arguments?.getString("recipientAccount") ?: ""
                val amountEgp = backStackEntry.arguments?.getString("amountEgp") ?: ""
                TransferPaymentScreen(
                    navController,
                    amount.toDouble(),
                    recipientName,
                    recipientAccount,
                    amountEgp.toDouble()
                )
            }
            composable(route = AppRoutes.NOTIFICATION_ROUTE) {
                NotificationScreen(navController)
            }
            composable(route = Change_Password_Route){
                ChangePasswordScreen(navController = navController)
            }
            composable(route = FAVORITSCREEN) {
                FavoritesScreen(navController)
            }
            composable(route = Edit_Profile_Route){
                EditProfileScreen(navController = navController)
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

