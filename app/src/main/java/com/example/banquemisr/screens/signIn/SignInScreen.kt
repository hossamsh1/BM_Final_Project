package com.example.banquemisr.screens.signIn

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.banquemisr.models.SignInViewModel
import com.example.bm_app.approutes.AppRoutes.HOME_ROUTE
import com.example.bm_app.approutes.AppRoutes.SIGN_UP_ROUTE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController, modifier: Modifier = Modifier) {
    val context= LocalContext.current
    val preferencesHelper = PreferencesHelper(context)
    var email = remember { mutableStateOf<String>(preferencesHelper.getEmail() ?: "") }
    var password = remember { mutableStateOf<String>(preferencesHelper.getPassword() ?: "") }


    Scaffold(

        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.Gredient),
                    titleContentColor = colorResource(id = R.color.Gray_G900)
                ),

                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Sign In",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.W500
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        SignIn(innerPadding, navController, email, password,preferencesHelper)

    }
}


@Composable

fun SignIn(
    innerPadding: PaddingValues,
    navController: NavController,
    email: MutableState<String>,
    password: MutableState<String>,
    preferencesHelper: PreferencesHelper,
    modifier: Modifier = Modifier
) {
    val viewModel=SignInViewModel()
    val signInSuccess by viewModel.signInSuccess.collectAsState()
    val scrollState = rememberScrollState()
    var background = Brush.verticalGradient(
        listOf(colorResource(id = R.color.Greadient2), colorResource(id = R.color.Gredient)),
        startY = 2000f,
        endY = 0f
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()

            .background(background)
            .verticalScroll(scrollState).padding(innerPadding)
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Box(
            contentAlignment = Alignment.Center, modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Speedo Transfer",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.W600
            )

        }
        Spacer(modifier = Modifier.height(60.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {



            TextFields(string1 = "Email", string2 = "Enter your email", R.drawable.email,email,KeyboardOptions(keyboardType = KeyboardType.Email),false)
            TextFields(
                string1 = "Password",
                string2 = "Enter your password",
                R.drawable.eye,password,KeyboardOptions(keyboardType = KeyboardType.Password),true
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    viewModel.signIn(
                        email.value,
                        password.value
                    )
                    if (signInSuccess == true) {
                        preferencesHelper.saveCredentialsSignIn(
                            email.value,
                            password.value,
                        )
                        navController.navigate("$HOME_ROUTE")
                    }
                    navController.navigate("$HOME_ROUTE")
                },

                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.Beige)
                ),
                shape = RoundedCornerShape(9.dp),
                enabled = email.value.isNotEmpty() && password.value.isNotEmpty()

            ) {
                Text(
                    text = "Sign In",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500
                )

            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account?",
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.Gray_G70)
                )
                TextButton(onClick = { preferencesHelper.clearCredentialsSignIn()
                    navController.navigate("$SIGN_UP_ROUTE") }) {
                    Text(
                        text = "Sign Up",
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.Beige),
                        textDecoration = TextDecoration.Underline
                    )

                }
            }




        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFields(string1: String, string2: String, icon: Int, state: MutableState<String>,keyboard:KeyboardOptions,isPassword:Boolean, modifier: Modifier = Modifier) {

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
        Text(
            text = string1,
            modifier = modifier.padding(horizontal = 32.dp),
            fontSize = 16.sp,
            color = colorResource(id = R.color.Gray_G700),
            fontWeight = FontWeight.W400,

            )
        var eyeClicked by remember {
            mutableStateOf(false)
        }
        OutlinedTextField(
            value = state.value,
            onValueChange = { state.value = it },
            placeholder = { Text(text = string2, color = colorResource(id = R.color.Gray_G70)) },
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = icon),
                    contentDescription = null,
                    modifier
                        .align(Alignment.End).clickable { eyeClicked = !eyeClicked }

                )
            },visualTransformation = if (isPassword&&!eyeClicked) PasswordVisualTransformation() else VisualTransformation.None,

            keyboardOptions =keyboard,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 10.dp)
                .background(color = Color.White, shape = RoundedCornerShape(9.dp)),
            shape = RoundedCornerShape(9.dp)


        )
    }
}


@Preview
@Composable
fun OverAll5() {
    SignInScreen(rememberNavController())
}