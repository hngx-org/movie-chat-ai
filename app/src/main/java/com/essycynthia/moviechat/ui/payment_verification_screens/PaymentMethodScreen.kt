package com.essycynthia.moviechat.ui.payment_verification_screens

import android.service.autofill.DateTransformation
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.essycynthia.moviechat.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodScreen(){
    var cardNumber by remember {mutableStateOf("")}
    var date by remember {mutableStateOf("")}
    var ccv by remember {mutableStateOf("")}
    var name by remember {mutableStateOf("")}
    var postCode by remember {mutableStateOf("")}
    Surface {
        Column (
            verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.padding(horizontal = 30.dp)
        ){
            Card (
                modifier = Modifier.fillMaxWidth().height(120.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.mastercard_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            OutlinedTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                supportingText = { Text(text = "Card Number")},
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = date,
                    onValueChange = {
                                    if (it.length <= 8) date = it
                                    },
                    supportingText = { Text(text = "mm/yyyy")},
                    modifier = Modifier.weight(0.5F),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = ccv,
                    onValueChange = {ccv = it},
                    supportingText = { Text(text = "ccv")},
                    modifier = Modifier.weight(0.5F),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            OutlinedTextField(
                value = name,
                onValueChange = {name = it},
                supportingText = { Text(text = "Client")},
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
            )
            OutlinedTextField(
                value = postCode,
                onValueChange = {postCode = it},
                supportingText = { Text(text = "Postal Address Code")},
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF209AFD)),
                modifier = Modifier.fillMaxWidth().height(50.dp),
            ) {
                Text(text = "Make Payment")
            }
        }
    }
}