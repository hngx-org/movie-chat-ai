package com.essycynthia.moviechat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.essycynthia.moviechat.ui.navigation.MovieRecommenderApp
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
                    MovieRecommenderApp()
                 //   ChatScreen()
                  //  SignUpScreen()
                    //LoginScreen()
                   // ResetPasswordScreen()
                   // ForgotPasswordScreen()
                }
            }
        }
    }
}

