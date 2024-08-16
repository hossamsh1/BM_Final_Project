package com.example.banquemisr.ui.screens.transferScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.banquemisr.screens.functionsusable.TextFormaterEGP
import com.example.banquemisr.screens.functionsusable.TextFormaterUSA
import com.example.banquemisr.ui.screens.notification.createNotificationChannel
import com.example.banquemisr.ui.screens.notification.notificationContent
import com.example.bm_app.approutes.AppRoutes.TRANSFERAMOUNT_ROUTE
import com.example.bm_app.approutes.AppRoutes.TRANSFERCONFIRMATION_ROUTE
import com.example.bm_app.approutes.AppRoutes.TRANSFERPAYMENT_ROUTE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferConfirmationScreen(
    navController: NavController,
    amount: Comparable<*>,
    recipientName: String,
    recipientAccount: String
    ,amountEgp:Double
) {
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

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
          ConfirmationScreen(
              navController = navController,
              amount = amount, recipientName = recipientName, recipientAccount = recipientAccount,amountEgp = amountEgp)
        }
    }
}



@Composable
fun ConfirmationScreen(
    navController: NavController, amount: Comparable<*>,
    recipientName: String,
    recipientAccount: String,
    amountEgp:Double) {
val context= LocalContext.current
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
                borderColor = colorResource(id = R.color.Beige),
                text = "02",
                textColor = colorResource(id = R.color.Beige)
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

Spacer(modifier = Modifier.padding(10.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 20.dp, end = 8.dp))
        {

Row (modifier = Modifier.align(Alignment.CenterHorizontally)){
    TextFormaterUSA( amount , modifier = Modifier
        , fontSize = 20 , color = colorResource(id =R.color.Total_amount)
        , fontWeight = FontWeight.Bold )

}


Spacer(modifier = Modifier.padding(0.dp))

            Text(modifier = Modifier.align(Alignment.CenterHorizontally)
                ,text = "Transfer Amount" ,
                fontSize = 16.sp
                , color = colorResource(id = R.color.text_light_Gray))
Spacer(modifier = Modifier.padding(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(130.dp)){
                Text(modifier = Modifier
                    .align(Alignment
                    .CenterVertically),
                    text = "Total Amount"
                    , fontSize =16.sp
                , fontWeight = FontWeight.Medium)

                TextFormaterEGP( amountEgp,fontSize = 16,
                    color = colorResource(id = R.color.Gray_G100), fontWeight = FontWeight.Bold)
            }
Spacer(modifier = Modifier.padding(12.dp))

            Divider()
            Spacer(modifier = Modifier.padding(12.dp))

            TransferInfo(fullName = "hossam"
                , fromAccount = "123456"
                , recipientName = recipientName
                , recipientAccount = recipientAccount
                , iconResId = R.drawable.icon_banque
                , iconTransA = R.drawable.icon_transfer)

Spacer(modifier = Modifier.padding(10.dp))

            FilledTonalButton(
                onClick = {
                    // Build and display the notification
                    notificationContent(
                        context=context,
                        textTitle = "Transfer Successful",
                        textContent = "You have successfully transferred $amount to $recipientName."
                    )

                        val route =
                            "$TRANSFERPAYMENT_ROUTE/${amount}/${recipientName}/${recipientAccount}/${amountEgp}"
                        navController.navigate(route)
                          },
                shape = RoundedCornerShape(10.dp)
                ,colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = colorResource(id = R.color.Beige))
                ,modifier = Modifier
                    .fillMaxWidth()
                    .height(51.dp)
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    color = Color.White,
                    text = "Continue",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                )
            }
Spacer(modifier = Modifier.padding(16.dp))

            TextButton(
                shape = RoundedCornerShape(10.dp)
                ,modifier = Modifier
                    .fillMaxWidth()
                    .height(51.dp)
                    .padding(horizontal = 12.dp)
                    .border(
                        1.dp,
                        color = colorResource(id = R.color.Beige),
                        shape = RoundedCornerShape(10.dp)
                    )
                ,onClick = { navController.navigate(TRANSFERAMOUNT_ROUTE) }) {
                Text(color = colorResource(id = R.color.Beige)
                    ,fontSize = 16.sp
                    , text = "Previous")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransferConfirmationScreenPreview() {
    TransferConfirmationScreen(
        navController = rememberNavController(),
        amount = 100.0,
        recipientName = "John Doe",
        recipientAccount = "123456789"
        ,amountEgp = 200000.0
    )
}



@Composable
fun TransferInfo(
    fullName: String,
    fromAccount: String,
    recipientName: String,
    recipientAccount: String,
    iconResId: Int,
    iconTransA:Int

) {

        Box() {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)

            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(id = R.color.primary_color))
                ) {

                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(48.dp)
                            .background(
                                color = colorResource(id = R.color.Gray_G40), shape = CircleShape
                            )
                            .clip(CircleShape)
                    ) {

                        Icon(
                            painter = painterResource(id = iconResId),
                            contentDescription = "From icon",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.padding(
                            start = 32.dp,
                            top = 16.dp,
                            bottom = 16.dp
                        )
                    ) {
                        Text(
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.Beige),
                            text = "From"
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            color = colorResource(id = R.color.Gray_G900),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            text = fullName
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.Gray_G100),
                            text = "Account $fromAccount"
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(11.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(id = R.color.primary_color))

                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(48.dp)
                            .background(
                                color = colorResource(id = R.color.Gray_G40), shape = CircleShape
                            )
                            .clip(CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(id = iconResId),
                            contentDescription = "To icon",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier.padding(
                            start = 32.dp,
                            top = 16.dp,
                            bottom = 16.dp
                        )
                    ) {
                        Text(
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.Beige),
                            text = "To"
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            color = colorResource(id = R.color.Gray_G900),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            text = recipientName
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.Gray_G100),
                            text = "Account $recipientAccount"
                        )
                    }
                }
            }
            Box(modifier = Modifier
                .align(Alignment.Center)) {
                Image(
                    modifier = Modifier.size(44.dp),
                    painter = painterResource(iconTransA),
                    contentDescription = null
                )
            }
        }
    }




