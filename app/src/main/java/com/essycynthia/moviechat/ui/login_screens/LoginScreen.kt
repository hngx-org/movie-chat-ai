package com.essycynthia.moviechat.ui.login_screens


import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.essycynthia.moviechat.R


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun LoginScreen(
    //navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Scaffold() {
        LazyColumn(
            modifier = Modifier

                .fillMaxSize(),

            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                LoginAnimation()


            }


            //   Spacer(modifier = Modifier.height(20.dp))
            item {
                Column {
                    Text(
                        text = "Welcome, Good to see you!!",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsbold))
                    )

                    Text(
                        text = "Login to your Account",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsemibold))
                    )

                }

            }
            item {


                Box(
                    modifier = Modifier
                        .height(320.dp)
                        .background(
                            Color(0xFF209AFD),
                            RoundedCornerShape(topStart = 100.dp, bottomEnd = 100.dp)
                        )
                        // .padding(15.dp)
                        .fillMaxWidth(), contentAlignment = Alignment.Center


                ) {
                    Column(
                        modifier = Modifier.padding(top = 35.dp)

                    ) {

                        TextField(value = email,
                            onValueChange = {
                                email = it
                            },
                            modifier = Modifier
                                .padding(start = 20.dp, end = 20.dp)
                                //.fillMaxWidth()
                                .width(300.dp)
                                .height(53.dp),

                            shape = RoundedCornerShape(18.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color(0xFF209AFD),
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
                        ///
                        Spacer(modifier = Modifier.height(30.dp))


                        TextField(value = password,
                            onValueChange = {
                                password = it
                            },
                            modifier = Modifier
                                .padding(start = 20.dp, end = 20.dp)
                                //.fillMaxWidth()
                                .width(300.dp)
                                .height(53.dp),
                            shape = RoundedCornerShape(18.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color(0xFF209AFD),
                                containerColor = Color.White
                            ),
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                            trailingIcon = {

                                val image = if (passwordVisible) Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff

                                // Localized description for accessibility services
                                val description =
                                    if (passwordVisible) "Hide password" else "Show password"

                                // Toggle button to hide or display password
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = image,
                                        description,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "password icon",
                                    modifier = Modifier.size(20.dp)
                                )

                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                            ),
                            placeholder = {
                                Text(
                                    text = "Enter your password",
                                    fontFamily = FontFamily(Font(R.font.poppinsemibold))
                                )
                            })

                        Spacer(modifier = Modifier.height(15.dp))



                        Text(
                            text = "Forgot password?",
                            modifier = Modifier
                                //  .clickable { navController.navigate("forgot") }

                                .padding(start = 20.dp),
                            fontSize = 14.sp,
                            color = Color.White,
                            letterSpacing = 0.07.sp,
                            fontFamily = FontFamily(Font(R.font.poppinsemibold))
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = { /*TODO*/ },

                            modifier = Modifier
                                .align(Alignment.End)

                                .padding(end = 20.dp)
                                .width(102.dp)
                                .height(40.dp),

                            shape = RoundedCornerShape(18.dp),
                            colors = ButtonDefaults.buttonColors(Color.White)

                        ) {
                            Text(
                                text = "Log in",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontFamily = FontFamily(Font(R.font.poppinsemibold))
                            )
                        }

                        Row(
                            //navigate to signup screen
                            //  Modifier.clickable { navController.navigate("signup") }
                        ) {
                            Text(
                                text = "Don't have an Account?",
                                modifier = Modifier

                                    .padding(start = 20.dp, top = 15.dp),
                                fontSize = 14.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.07.sp,
                                fontFamily = FontFamily(Font(R.font.poppinsemibold))
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "Sign Up",
                                modifier = Modifier.padding(top = 15.dp),


                                fontSize = 14.sp,
                                color = Color(0xFFFFE6A5),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.07.sp,
                                fontFamily = FontFamily(Font(R.font.poppinsemibold))
                            )

                        }


                    }


                }

            }



        }

    }


}


@Composable
fun LoginAnimation() {

    val bounceAnimation = rememberInfiniteTransition()
    val translateY by bounceAnimation.animateFloat(
        initialValue = -16f, targetValue = 16f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "Animation"
    )

    val scaleAnimation = rememberInfiniteTransition()
    val scale by scaleAnimation.animateFloat(
        initialValue = 1f, targetValue = 1.2f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier.fillMaxWidth(),
        //.padding(top = 12.dp),
        contentAlignment = Alignment.Center

    ) {
        Icon(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = "Circle",
            tint = Color(0xFF209AFD).copy(alpha = 0.7f),
            modifier = Modifier
                .size(160.dp)
                .scale(scale)

        )
        Image(painter = painterResource(
            id = R.drawable.ava2
        ),
            contentDescription = "Avatar",
            modifier = Modifier
                .clip(RoundedCornerShape(180.dp))
                .size(100.dp)
                .graphicsLayer {
                    translationY = translateY // Apply translationY to the icon
                })

    }
}


@Preview
@Composable
fun MyLogin() {
    LoginScreen()
}