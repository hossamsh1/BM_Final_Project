package com.example.banquemisr.screens.addCard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.banquemisr.screens.signIn.PreferencesHelper
import com.example.bm_app.approutes.AppRoutes.ACCOUNT_CONNECTED_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPScreen(navController: NavController, modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.Gredient),
                    titleContentColor = colorResource(id = R.color.Gray_G900)
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
        OTPVerificationScreen(innerPadding,navController)
    }
}

@Composable
fun OTPVerificationScreen(innerPadding: PaddingValues,navController: NavController) {
    var otp by remember { mutableStateOf("") }
    val context = LocalContext.current
    val preferencesHelper = PreferencesHelper(context)
    var email = remember { mutableStateOf<String>(preferencesHelper.getEmail() ?: "") }

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
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(30.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Enter the digits verification code\n " + email,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.Gray_G700),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            )
            Spacer(modifier = Modifier.height(50.dp))
            OTPInputFields(otp = otp) { otp = it }
            Spacer(modifier = Modifier.height(50.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "Don't receive OTP?",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.Gray_G700),
                    fontSize = 15.sp
                )
                Text(
                    text = "Resend OTP",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.Beige),
                    modifier = Modifier.clickable { /* Handle Resend OTP */ },
                    fontSize = 15.sp
                )
            }
            Spacer(modifier = Modifier.height(150.dp))
            Button(
                onClick = { navController.navigate("${ACCOUNT_CONNECTED_ROUTE}")},
                enabled = otp.length == 6,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.Beige)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(9.dp),
            ) {
                Text(text = "Submit Refill", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPInputFields(otp: String, onOtpChange: (String) -> Unit) {
    val focusRequesters = List(6) { FocusRequester() }
    val isFieldFilled = remember { mutableStateOf(Array(6) { false }) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        (0 until 6).forEach { index ->
            val isFilled = isFieldFilled.value[index]
            OutlinedTextField(
                value = if (index < otp.length) otp[index].toString() else "",
                onValueChange = { value ->
                    if (value.length <= 1) {
                        val newOtp = otp.take(index) + value + otp.drop(index + 1)
                        onOtpChange(newOtp.take(6))
                        isFieldFilled.value[index] = value.isNotEmpty()
                        if (value.isNotEmpty() && index < 5) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    }
                },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier
                    .height(30.dp)
                    .weight(1f)
                    .aspectRatio(0.8f)
                    .focusRequester(focusRequesters[index]),
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = if (isFilled) colorResource(id = R.color.Beige) else Color.Gray,
                    unfocusedBorderColor = if (isFilled) colorResource(id = R.color.Beige) else Color.Gray,
                    focusedTextColor = colorResource(id = R.color.Beige),
                    unfocusedTextColor = colorResource(id = R.color.Beige)
                ),
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.Beige),
                    fontSize = 25.sp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOTPVerificationScreen() {
    OTPScreen(navController = rememberNavController())
}
