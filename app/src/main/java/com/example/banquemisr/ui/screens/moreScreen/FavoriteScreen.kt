package com.example.banquemisr.ui.screens.moreScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.banquemisr.ui.screens.profileScreen.color
import com.example.banquemisr.ui.screens.reusableUI.ScreenHeader

@Composable
fun FavoritesScreen(navController: NavController) {
val background = Brush.verticalGradient(
    listOf(colorResource(id = R.color.Greadient2), colorResource(id = R.color.Gredient)),
    startY = 2000f,
    endY = 0f
)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        ScreenHeader("favorites", onClick = { navController.popBackStack() })
Row (modifier = Modifier
    .align(Alignment.CenterHorizontally)
    .wrapContentSize()){
    Text(
        modifier = Modifier
            .align(Alignment.CenterVertically)
        ,text = "Your favorites Screen"
    , fontSize =20.sp
    , color = colorResource(id = R.color.Gray_G900))
}
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, start = 16.dp, top = 16.dp, bottom = 10.dp)
            .background(Color.White)) {
            Row(modifier = Modifier
                .wrapContentSize()
                .padding(end = 16.dp, start = 16.dp, top = 16.dp))
            {

                Box(
                    modifier = Modifier
                        .padding(top = 5.dp, start = 8.dp, bottom = 5.dp)
                        .size(48.dp)
                        .background(Color.LightGray, shape = CircleShape)
                        .clip(CircleShape)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.icon_banque),
                        contentDescription = null
                    )
                }

                Column(
                    modifier = Modifier.padding(
                        start = 20.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                ) {

                    Text(
                        color = colorResource(id = R.color.Gray_G900),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        text = "Receive Transaction"
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.Gray_G100),
                        text = "Today 11:00 - Received"
                    )

                }
            }
            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
}


@Preview
@Composable
fun FavoritesScreenPreview() {
    val navController = rememberNavController()
    FavoritesScreen(navController = navController)
}