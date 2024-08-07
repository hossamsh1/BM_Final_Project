package com.example.banquemisr.ui.screens.notification

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.banquemisr.R
import com.example.banquemisr.screens.functionsusable.TextFormaterUSA

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController) {


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
                        "Notification",
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

        }

        ListTransactionSuccessfulNotification()
    }
}


@Composable
fun ListTransactionSuccessfulNotification() {

 var transferAmount by remember { mutableStateOf(0.0) }
 var transferDate by remember { mutableStateOf("") }
 var transferAccount by remember { mutableStateOf("xxx1234") }
 var transferName by remember { mutableStateOf("sarah shaaban") }


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
                    .padding(top = 0.dp)
                    .size(56.dp),
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.receve_notification),
                contentDescription = null
            )

            Column(
                modifier = Modifier.padding(
                    start = 32.dp,
                    top = 0.dp,
                    bottom = 5.dp
                )
            ) {

                Text(
                    color = colorResource(id = R.color.Gray_G900),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    text = "Receive Transaction"
                )
                Text(
                    color = colorResource(id = R.color.Gray_G700),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    text = "You have received $transferAmount from $transferName $transferAccount"
                )
                Text(
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.Gray_G100),
                    text = "Today 11:00 - Received"
                )

            }
        }
        Spacer(modifier = Modifier.padding(5.dp))
    }
}



@Preview(showBackground = true, device = "id:pixel_6a")
@Composable
fun TransActionScreenPreview() {
    NotificationScreen(navController = rememberNavController())
}