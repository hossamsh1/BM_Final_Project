package com.example.banquemisr.screens.addCard

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.bm_app.approutes.AppRoutes.CONNECTING_SCREEN_ROUTE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardScreen(navController: NavController, modifier: Modifier = Modifier) {

    val cardHolderName = remember { mutableStateOf("") }
    val cardNO = remember { mutableStateOf("") }
    val CVV = remember { mutableStateOf("") }
    val expiryDate = remember { mutableStateOf("") }


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
                            "Add Card",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.W500
                        )

                    }

                }
                ,actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { navController.popBackStack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.W500
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        addCard(
            navController,cardHolderName,cardNO,CVV,expiryDate, innerPadding
        )
    }
}

@Composable
fun addCard(navController: NavController, cardHolder:MutableState<String>, CardNo: MutableState<String>, CVV:MutableState<String>, expiryDate:MutableState<String>, innerPadding: PaddingValues,modifier: Modifier=Modifier){
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
            .verticalScroll(scrollState)
            .padding(innerPadding)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            contentAlignment = Alignment.Center, modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Sign on your Speedo Transfer Account",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.W600
            )

        }

        Spacer(modifier = Modifier.height(40.dp))
        TextFieldsNoIcon(
            string1 = "cardHolder Name",
            string2 = "Enter cardHolder Name",
            state = cardHolder,
            keyboard = KeyboardOptions(keyboardType = KeyboardType.Text),
            isPassword = false
        )
        TextFieldsNoIcon(
            string1 = "Card NO",
            string2 = "Card NO",
            state = CardNo,
            keyboard = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            isPassword = false
        )
        Row (horizontalArrangement = Arrangement.SpaceBetween){
            TextFieldsNoIconSmall(
                string1 = "MM/YY",
                string2 = "MM/YY",
                state = expiryDate,
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Text),
                isPassword = false,
                horizontalPadding = 32,
                padding = 5
            )
            TextFieldsNoIconSmall(
                string1 = "CVV",
                string2 = "CVV",
                state = CVV,
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isPassword = false,
                horizontalPadding = 5,
                padding = 32
            )

        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = { navController.navigate("${CONNECTING_SCREEN_ROUTE}") },
            modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.Beige)
            ),
            shape = RoundedCornerShape(9.dp),
            enabled = cardHolder.value.isNotEmpty() && CardNo.value.isNotEmpty()&& expiryDate.value.isNotEmpty()
                    && CVV.value.isNotEmpty()

        ) {
            Text(
                text = "Sign on",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500
            )

        }


    }









}
@Composable
fun TextFieldsNoIcon(string1: String, string2: String, state: MutableState<String>,keyboard:KeyboardOptions,isPassword:Boolean,modifier: Modifier = Modifier) {

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
            visualTransformation = if (isPassword&&!eyeClicked) PasswordVisualTransformation() else VisualTransformation.None,

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
fun TextFieldsNoIconSmall(string1: String, string2: String, state: MutableState<String>,keyboard:KeyboardOptions,isPassword:Boolean,horizontalPadding:Int,padding:Int,modifier: Modifier = Modifier) {

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
        Text(
            text = string1,
            modifier = modifier.padding(horizontal = horizontalPadding.dp),
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
            visualTransformation = if (isPassword&&!eyeClicked) PasswordVisualTransformation() else VisualTransformation.None,

            keyboardOptions = keyboard,
            modifier = modifier
                .width(200.dp)
                .padding( start = horizontalPadding.dp ,top = 10.dp,end=padding.dp)
                .background(color = Color.White, shape = RoundedCornerShape(9.dp)),
            shape = RoundedCornerShape(9.dp)


        )
    }
}

@Preview
@Composable
fun addCardPreview(){
    AddCardScreen(navController = rememberNavController())
}