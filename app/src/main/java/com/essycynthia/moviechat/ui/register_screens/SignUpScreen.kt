package com.essycynthia.moviechat.ui.register_screens


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.essycynthia.moviechat.R
import com.shegs.hng_auth_library.model.SignupRequest

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun SignUpScreen(

    navigateToChat: () -> Unit,

    viewModel: SignUpScreenViewModel = hiltViewModel(),

    ) {
    var signUpEmail by remember { mutableStateOf("") }
    var signUpPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var signUpName by remember { mutableStateOf("") }
    var sigUpConfirmPassword by remember { mutableStateOf("") }
    val mContext = LocalContext.current
    val state = viewModel.userSignUpState.collectAsState()



    Scaffold(

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                SignUpAnimation()

            }


            item {

                Column {


                    Text(
                        text = "Welcome, Good to see you!!",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsbold))
                    )

                    Text(
                        text = "Create your Account to get Started",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsemibold))
                    )

                }

            }
            ///check


            item {


                Box(
                    modifier = Modifier
                        //check
                        .height(400.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(topStart = 100.dp, bottomEnd = 100.dp)
                        )
                        // .padding(15.dp)
                        .fillMaxWidth(),

                    contentAlignment = Alignment.Center,


                    ) {
                    Column {

//name
                        TextField(value = signUpName, onValueChange = {
                            signUpName = it
                        },
                            modifier = Modifier
                                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
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
                                    imageVector = Icons.Default.ManageAccounts,
                                    contentDescription = "name",
                                    modifier = Modifier.size(20.dp)
                                )

                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            placeholder = {
                                Text(
                                    text = "Enter your Name",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily(Font(R.font.poppinsemibold))
                                )
                            }


                        )
                        ///
                        Spacer(modifier = Modifier.height(20.dp))

//email
                        TextField(value = signUpEmail, onValueChange = {
                            signUpEmail = it
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
                                cursorColor = MaterialTheme.colorScheme.primary,
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
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Done
                            ),
                            placeholder = {
                                Text(
                                    text = "Enter your Email",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily(Font(R.font.poppinsemibold)),
                                    color = MaterialTheme.colorScheme.onPrimary

                                )
                            }


                        )
                        ///
                        Spacer(modifier = Modifier.height(20.dp))

//password
                        TextField(value = signUpPassword, onValueChange = {
                            signUpPassword = it
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
                                cursorColor = MaterialTheme.colorScheme.primary,
                                containerColor = if (isSystemInDarkTheme()) Color.White else Color.Black,

                                ),

                            visualTransformation = if (passwordVisible) VisualTransformation.None else
                                PasswordVisualTransformation(),

                            trailingIcon = {

                                val image = if (passwordVisible)
                                    Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff

                                // Localized description for accessibility services
                                val description =
                                    if (passwordVisible) "Hide password" else "Show password"

                                // Toggle button to hide or display password
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = image, description,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "pass",
                                    modifier = Modifier.size(20.dp)
                                )

                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            placeholder = {
                                Text(
                                    text = "Enter your password",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily(Font(R.font.poppinsemibold))
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(30.dp))
                        //confrim pasword
                        TextField(value = sigUpConfirmPassword, onValueChange = {
                            sigUpConfirmPassword = it
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
                                cursorColor = MaterialTheme.colorScheme.primary,
                                containerColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                            ),
                            visualTransformation = if (passwordVisible) VisualTransformation.None else
                                PasswordVisualTransformation(),

                            trailingIcon = {

                                val image = if (passwordVisible)
                                    Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff

                                // Localized description for accessibility services
                                val description =
                                    if (passwordVisible) "Hide password" else "Show password"

                                // Toggle button to hide or display password
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = image, description,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "pass",
                                    modifier = Modifier.size(20.dp)
                                )

                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            placeholder = {
                                Text(
                                    text = "Confirm password",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily(Font(R.font.poppinsemibold))
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        val passwordRegex = Regex("[A-Za-z0-9]+")
                        Button(
                            onClick = {
                                if (signUpEmail.isEmpty() || signUpPassword.isEmpty() || signUpName.isEmpty() || sigUpConfirmPassword.isEmpty()) {
                                    Toast.makeText(
                                        mContext,
                                        "Please fill all the fields",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(signUpEmail)
                                        .matches()
                                ) {
                                    Toast.makeText(
                                        mContext,
                                        "Provide a valid email address!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                else if (signUpPassword.length<8)
                                 {
                                    Toast.makeText(
                                        mContext,
                                        "Password should have a minimum of 8 characters",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                else if (signUpPassword != sigUpConfirmPassword) {
                                    Toast.makeText(
                                        mContext,
                                        "Password and confirm password does not match",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                              else  if (!passwordRegex.matches(signUpPassword)) {
                                    // Password contains special characters
                                    Toast.makeText(
                                        mContext,
                                        "Password should not contains special characters",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else if (!passwordRegex.matches(sigUpConfirmPassword)) {
                                    // Confirm password contains special characters
                                    Toast.makeText(
                                        mContext,
                                        "Confirm password contains special characters",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                else {
                                    val registerRequest = SignupRequest(
                                        password = signUpPassword,
                                        email = signUpEmail,
                                        name = signUpName,
                                        confirm_password = sigUpConfirmPassword
                                    )

                                    viewModel.signup(registerRequest)
                                }

                            },

                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(start = 20.dp, end = 20.dp, bottom = 15.dp)
                                .width(104.dp)
                                .height(40.dp),

                            shape = RoundedCornerShape(18.dp),
                            colors = ButtonDefaults.buttonColors(Color.White)

                        ) {
                            Text(
                                text = "Sign Up",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontFamily = FontFamily(Font(R.font.poppinsemibold))
                            )
                        }
                        if (state.value.isLoading) {
                            CircularProgressIndicator()
                        } else if (state.value.success != null) {
                            navigateToChat()
                        } else if (state.value.success == null) {
//

                        }

                    }
                }
            }
        }
    }
}

@Composable
fun SignUpAnimation() {

    val bounceAnimation = rememberInfiniteTransition()
    val translateY by bounceAnimation.animateFloat(
        initialValue = -16f,
        targetValue = 16f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "Animation"
    )

    val scaleAnimation = rememberInfiniteTransition()
    val scale by scaleAnimation.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier

            .fillMaxWidth(),
        contentAlignment = Alignment.Center

    ) {
        Icon(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = "Circle",
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
            modifier = Modifier

                .size(160.dp)
                .scale(scale)

        )
        Image(
            painter = painterResource(
                id = R.drawable.ava2
            ),
            contentDescription = "Avatar",
            modifier = Modifier
                .clip(RoundedCornerShape(180.dp))
                .size(100.dp)
                .graphicsLayer {
                    translationY = translateY // Apply translationY to the icon
                }
        )

    }
}


/*


@Preview
@Composable
fun MyPreview() {
    SignUpScreen(
        {}
    )
}*/
