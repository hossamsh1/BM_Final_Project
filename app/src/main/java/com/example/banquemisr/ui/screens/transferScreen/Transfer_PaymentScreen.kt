package com.example.banquemisr.ui.screens.transferScreen

import android.media.session.MediaSession.Token
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.banquemisr.R
import com.example.banquemisr.models.FavoriteViewModel
import com.example.banquemisr.screens.functionsusable.TextFormaterEGP

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferPaymentScreen(navController: NavController,
                          amount: Double,
                          recipientName: String,
                          recipientAccount: String
                              ,amountEgp:Double) {
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
            PaymentScreen(navController = navController, amount = amount
                , recipientName = recipientName, recipientAccount = recipientAccount,amountEgp = amountEgp, token = "token")
        }
    }
}



@Composable
fun PaymentScreen(viewModel: FavoriteViewModel
                  = androidx.lifecycle.viewmodel.compose.viewModel(),token: String
    ,navController: NavController,
                  amount: Double,
                  recipientName: String,
                  recipientAccount: String
                  ,amountEgp:Double) {

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
                    .background(color = colorResource(id = R.color.Beige))
                    .align(Alignment.CenterVertically)
            )

            CircleWithNumWithText(
                modifier = Modifier.align(Alignment.CenterVertically),
                borderColor = colorResource(id = R.color.Beige),
                text = "03",
                textColor = colorResource(id = R.color.Beige)
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

Spacer(modifier = Modifier.padding(12.dp))

        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
            Image(modifier = Modifier.size(112.dp)
                ,painter = painterResource(id = R.drawable.icon_successful)
                , contentDescription = null)

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 20.dp, end = 8.dp))
        {

            Text(modifier = Modifier.align(Alignment.CenterHorizontally)
                ,color = colorResource(id = R.color.Gray_G900)
                ,fontWeight = FontWeight.Bold
                ,fontSize = 20.sp
                ,text = "Your transfer was successful")

            Spacer(modifier = Modifier.padding(5.dp))

            TransferInfo(fullName = "hossam"
                , fromAccount = "123456"
                , recipientName = recipientName
                , recipientAccount = recipientAccount
                , iconResId = R.drawable.icon_banque
                , iconTransA = R.drawable.icon_correct)

            Spacer(modifier = Modifier.padding(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(60.dp))
            {
                Text(color = colorResource(id = R.color.text_light_Gray)
                    ,modifier = Modifier
                        .padding(bottom = 9.dp)
                        .align(
                            Alignment
                                .CenterVertically
                        ),
                    text = "Transfer amount amount"
                    , fontSize =16.sp
                    , fontWeight = FontWeight.Medium)

                TextFormaterEGP( amountEgp,fontSize = 16,
                    color = colorResource(id = R.color.text_light_Gray)
                    , fontWeight = FontWeight.Bold)
            }
            Divider()
            Spacer(modifier = Modifier.padding(18.dp))

            FilledTonalButton(
                onClick = { navController.navigate("home") },
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
                    text = "Back to Home",
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
                ,onClick = {
                    try {
                        viewModel.addFavorite(token,recipientName,recipientAccount)
                        Toast.makeText(context, "the added successful", Toast.LENGTH_SHORT).show()
                        navController.navigate("transferAmount")
                    }catch (e :Exception){
                        Toast.makeText(context, "added faild", Toast.LENGTH_SHORT).show()
                }
                }
            ) {
                Text(color = colorResource(id = R.color.Beige)
                    ,fontSize = 16.sp
                    , text = "Add to Favourite")
            }
        }
    }
}


@Preview
@Composable
fun TransferPaymentScreenPreview() {
    TransferPaymentScreen(navController = NavController(LocalContext.current) , amount = 100.0, recipientName = "John Doe", recipientAccount = "123456789",amountEgp = 200000.0)
}