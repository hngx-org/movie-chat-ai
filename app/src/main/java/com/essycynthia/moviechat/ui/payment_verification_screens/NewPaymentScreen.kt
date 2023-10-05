package com.essycynthia.moviechat.ui.payment_verification_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.essycynthia.moviechat.R
import com.essycynthia.moviechat.ui.payment_verification_screens.payment_utils.FieldType
import com.essycynthia.moviechat.ui.payment_verification_screens.payment_utils.InputTransformation
import com.essycynthia.moviechat.ui.payment_verification_screens.payment_utils.InputValidator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPaymentMethodScreen(
    cardViewModel: PaymentViewModel = viewModel()
) {
    val focusHolderName = FocusRequester()
    val focusExpiration = FocusRequester()
    val focusCVC = FocusRequester()
    var scrollState = rememberScrollState()
    // Surface {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            20.dp,
            alignment = Alignment.CenterVertically
        ),
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(top = 38.dp)
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
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { state ->
                    if (state.isFocused) cardViewModel.flipped = false
                },
            value = cardViewModel.number,
            label = {Text(text = "Number of card")},
            visualTransformation = InputTransformation(FieldType.CARD_NUMBER),
            onValueChange = {
                cardViewModel.number =
                    if (cardViewModel.number.length >= 16) cardViewModel.number.substring(0..15) else it

                // When value is completed, request focus of next field
                if (cardViewModel.number.length >= 16) focusHolderName.requestFocus()
            },
            shape = RoundedCornerShape(10.dp),
            trailingIcon = {
                CustomTextFieldDeleteIcon(value = cardViewModel.number) {
                    cardViewModel.number = ""
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { state ->
                    if (state.isFocused) cardViewModel.flipped = false
                }
                .focusRequester(focusHolderName)
                .testTag("tCardHolder"),
            value = cardViewModel.name,
            label = {Text(text = "Name of card")},
            onValueChange = {
                InputValidator.parseHolderName(it)?.let { name ->
                    cardViewModel.name = name
                }
            },
            shape = RoundedCornerShape(10.dp),
            trailingIcon = {
                CustomTextFieldDeleteIcon(value = cardViewModel.name) {
                    cardViewModel.name = ""
                }
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = cardViewModel.expiration,
                onValueChange = {
                    cardViewModel.expiration = if (it.length >= 4) it.substring(0..3) else it

                    // When value is completed, request focus of next field
                    if (cardViewModel.expiration.length >= 4) focusCVC.requestFocus()
                },
                modifier = Modifier
                    .weight(0.5f)
                    .onFocusEvent { state ->
                        if (state.isFocused) cardViewModel.flipped = false
                    }
                    .focusRequester(focusExpiration)
                    .testTag("tExpiration"),

                label = {Text(text = "Expiration")},
                visualTransformation = InputTransformation(FieldType.EXPIRATION),
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    CustomTextFieldDeleteIcon(value = cardViewModel.expiration) {
                        cardViewModel.expiration = ""
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                /*nextFocus = focusCVC*/
            )
            OutlinedTextField(
                modifier = Modifier
                    .weight(0.5f)
                    .onFocusEvent { state ->
                        if (state.isFocused) cardViewModel.flipped = true
                    }
                    .focusRequester(focusCVC)
                    .testTag("tSecurityCode"),
                value = cardViewModel.cvc,
                label = {Text(text = "CVC")},
                shape = RoundedCornerShape(10.dp),
                onValueChange = {
                    InputValidator.parseCVC(it)?.let { cvc ->
                        cardViewModel.cvc = cvc
                    }
                },
                trailingIcon = {
                    CustomTextFieldDeleteIcon(value = cardViewModel.cvc) {
                        cardViewModel.cvc = ""
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

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

@Composable
fun CustomTextFieldDeleteIcon(
    value: String,
    onClick: () -> Unit
) {
    if (value.isNotBlank()) {
        Box(
            modifier = Modifier
                .height(30.dp)
                .padding(2.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable { onClick() }
                .testTag("deleteIconContainer")
        ) {
            Icon(
                modifier = Modifier
                    .padding(5.dp)
                    .testTag("deleteIcon"),
                painter = painterResource(id = R.drawable.ic_baseline_close_24),
                contentDescription = null
            )
        }
    }
}
