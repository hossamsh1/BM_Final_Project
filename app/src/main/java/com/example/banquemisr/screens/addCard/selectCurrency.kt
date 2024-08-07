package com.example.banquemisr.screens.addCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.bm_app.approutes.AppRoutes.ADD_CARD_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCurrencyScreen(navController: NavController, modifier: Modifier = Modifier) {

    val selectedCurrency = remember { mutableStateOf("") }

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
                            "Select Currency",
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
        TransparentList(
            items = listOf("United States", "United Kingdom", "Egypt"),
            selectedItem = selectedCurrency,
            innerPadding = innerPadding,
            navController=navController
        )
    }
}

@Composable
fun TransparentList(
    items: List<String>,
    selectedItem: MutableState<String>,
    innerPadding: PaddingValues,
    navController: NavController,
    modifier: Modifier = Modifier


) {
    var background = Brush.verticalGradient(
        listOf(colorResource(id = R.color.Greadient2), colorResource(id = R.color.Gredient)),
        startY = 2000f,
        endY = 0f
    )

    Column(modifier = modifier
        .padding(innerPadding)
        .background(background)
        .fillMaxSize()) {
        Spacer(modifier = Modifier.height(20.dp))
        items.forEach { item ->
            Column {
                ListItem(
                    item = item,
                    isSelected = item == selectedItem.value,
                    onItemSelected = {
                        selectedItem.value = it
                    }
                )
                 // Transparent divider

            }

        }
        Spacer(modifier = Modifier.height(400.dp))
        Button(
            onClick = {navController.navigate("${ADD_CARD_ROUTE}")}


            ,
            modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .size(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.Beige)
            ),
            shape = RoundedCornerShape(9.dp),
            enabled = selectedItem.value.isNotEmpty()
        ) {
            Text(
                text = "Get Started",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color = Color.White

            )

        }
    }
}

@Composable
fun ListItem(
    item: String,
    isSelected: Boolean,
    onItemSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemSelected(item) }
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .background(Color.Transparent), // Transparent background
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
     Row {

         Icon(
             painter = painterResource(id = R.drawable.united_states), // Replace with actual icons
             contentDescription = null,
             modifier = Modifier
                 .size(24.dp)
                 .padding(end = 8.dp)
         )
         Text(
             text = item,
             fontSize = 16.sp,
             color = Color.Gray
         )
     }
        if (isSelected) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                tint = colorResource(id = R.color.Beige),
                modifier = Modifier.size(24.dp)
            )
        }

    }
    Spacer(modifier = Modifier.height(5.dp))
    Divider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 1.dp, modifier = Modifier.padding(horizontal = 20.dp))
}

@Preview
@Composable
fun SelectCurrencyScreenPreview() {
    SelectCurrencyScreen(navController = rememberNavController())
}
