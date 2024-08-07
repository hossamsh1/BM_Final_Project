package com.example.banquemisr.screens.functionsusable


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banquemisr.R


data class ImageWithText(var painter: Painter, var text: String)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuBox(onCurrencySelected: (String) -> Unit) {


    var country = listOf(
        ImageWithText(painter = painterResource(id = R.drawable.unitedstates_image), text = "USD"),
        ImageWithText(painter = painterResource(id = R.drawable.egypt_image), text = "EGP")
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(country[0]) }

    Box(
        modifier = Modifier
            .height(70.dp)
            .width(170.dp)
            .padding(20.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            BasicTextField(
                value = selectedItem.text ,
                onValueChange = {selectedItem=selectedItem.copy(text = it)},
                readOnly = true,
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.Beige)
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 5.dp)
                            .menuAnchor()
                            .clickable { expanded = !expanded }
                    ) {
                        Image(
                            painter = selectedItem.painter,
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 8.dp)
                        )
                        Row (modifier = Modifier.width(50.dp)){
                            innerTextField()
                        }

                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded,)
                    }
                },
                modifier = Modifier
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                modifier = Modifier.background(Color.White),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                country.forEach { item ->
                    DropdownMenuItem(
                        text = { ImageWithTextRow(item.painter, item.text) },
                        onClick = {
                            selectedItem = item
                            expanded = false
                            onCurrencySelected(item.text)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewExposedDropdownMenuBox() {
    ExposedDropdownMenuBox(onCurrencySelected = {})
}

@Composable
fun ImageWithTextRow(painter: Painter, text: String) {
    Row {
        Image(
            painter = painter,
            modifier = Modifier
                .size(30.dp)
                .padding(end = 8.dp),
            contentDescription = null
        )
        Text(
            text = text,
            fontSize = 25.sp,
            color = colorResource(id = R.color.Beige),
            modifier = Modifier.padding(start = 1.dp)
        )
    }
}



