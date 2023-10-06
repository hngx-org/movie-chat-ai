package com.essycynthia.moviechat.ui.payment_verification_screens

import android.annotation.SuppressLint
import android.service.autofill.DateTransformation
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.essycynthia.moviechat.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodScreen() {
    var cardNumber by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var ccv by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var postCode by remember { mutableStateOf("") }
    //var scrollState = rememberScrollState()
    //


Scaffold (
    topBar = {
        TopAppBar(title =
        { 
            Text(text = "Add Payment",
                fontFamily = FontFamily(Font(R.font.poppinsemibold)),
              //  fontSize = 17.sp
            )
        })
    }
){


    
    Column(
        verticalArrangement = Arrangement.spacedBy(
            20.dp,
            alignment = Alignment.CenterVertically
        ),
        modifier = Modifier
            //   .verticalScroll(scrollState)
            .padding(top = 50.dp)
            .padding(horizontal = 30.dp,),

        ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.mastercard_image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        OutlinedTextField(
            value = cardNumber,
            onValueChange = { cardNumber = it },

            label = {
                Text(
                    text = "Card Number",
                    //added font family
                    fontFamily = FontFamily(Font(R.font.poppinsemibold)),
                )
            },
            //added font family
            placeholder = {
                Text(
                    text = "1234 5678 9101 1123",
                    fontFamily = FontFamily(Font(R.font.poppinslight))
                )
            },
            modifier = Modifier.fillMaxWidth(),
            //check
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF209AFD),
                containerColor = Color.Transparent,
                cursorColor = Color(0xFF209AFD),
                focusedLabelColor = Color(0xFF209AFD),

                ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1,

            )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = date,
                onValueChange = { if (it.length <= 8) date = it },
                label = {
                    Text(
                        text = "Date",
                        //added font family
                        fontFamily = FontFamily(Font(R.font.poppinsemibold))
                    )
                },
                //added font family
                placeholder = {
                    Text(
                        text = "11/2000",
                        fontFamily = FontFamily(Font(R.font.poppinslight))
                    )
                },
                modifier = Modifier.weight(0.5F),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF209AFD),
                    containerColor = Color.Transparent,
                    cursorColor = Color(0xFF209AFD),
                    focusedLabelColor = Color(0xFF209AFD)
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1
            )
            OutlinedTextField(
                value = ccv,
                onValueChange = { ccv = it },
                label = {
                    Text(
                        text = "CCV",
                        fontFamily = FontFamily(Font(R.font.poppinsemibold))
                    )
                },
                placeholder = {
                    Text(
                        text = "123",
                        fontFamily = FontFamily(Font(R.font.poppinslight))
                    )
                },
                modifier = Modifier.weight(0.5F),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF209AFD),
                    containerColor = Color.Transparent,
                    cursorColor = Color(0xFF209AFD),
                    focusedLabelColor = Color(0xFF209AFD)
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text(
                    text = "Client",
                    fontFamily = FontFamily(Font(R.font.poppinsemibold))
                )
            },
            placeholder = {
                Text(
                    text = "Franklin Okoli",
                    fontFamily = FontFamily(Font(R.font.poppinslight))
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF209AFD),
                containerColor = Color.Transparent,
                cursorColor = Color(0xFF209AFD),
                focusedLabelColor = Color(0xFF209AFD)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1
        )
        OutlinedTextField(
            value = postCode,
            onValueChange = { postCode = it },
            label = {
                Text(
                    text = "Postal Code",
                    fontFamily = FontFamily(Font(R.font.poppinsemibold))
                )
            },
            placeholder = {
                Text(
                    text = "01000001",
                    fontFamily = FontFamily(Font(R.font.poppinslight))
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF209AFD),
                containerColor = Color.Transparent,
                cursorColor = Color(0xFF209AFD),
                focusedLabelColor = Color(0xFF209AFD)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1
        )
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF209AFD)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),

            ) {
            Text(
                text = "Make Payment",
                fontFamily = FontFamily(Font(R.font.poppinsemibold))
            )
        }
    }
}
    
}
  
  
  
    
    
    


  //  }
