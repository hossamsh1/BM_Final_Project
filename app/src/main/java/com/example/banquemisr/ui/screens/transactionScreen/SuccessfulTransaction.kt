package com.example.banquemisr.ui.screens.transactionScreen

import com.example.banquemisr.ui.screens.transferScreen.TransferInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
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
import com.example.banquemisr.screens.functionsusable.TextFormaterEGP

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessfulTransactionScreen(navController: NavController) {
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
                        "Successful Transaction",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("transaction") }) {
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
            TransactionScrol(navController = navController)
        }
    }
}



@Composable
fun TransactionScrol(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {


        Spacer(modifier = Modifier.padding(12.dp))

        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
            Image(modifier = Modifier.size(124.dp)
                ,painter = painterResource(id = R.drawable.transaction_successful)
                , contentDescription = null)

        }
Spacer(modifier = Modifier.padding(16.dp))
        Column (modifier = Modifier.fillMaxWidth()){
            Row (modifier = Modifier
                .align(Alignment.CenterHorizontally)){
                Text(fontSize = 24.sp
                    , fontWeight = FontWeight.Bold
                    , color = colorResource(id = R.color.Gray_G900)
                    ,text = "100")
                Spacer(modifier = Modifier.padding(3.dp))
                Text(fontSize = 24.sp
                    , fontWeight = FontWeight.Bold
                    , color = colorResource(id = R.color.Beige)
                    ,text = "USD")
            }
Spacer(modifier = Modifier.padding(4.dp))
            Text(modifier = Modifier.align(Alignment.CenterHorizontally)
                ,color = colorResource(id = R.color.Gray_G700)
                , fontSize = 16.sp
                ,text = "Transfer Amount")
Spacer(modifier = Modifier.padding(4.dp))

            Text(modifier = Modifier.align(Alignment.CenterHorizontally)
                ,color = colorResource(id = R.color.Beige)
                , fontSize = 14.sp
                , fontWeight = FontWeight.Medium
                ,text = "Send Money")

        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 20.dp, end = 8.dp))
        {

            TransferInfo(
                fullName = "hossam",
                fromAccount = "123456",
                recipientName = "mohamed",
                recipientAccount = "123456",
                iconResId = R.drawable.icon_banque,
                iconTransA = R.drawable.icon_correct
            )


            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.primary_color))
            ) {
                Column {
                    Row(horizontalArrangement = Arrangement.spacedBy(60.dp))
                    {
                        Text(
                            color = colorResource(id = R.color.Gray_G700),
                            modifier = Modifier
                                .padding(bottom = 9.dp)
                                .align(
                                    Alignment
                                        .CenterVertically
                                ),
                            text = "Transfer amount amount",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )

                        TextFormaterEGP(
                            20000.0,
                            fontSize = 16,
                            color = colorResource(id = R.color.text_light_Gray),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Divider()
                    Spacer(modifier = Modifier.padding(8.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(175.dp))
                    {
                        Text(
                            color = colorResource(id = R.color.Gray_G700),
                            modifier = Modifier
                                .padding(bottom = 9.dp)
                                .align(
                                    Alignment
                                        .CenterVertically
                                ),
                            text = "Reference",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            color = colorResource(id = R.color.text_light_Gray),
                            modifier = Modifier
                                .padding(bottom = 9.dp)
                                .align(
                                    Alignment
                                        .CenterVertically
                                ),
                            text = "123456789987",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
Spacer(modifier = Modifier.padding(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(175.dp))
                    {
                        Text(
                            color = colorResource(id = R.color.Gray_G700),
                            modifier = Modifier
                                .padding(bottom = 9.dp)
                                .align(
                                    Alignment
                                        .CenterVertically
                                ),
                            text = "Date",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            color = colorResource(id = R.color.text_light_Gray),
                            modifier = Modifier
                                .padding(bottom = 9.dp)
                                .align(
                                    Alignment
                                        .CenterVertically
                                ),
                            text = "20 Jul 2024 7:50 PM",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )


                    }

                }
            }
        }
    }
}


@Preview
@Composable
fun TransferPaymentScreenPreview() {
    SuccessfulTransactionScreen(navController = NavController(LocalContext.current))
}