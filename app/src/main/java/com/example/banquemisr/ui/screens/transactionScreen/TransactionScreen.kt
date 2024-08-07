package com.example.banquemisr.ui.screens.transactionScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.banquemisr.R
import com.example.banquemisr.screens.functionsusable.TextFormaterUSA
import com.example.bm_app.approutes.AppRoutes.SUCCESSFUL_TRANSACTION_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransActionScreen(navController: NavController) {


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
                        "Transactions",
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
            TransActionScreenScrol()
        }


    }
}

    @Composable
    fun TransActionScreenScrol() {
        Column {
            Row(
                horizontalArrangement = Arrangement.Center, modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier
                        .align(Alignment.CenterVertically), text = "Your Last Transactions"
                )


            }

            ListTransactionSuccessful(navController = rememberNavController())
            ListTransactionFaild()
        }
    }


        @Composable
        fun ListTransactionSuccessful(navController: NavController) {

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, start = 16.dp, top = 16.dp, bottom = 10.dp)
                .background(Color.White)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, start = 16.dp, top = 16.dp))
                {

    Image(
        modifier = Modifier
            .padding(top = 10.dp)
            .size(56.dp),
        alignment = Alignment.Center,
        painter = painterResource(id = R.drawable.image_visa_card),
        contentDescription = null
    )

                        Column(
                            modifier = Modifier.padding(
                                start = 32.dp,
                                top = 10.dp,
                                bottom = 5.dp
                            )
                        ) {

                            Text(
                                color = colorResource(id = R.color.Gray_G900),
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp,
                                text = "hossam Shaban"
                            )
                            Text(
                                color = colorResource(id = R.color.Gray_G700),
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                text = "Visa . Mater Card . 1234"
                            )
                            Text(
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.Gray_G100),
                                text = "Today 11:00 - Received"
                            )

Spacer(modifier = Modifier.padding(8.dp))

                            TextFormaterUSA(
                                balance = 100.0,
                                fontSize = 16, color = colorResource(id = R.color.Beige),
                                fontWeight = FontWeight.Medium)
                        }

                    Column(modifier = Modifier
                        .padding(start = 25.dp, top = 8.dp)) {

                        Image(modifier = Modifier
                            .padding(start = 50.dp)
                            .size(24.dp)
                            ,painter = painterResource(id = R.drawable.next)
                            , contentDescription = null)

Spacer(modifier = Modifier.padding(8.dp))

                        Image(modifier = Modifier
                            .height(19.dp)
                            .width(71.dp)
                            ,painter = painterResource(id = R.drawable.successful_transaction)
                            , contentDescription = null)
                    }
                    }
            }
        }


@Composable
fun ListTransactionFaild() {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(end = 16.dp, start = 16.dp, top = 16.dp, bottom = 10.dp)
        .background(Color.White)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, start = 16.dp, top = 16.dp))
        {

            Image(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(56.dp),
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.image_banque_card),
                contentDescription = null
            )

            Column(
                modifier = Modifier.padding(
                    start = 32.dp,
                    top = 10.dp,
                    bottom = 5.dp
                )
            ) {

                Text(
                    color = colorResource(id = R.color.Gray_G900),
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    text = "hossam Shaban"
                )
                Text(
                    color = colorResource(id = R.color.Gray_G700),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    text = "Visa . Mater Card . 1234"
                )
                Text(
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.Gray_G100),
                    text = "Today 11:00 - Received"
                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextFormaterUSA(
                    balance = 100.0,
                    fontSize = 16, color = colorResource(id = R.color.Beige),
                    fontWeight = FontWeight.Medium)
            }

            Column(modifier = Modifier
                .padding(start = 40.dp, top = 8.dp)) {

                Image(modifier = Modifier
                    .padding(start = 30.dp)
                    .size(24.dp)
                    ,painter = painterResource(id = R.drawable.next)
                    , contentDescription = null)

                Spacer(modifier = Modifier.padding(8.dp))

                Image(modifier = Modifier
                    .height(19.dp)
                    .width(80.dp)
                    ,painter = painterResource(id = R.drawable.field_transaction)
                    , contentDescription = null)
            }
        }
    }
}



@Preview(showBackground = true, device = "id:pixel_6a")
@Composable
fun TransActionScreenPreview() {
   TransActionScreen(navController = rememberNavController())
}