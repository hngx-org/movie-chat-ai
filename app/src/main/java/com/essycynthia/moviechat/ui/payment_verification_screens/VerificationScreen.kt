package com.essycynthia.moviechat.ui.payment_verification_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.essycynthia.moviechat.R

@Composable
fun VerificationColumn(
    focusRequester5: FocusRequester = remember {FocusRequester()},
    navigateToChat: ()->Unit
){
    Surface() {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "Get Your Code")
            Card (
                modifier = Modifier.size(200.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.verification_image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Text(text = "Enter the verification code just sent to your email address", maxLines = 2, style = MaterialTheme.typography.bodyLarge)
            CodeRow(focusRequester5)
            Text(text = "Didn't get a code? Resend")
            Button(
                onClick = { navigateToChat() },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF209AFD)),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester5)
                    .height(50.dp)
            ) {
                Text(text = "Verify")
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeRow(
    focusRequester: FocusRequester
){
    // Make verification screen Ui State which holds the value in the textfields
    var txt1 by remember { mutableStateOf("") }
    var txt2 by remember { mutableStateOf("") }
    var txt3 by remember { mutableStateOf("") }
    var txt4 by remember { mutableStateOf("") }
    val focusRequester1 = remember {FocusRequester()}
    val focusRequester2 = remember {FocusRequester()}
    val focusRequester3 = remember {FocusRequester()}
    val focusRequester4 = remember {FocusRequester()}
    Surface(){
        Row {
            OutlinedTextField(
                value = txt1,
                onValueChange = {
                    if (it.isDigitsOnly()){
                        txt1 = it
                    }
                    if (it.isNotEmpty()){
                        focusRequester2.requestFocus()
                    }
                                },
                modifier = Modifier
                    .padding(5.dp)
                    .focusRequester(focusRequester1)
                    .size(48.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = txt2,
                onValueChange = {
                    if (it.isDigitsOnly()){
                        txt2 = it
                    }
                    focusRequester3.requestFocus()
                                },
                modifier = Modifier
                    .padding(5.dp)
                    .focusRequester(focusRequester2)
                    .size(48.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = txt3,
                onValueChange = {
                    if (it.isDigitsOnly()){
                        txt3 = it
                    }
                    focusRequester4.requestFocus()
                },
                modifier = Modifier
                    .padding(5.dp)
                    .focusRequester(focusRequester3)
                    .size(48.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = txt4,
                onValueChange = {
                    if (it.isDigitsOnly()){
                        txt4 = it
                    }
                },
                modifier = Modifier
                    .padding(5.dp)
                    .focusRequester(focusRequester4)
                    .size(48.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}