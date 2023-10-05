package com.essycynthia.moviechat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.essycynthia.moviechat.ui.home_screens.ChatScreen
import com.essycynthia.moviechat.ui.navigation.ChatScreen
import com.essycynthia.moviechat.ui.navigation.MovieRecommenderApp
import com.essycynthia.moviechat.ui.navigation.Payment
import com.essycynthia.moviechat.ui.payment_verification_screens.PaymentMethodScreen
import com.essycynthia.moviechat.ui.theme.MovieChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val navController = rememberNavController()
//                    NavHost(navController = navController, startDestination = ChatScreen.route){
//                        composable(ChatScreen.route){
//                            ChatScreen (
//                                navigateToPayment = {navController.navigate(Payment.route)},
//                            )
//
//                        }
//                        composable(Payment.route){
//                            PaymentMethodScreen()
//                        }
//                    }

                    MovieRecommenderApp()
                  //  SignUpScreen()
                    //LoginScreen()
                   // ResetPasswordScreen()
                   // ForgotPasswordScreen()
                }
            }
        }
    }
}

