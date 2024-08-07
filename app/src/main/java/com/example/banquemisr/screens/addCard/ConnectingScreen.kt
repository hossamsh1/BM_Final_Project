import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banquemisr.R
import com.example.bm_app.approutes.AppRoutes.OTP_ROUTE


@Composable
fun DynamicLoadingScreen(navController: NavController) {
    val progress = remember { mutableStateOf(0f) }

    // Simulate loading progress
    LaunchedEffect(key1 = progress.value) {
        if (progress.value < 1f) {
            kotlinx.coroutines.delay(100)
            progress.value += 0.05f
        }
        else {
            navController.navigate(OTP_ROUTE) }
     
    }

    var background = Brush.verticalGradient(
        listOf(colorResource(id = R.color.Greadient2), colorResource(id = R.color.Gredient)),
        startY = 2000f,
        endY = 0f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(200.dp)
            ) {
                CircularProgressIndicator(
                    progress = progress.value,
                    color = Color(0xFFB71C1C), // Dark red color
                    strokeWidth = 8.dp,
                    modifier = Modifier.size(180.dp),
                    backgroundColor = colorResource(id = R.color.transRed)
                )
                Text(
                    text = "Speedo \n Transfer",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,


                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Connecting to Speedo Transfer\nCredit card",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewDynamicLoadingScreen() {
    DynamicLoadingScreen(rememberNavController())
}