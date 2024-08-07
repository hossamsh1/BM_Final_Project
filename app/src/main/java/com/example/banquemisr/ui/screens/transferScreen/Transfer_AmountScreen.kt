package com.example.banquemisr.ui.screens.transferScreen

import FavouriteListModalBottomSheetContent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.banquemisr.screens.functionsusable.ExposedDropdownMenuBox
import com.example.bm_app.approutes.AppRoutes.TRANSFERCONFIRMATION_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferAmountScreen(navController: NavController) {

    var amount = remember { mutableStateOf("") }
    var recipientName = remember { mutableStateOf("") }
    var recipientAccount = remember { mutableStateOf("") }


    val background = Brush.verticalGradient(
        listOf(colorResource(id = R.color.Greadient2), colorResource(id = R.color.Gredient)),
        startY = 2000f,
        endY = 0f
    )

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())


    Scaffold(
        modifier = Modifier
            .background(background)
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(id = R.color.Gredient),
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        "Transfer",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },

        )

    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            ScrollContent(innerPadding,navController,amount,recipientName,recipientAccount)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ScrollContent(
    innerPadding: PaddingValues,
    navController: NavController,
    amount: MutableState<String>,
    recipientName: MutableState<String>,
    recipientAccount: MutableState<String>) {

    val context = LocalContext.current
    var sheetstate = rememberModalBottomSheetState()
    var isSheetOpen by remember { mutableStateOf(false) }

    var selectedCurrency by remember { mutableStateOf("USD") } // Default currency
    var amountEgp by remember { mutableStateOf("") }

    val conversionRate = 48.4220 // Example rate for USD to EGP

    fun calculateAmountEgp(amount: String, currency: String): String {
        val amountValue = amount.toDoubleOrNull() ?: 0.0
        return when (currency) {
            "EGP" -> (amountValue * conversionRate).toString()
            else -> ""
        }
    }

    if (isSheetOpen) {

        ModalBottomSheet(
            onDismissRequest = { isSheetOpen = !isSheetOpen },
            sheetState = sheetstate,
            dragHandle = { },
        ) {
            FavouriteListModalBottomSheetContent(
                token = "token",
                onDismiss = { isSheetOpen = !isSheetOpen }
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            CircleWithNumWithText(
                modifier = Modifier.align(Alignment.CenterVertically),
                borderColor = colorResource(id = R.color.Beige),
                text = "01",
                textColor = colorResource(id = R.color.Beige)
            )
            Box(
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
                    .width(100.dp)
                    .height(2.dp)
                    .background(colorResource(id = R.color.Beige))
                    .align(Alignment.CenterVertically)
            )

            CircleWithNumWithText(
                modifier = Modifier.align(Alignment.CenterVertically),
                borderColor = Color.Gray,
                text = "02",
                textColor = Color.Gray
            )

            Box(
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
                    .width(100.dp)
                    .height(2.dp)
                    .background(Color.Gray)
                    .align(Alignment.CenterVertically)
            )

            CircleWithNumWithText(
                modifier = Modifier.align(Alignment.CenterVertically),
                borderColor = Color.Gray,
                text = "03",
                textColor = Color.Gray
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(70.dp),
            modifier = Modifier.padding(start = 25.dp, top = 20.dp)
        ) {
            Text(
                color = colorResource(id = R.color.col_Text_gray),
                text = "Amount"
            )
            Text(
                color = colorResource(id = R.color.col_Text_gray),
                text = "Confirmation"
            )
            Text(
                color = colorResource(id = R.color.col_Text_gray),
                text = "Payment"
            )
        }

        Text(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 20.dp),
            text = "How much are you sending?"
        )

        Column(
            modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 8.dp, bottom = 50.dp)
        ) {
            Text(
                color = colorResource(id = R.color.col_Text_gray),
                fontSize = 16.sp,
                text = "Choose Currency"
            )

            ElevatedCard(
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
                modifier = Modifier
                    .padding(top = 20.dp, end = 10.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(start = 8.dp, top = 10.dp)) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Text(
                            modifier = Modifier.padding(start = 5.dp),
                            color = Color.Black,
                            fontSize = 16.sp,
                            text = "1 USD ="
                        )

                        Text(
                            color = Color.Black,
                            fontSize = 16.sp,
                            text = "48.4220 EGP"
                        )
                    }


                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Gray,
                        fontSize = 14.sp,
                        text = "Rate guaranteed (2h)"
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        color = colorResource(id = R.color.col_Text_gray),
                        fontSize = 16.sp,
                        text = "You Send"
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)
                        , modifier = Modifier.padding(end = 5.dp)) {

                        ExposedDropdownMenuBox(onCurrencySelected = { currency ->
                            selectedCurrency = currency
                            amountEgp = calculateAmountEgp(amount.value, selectedCurrency)
                        })
                        OutlinedTextField(
                            value = amount.value ,
                            onValueChange = { amount.value = it
                                amountEgp = calculateAmountEgp(it, selectedCurrency)         },
                            modifier = Modifier
                                .height(60.dp)
                                .width(200.dp)
                                .padding(top = 0.dp, end = 12.dp)
                        )
                    }


                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 10.dp)
                    )

                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        color = colorResource(id = R.color.col_Text_gray),
                        fontSize = 16.sp,
                        text = "Recipient Gets"
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)
                        , modifier = Modifier.padding(end = 15.dp)) {
                        ExposedDropdownMenuBox(onCurrencySelected = { currency ->
                            selectedCurrency = currency
                            amountEgp = calculateAmountEgp(amount.value, selectedCurrency)
                        })
                        Box (modifier = Modifier
                            .height(60.dp)
                            .width(200.dp)
                            .border(
                                0.5.dp,
                                color = colorResource(id = R.color.col_Text_gray),
                                RoundedCornerShape(10.dp)
                            )
                            .padding(top = 0.dp, end = 0.dp)
                        ){

                            Text(text = amountEgp
                                ,fontSize = 16.sp
                                , textAlign = TextAlign.Center
                                ,modifier = Modifier
                                    .padding(top = 20.dp, bottom = 16.dp, start = 10.dp)
                            )
                        }

                    }
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(60.dp),
                modifier = Modifier
                    .padding(start = 10.dp, top = 20.dp, end = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Recipient information",
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.col_Text_gray)
                )
                Row(modifier = Modifier.clickable {
                    isSheetOpen = true
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_favorite_stare),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 5.dp),
                        text = "favorites",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.Beige)
                    )
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.icon_wrightside),
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))


            TextFields(string1 = "Recipient Name", string2 = "Enter recipient name",recipientName)


            Spacer(modifier = Modifier.padding(8.dp))

            TextFields(string1 = "Recipient Account ", string2 = "Enter Recipient Account Number ",recipientAccount)

            Spacer(modifier = Modifier.padding(20.dp))

            FilledTonalButton(
                onClick = {
                if (recipientName.value.isNotEmpty() && recipientAccount.value.isNotEmpty() && amount.value.isNotEmpty()) {
                    val route =
                        "$TRANSFERCONFIRMATION_ROUTE/${amount.value}/${recipientName.value}/${recipientAccount.value}/${amountEgp}"
                    navController.navigate(route)
                }else{
                    Toast.makeText(context, "Please enter all fields", Toast.LENGTH_SHORT).show()} },

                shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = colorResource(id = R.color.Beige)
                ), modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .padding(start = 12.dp, end = 12.dp, top = 5.dp, bottom = 15.dp)
            ) {
                Text(
                    color = Color.White,
                    text = "Continue",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                )
            }

        }
    }
}


@Composable
fun CircleWithNumWithText(
    modifier: Modifier = Modifier,
    borderColor: Color,
    text: String,
    textColor: Color
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = CircleShape
            )
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun TextFields(string1: String, string2: String, state: MutableState<String>, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = string1,
            modifier = modifier.padding(horizontal = 12.dp),
            fontSize = 16.sp,
            color = colorResource(id = R.color.col_Text_gray),
            fontWeight = FontWeight.W400,

            )
        OutlinedTextField(
            value = state.value,
            onValueChange = { state.value = it },
            placeholder = { Text(text = string2, color = Color.Gray) },

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .background(color = Color.White, shape = RoundedCornerShape(9.dp)),
            shape = RoundedCornerShape(9.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransferScreenPreview() {
    TransferAmountScreen(navController = rememberNavController())
}