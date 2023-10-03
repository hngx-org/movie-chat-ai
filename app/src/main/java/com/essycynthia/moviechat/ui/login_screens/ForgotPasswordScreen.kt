package com.essycynthia.moviechat.ui.login_screens


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.essycynthia.moviechat.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    navigateToVerification: () -> Unit
) {
    var forgotPasswordEmail by remember { mutableStateOf("") }

    Scaffold() {
        LazyColumn(
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.forgotpass),
                    contentDescription = "forgotpass",
                    modifier = Modifier.size(120.dp)
                )
            }



            item {

                Column {


                    Text(
                        text = "Forgot Password?",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.poppinsbold))

                    )

                    Text(
                        text = "Enter  the email address\n " + "assosciated with your account",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center
                    )


                }
            }


            item {
                Box(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .height(180.dp)
                        .background(
                            MaterialTheme.colorScheme.primary, RoundedCornerShape(28.dp)
                        )

                        .fillMaxWidth(),

                    contentAlignment = Alignment.Center,

                    ) {

                    Column {

                        TextField(value = forgotPasswordEmail,
                            onValueChange = {
                                forgotPasswordEmail = it
                            },
                            modifier = Modifier
                                .padding(start = 20.dp, end = 20.dp)
                                .fillMaxWidth()

                                .height(53.dp),
                            shape = RoundedCornerShape(18.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor =MaterialTheme.colorScheme.primary,
                                containerColor = Color.White
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = "email",
                                    modifier = Modifier.size(20.dp)
                                )

                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email, imeAction = ImeAction.Done
                            ),
                            placeholder = {
                                Text(
                                    text = "Enter your Email",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily(Font(R.font.poppinsemibold))
                                )
                            }


                        )
                        Spacer(modifier = Modifier.height(28.dp))

                        Button(
                            onClick = { navigateToVerification() },

                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(start = 20.dp, end = 20.dp)
                                .width(100.dp)
                                .height(40.dp),

                            shape = RoundedCornerShape(18.dp),
                            colors = ButtonDefaults.buttonColors(Color.White)

                        ) {
                            Text(
                                text = "Send",
                                // modifier = Modifier.clickable { navController.navigate("reset") },
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontFamily = FontFamily(Font(R.font.poppinsemibold))

                            )
                        }

                    }


                }

            }


        }

    }
}

@Preview
@Composable
fun MyForgot() {
    ForgotPasswordScreen({})
}