package com.example.banquemisr.screens.signUp

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.bm_app.approutes.AppRoutes.SIGNIN_ROUTE
import com.example.bm_app.approutes.AppRoutes.SIGN_UP_COMPLETE_ROUTE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController,modifier: Modifier=Modifier) {
    var email = remember { mutableStateOf<String>("") }
    var password = remember { mutableStateOf<String>("") }
    var name = remember { mutableStateOf<String>("") }
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
                            "Sign Up",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.W500
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        SignUp(innerPadding,navController,email,password,name)
    }
}


@Composable

fun SignUp(innerPadding: PaddingValues,
           navController: NavController,
           email: MutableState<String>,
           password: MutableState<String>,
           name: MutableState<String> ,modifier: Modifier = Modifier)
{
    val scrollState = rememberScrollState()
    var showDialog by remember { mutableStateOf(false) }
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
            .verticalScroll(scrollState)
            .padding(innerPadding)
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
            TextFields(
                string1 = "Full name",
                string2 = "Enter your Full name",
                R.drawable.person,
                name,KeyboardOptions(keyboardType = KeyboardType.Text),false
            )
            TextFields(string1 = "Email", string2 = "Enter your email", R.drawable.email,email,KeyboardOptions(keyboardType = KeyboardType.Email),false)
            TextFields(string1 = "Password", string2 = "Enter your password", R.drawable.eye,password,KeyboardOptions(keyboardType = KeyboardType.Password),true)
            var isPasswordValid= isPasswordValid(password = password)

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Invalid Password") },
                    text = {
                        Text(
                            text = "Password must be at least 6 characters long, " +
                                    "contain at least one uppercase letter, one lowercase letter, and one special character."
                        )
                    },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { if (isPasswordValid) {
                    val route = "$SIGN_UP_COMPLETE_ROUTE/${name.value}/${email.value}/${password.value}"
                    navController.navigate(route)
                } else {
                    showDialog = true
                }},
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.Beige)
                ),
                shape = RoundedCornerShape(9.dp),
                enabled = email.value.isNotEmpty() && password.value.isNotEmpty()&&name.value.isNotEmpty()
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500
                )

            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account?",
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.Gray_G70)
                )
                TextButton(onClick = {navController.navigate("$SIGNIN_ROUTE") }) {
                    Text(
                        text = "Sign in",
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
fun TextFields(string1: String, string2: String, icon: Int, state: MutableState<String>,keyboard:KeyboardOptions,isPassword:Boolean,modifier: Modifier = Modifier,size:Int=25) {

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
                        .align(Alignment.End)
                        .clickable { eyeClicked = !eyeClicked }
                        .size(size.dp)

                )
            }, visualTransformation = if (isPassword&&!eyeClicked) PasswordVisualTransformation() else VisualTransformation.None,

            keyboardOptions = keyboard,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 10.dp)
                .background(color = Color.White, shape = RoundedCornerShape(9.dp)),
            shape = RoundedCornerShape(9.dp)

        )
    }
}
@Composable
fun isPasswordValid(password: MutableState<String>): Boolean {
    val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{6,}$".toRegex()
    return passwordRegex.matches(password.value)
}


@Preview(device = "id:pixel_6a")
@Composable
fun OverAll() {
    SignUpScreen(rememberNavController())
}