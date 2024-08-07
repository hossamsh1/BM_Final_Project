import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.banquemisr.R
import com.example.banquemisr.models.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteListModalBottomSheetContent(viewModel: FavoriteViewModel = viewModel(), token: String, onDismiss: () -> Unit) {
LaunchedEffect(Unit) {
    viewModel.getFavorites(token)
}


    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_favorite_stare),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(18.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                modifier = Modifier,
                fontSize = 20.sp,
                text = "Favourite List",
                color = colorResource(id = R.color.Beige)
            )
        }

        LazyColumn {
            items(viewModel.favoriteList) { favorite ->
                FavouriteItem(name = favorite.fullName, account = favorite.accountNumber,
//                    onDelete = {
//                    viewModel.deleteFavorite(token, favorite.id)
               // }
             )
            }
        }

        FavouriteItem(name = "hossam", account = "123456")
        FavouriteItem(name = "mohamed", account = "23456")
    }
}

@Composable
fun FavouriteItem(name: String, account: String) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .background(colorResource(id = R.color.card_color_list))
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp)
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

            Spacer(modifier = Modifier.width(12.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 18.dp)
            ) {
                Text(color = colorResource(id = R.color.Gray_G900), fontWeight = FontWeight.Medium, fontSize = 16.sp, text = name)
                Spacer(modifier = Modifier.padding(8.dp))
                Text(color = colorResource(id = R.color.Gray_G100), fontWeight = FontWeight.Medium, fontSize = 16.sp, text = account)
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    FavouriteListModalBottomSheetContent(onDismiss = {}, token = "sample_token")
}
