@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.banquemisr.screens.signUp

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.banquemisr.models.SignUpViewModel
import com.example.banquemisr.screens.signIn.PreferencesHelper
import com.example.bm_app.approutes.AppRoutes.SIGNIN_ROUTE
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen2(
    navController: NavController,
    fullName: String,
    email: String,
    password: String,
    modifier: Modifier = Modifier
) {

    val country = remember { mutableStateOf<String>("") }
    val mDate = remember { mutableStateOf<String>("") }
    val viewModel: SignUpViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.Gredient),
                    titleContentColor = colorResource(id = R.color.Gray_G900),
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.W500
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        SignUp2(innerPadding, navController, country, mDate, viewModel, fullName, email, password)
    }
}

@Composable
fun SignUp2(
    innerPadding: PaddingValues,
    navController: NavController,
    country: MutableState<String>,
    mDate: MutableState<String>,
    viewModel: SignUpViewModel,
    fullName: String,
    email: String,
    password: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val preferencesHelper = PreferencesHelper(context)
    val scrollState = rememberScrollState()
    val signUpSuccess by viewModel.signUpSuccess.collectAsState()

    LaunchedEffect(signUpSuccess) {
        if (signUpSuccess == true) {
            preferencesHelper.saveCredentialsSignUp(
                email,
                password,
                fullName,
                country.value,
                mDate.value
            )
            preferencesHelper.clearCredentialsSignIn()
            navController.navigate(SIGNIN_ROUTE)
        } else if (signUpSuccess == false) {
            Toast.makeText(context, "Sign Up Failed", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(
                listOf(colorResource(id = R.color.Greadient2), colorResource(id = R.color.Gredient)),
                startY = 2000f,
                endY = 0f
            ))
            .verticalScroll(scrollState)
            .padding(innerPadding)
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Box(
            contentAlignment = Alignment.Center, modifier = modifier.fillMaxWidth()
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
        Text(
            text = "Welcome To Banque Misr!",
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.W600
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Let's complete your profile",
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.W400
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            bottomSheet(country)
            Spacer(modifier = Modifier.height(10.dp))
            DatePickerButton(mDate)
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    viewModel.signUp(
                        fullName,
                        email,
                        "0112193837", // Fill in other required fields
                        "username", // Fill in other required fields
                        password,
                        password, // Fill in other required fields
                        "male", // Fill in other required fields
                        country.value,
                        mDate.value
                    )
                    navController.navigate(SIGNIN_ROUTE)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.Beige)
                ),
                shape = RoundedCornerShape(9.dp),
                enabled = country.value.isNotEmpty() && mDate.value.isNotEmpty()
            ) {
                Text(
                    text = "Continue",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500
                )
            }
        }
    }
}

@Composable
fun DatePickerButton(mDate: MutableState<String>) {
    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            mDate.value = "$dayOfMonth/${month + 1}/$year"
        }, mYear, mMonth, mDay
    )

    Column(
        modifier = Modifier
            //.fillMaxSize()
                ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Date Of Birth",
            modifier = Modifier.padding(horizontal = 32.dp),
            fontSize = 16.sp,
            color = colorResource(id = R.color.Gray_G700),
            fontWeight = FontWeight.W400
        )
        Spacer(modifier = Modifier.height(5.dp))

        Button(
            onClick = { mDatePickerDialog.show() },
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(4.dp))
                .background(color = Color.White),
            colors = ButtonDefaults.buttonColors(Color.White),
        ) {
            Text(
                text = if (mDate.value.isNotEmpty()) mDate.value else "DD/MM/YYYY",
                color = if (mDate.value.isNotEmpty()) Color.Black else colorResource(id = R.color.Gray_G70),
                fontSize = 16.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
            )
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                tint = Color.DarkGray,
            )
        }
    }
}

@Preview(device = "id:pixel_6a")
@Composable
fun OverAll5() {
    SignUpScreen2(rememberNavController(), "malak", ".com", "12345")}






//@file:OptIn(ExperimentalMaterial3Api::class)
//
//package com.example.banquemisr.screens.signUp
//
//
//import android.app.DatePickerDialog
//import android.widget.DatePicker
//import android.widget.Toast
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material.icons.filled.DateRange
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.banquemisr.R
//import com.example.banquemisr.models.SignUpViewModel
//import com.example.banquemisr.screens.signIn.PreferencesHelper
//import com.example.bm_app.approutes.AppRoutes.SIGNIN_ROUTE
//import java.util.Calendar
//import java.util.Date
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SignUpScreen2(
//    navController: NavController,
//    fullName: String,
//    email: String,
//    password: String,
//    modifier: Modifier = Modifier
//) {
//    var country = remember { mutableStateOf<String>("") }
//    var mDate = remember { mutableStateOf<String>("") }
//    val viewModel: SignUpViewModel = viewModel()
//
//
//
//
//    Scaffold(
//
//        topBar = {
//            TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = colorResource(id = R.color.Gredient),
//                    titleContentColor = colorResource(id = R.color.Gray_G900),
//
//                    ),
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Localized description"
//                        )
//                    }
//                },
//
//                title = {
//
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            text = "",
//                            fontSize = 20.sp,
//                            textAlign = TextAlign.Center,
//                            fontWeight = FontWeight.W500
//                        )
//                    }
//                }
//
//
//            )
//        },
//    ) { innerPadding ->
//        SignUp2(innerPadding, navController, country, mDate, viewModel, fullName, email, password)
//    }
//}
//
//
//@Composable
//
//fun SignUp2(
//    innerPadding: PaddingValues,
//    navController: NavController,
//    country: MutableState<String>,
//    mDate: MutableState<String>,
//    viewModel: SignUpViewModel,
//    fullName: String,
//    email: String,
//    password: String,
//    modifier: Modifier = Modifier
//) {
//
//    val context = LocalContext.current
//
//    val preferencesHelper = PreferencesHelper(context)
//
//
//    val scrollState = rememberScrollState()
//    val signUpSuccess by viewModel.signUpSuccess.collectAsState()
//    var background = Brush.verticalGradient(
//        listOf(colorResource(id = R.color.Greadient2), colorResource(id = R.color.Gredient)),
//        startY = 2000f,
//        endY = 0f
//    )
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//            .fillMaxSize()
//            .background(background)
//            .verticalScroll(scrollState)
//            .padding(innerPadding)
//    ) {
//        Spacer(modifier = Modifier.height(80.dp))
//
//        Box(
//            contentAlignment = Alignment.Center, modifier = modifier
//                .fillMaxWidth()
//        ) {
//            Text(
//                text = "Speedo Transfer",
//                textAlign = TextAlign.Center,
//                color = Color.Black,
//                fontSize = 24.sp,
//                fontWeight = FontWeight.W600
//            )
//
//        }
//        Spacer(modifier = Modifier.height(60.dp))
//        Text(
//            text = "Welcome To Banque Misr!",
//            textAlign = TextAlign.Center,
//            color = Color.Black,
//            fontSize = 24.sp,
//            fontWeight = FontWeight.W600
//        )
//        Spacer(modifier = Modifier.height(20.dp))
//        Text(
//            text = "Let's complete your profile",
//            textAlign = TextAlign.Center,
//            color = Color.Black,
//            fontSize = 16.sp,
//            fontWeight = FontWeight.W400
//        )
//        Spacer(modifier = Modifier.height(20.dp))
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            bottomSheet(country)
//            Spacer(modifier = Modifier.height(10.dp))
//            DatePickerButton(mDate)
//            Spacer(modifier = Modifier.height(40.dp))
//            Button(
//
//                onClick = {
//                    viewModel.signUp(
//                        fullName,
//                        email,
//                        "0112193837", // Fill in other required fields
//                        "username", // Fill in other required fields
//                        password,
//                        password, // Fill in other required fields
//                        "male", // Fill in other required fields
//                        country.value,
//                        mDate.value
//                    )
//                    if (signUpSuccess == true) {
//                        preferencesHelper.saveCredentialsSignUp(
//                            email,
//                            password,
//                            fullName,
//                            country.value,
//                            mDate.value
//                        )
//                        preferencesHelper.clearCredentialsSignIn()
//                        navController.navigate("$SIGNIN_ROUTE")
//                    }
//
//
//                    if (signUpSuccess == true)
//                        navController.navigate("$SIGNIN_ROUTE")
//                    else Toast.makeText(context, "faild", Toast.LENGTH_SHORT).show()
//                },
//                modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 32.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = colorResource(id = R.color.Beige)
//                ),
//                shape = RoundedCornerShape(9.dp),
//                enabled = country.value.isNotEmpty() && mDate.value.isNotEmpty()
//            ) {
//                Text(
//                    text = "Continue",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.W500
//                )
//
//            }
//
//
//        }
//    }
//
//
//}
//
//
//@Composable
//fun DatePickerButton(mDate: MutableState<String>) {
//
//
//    val mContext = LocalContext.current
//
//    val mYear: Int
//    val mMonth: Int
//    val mDay: Int
//
//
//    val mCalendar = Calendar.getInstance()
//
//
//    mYear = mCalendar.get(Calendar.YEAR)
//    mMonth = mCalendar.get(Calendar.MONTH)
//    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
//
//    mCalendar.time = Date()
//
//
//    val mDatePickerDialog = DatePickerDialog(
//        mContext,
//        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
//            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
//        }, mYear, mMonth, mDay
//    )
//
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.Start
//    ) {
//
//        Text(
//            text = "Date Of Birth",
//            modifier = Modifier.padding(horizontal = 32.dp),
//            fontSize = 16.sp,
//            color = colorResource(id = R.color.Gray_G700),
//            fontWeight = FontWeight.W400,
//
//            )
//
//
//
//        Button(
//            onClick = {
//                mDatePickerDialog.show()
//
//            },
//            modifier = Modifier
//                .height(55.dp)
//                .fillMaxWidth()
//                .padding(horizontal = 32.dp)
//                .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(4.dp))
//                .background(color = Color.White),
//            colors = ButtonDefaults.buttonColors(Color.White),
//
//            ) {
//            Text(
//                text = if (mDate.value.isNotEmpty()) mDate.value else "DD/MM/YYYY",
//                color = if (mDate.value.isNotEmpty()) Color.Black else colorResource(id = R.color.Gray_G70),
//                fontSize = 16.sp,
//                modifier = Modifier.weight(1f),
//                textAlign = TextAlign.Start
//            )
//            Icon(
//                imageVector = Icons.Default.DateRange,
//                contentDescription = null,
//                tint = Color.DarkGray,
//
//                )
//        }
//
//
//    }
//}
//
//
//@Preview(device = "id:pixel_6a")
//@Composable
//fun OverAll5() {
//    SignUpScreen2(rememberNavController(), "malak", ".com", "12345")
//}
//
