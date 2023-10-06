package com.essycynthia.moviechat.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.essycynthia.moviechat.ui.home_screens.ChatScreen
import com.essycynthia.moviechat.ui.password_screens.ForgotPasswordScreen
import com.essycynthia.moviechat.ui.login_screens.LoginScreen
import com.essycynthia.moviechat.ui.password_screens.ResetPasswordScreen
import com.essycynthia.moviechat.ui.payment_verification_screens.NewPaymentMethodScreen
import com.essycynthia.moviechat.ui.register_screens.SignUpScreen
import com.essycynthia.moviechat.ui.payment_verification_screens.PaymentMethodScreen
import com.essycynthia.moviechat.ui.payment_verification_screens.VerificationColumn

/*enum class NavigationRoutes(val title: String){
    SIGNUP(""),
    LOGIN(""),

    FORGOT("Forgot Password"),
    RESET("Reset Password"),
    VERIFICATION("Verify your Account"),
    PAYMENT("Add Payment Method"),
    CHAT_SCREEN("")
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRecommenderApp(
    navController: NavHostController = rememberNavController()
){

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val currentScreen = movieAppScreens.find { it.route == currentDestination?.route }?: Login
    Scaffold(
        topBar = {
            MovieRecommenderTopAppBar(currentScreen = currentScreen) { navController.navigateUp() }
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Login.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = Login.route){
                LoginScreen(
                    navigateToForgot = { navController.navigateSingleTopTo(Forgot.route) },
                    navigateWithUserId = {userId ->
                        navController.navigateSingleTopTo("${ChatScreen.route}/$userId")
                    },
                    navigateToSignUp = {navController.navigateSingleTopTo(Signup.route)}
                )
            }
            composable(route = Forgot.route){
                ForgotPasswordScreen(navigateToVerification = { navController.navigateSingleTopTo(Verification.route) })
            }
            composable(route = Verification.route){
                VerificationColumn(
                    navigateToReset = {navController.navigateSingleTopTo(Reset.route)}
                )
            }
            composable(route = Reset.route){
                ResetPasswordScreen(
                    navigateToLogin = { navController.navigateSingleTopTo(Login.route) }
                )
            }
            composable(route = Signup.route){
                SignUpScreen(
                    navigateToChat = {navController.navigateSingleTopTo(Login.route)},
                )
            }
            composable(
                route = ChatScreen.routeWithArgs,
                arguments = ChatScreen.arguments
            ){navBackStackEntry ->
                val userId = navBackStackEntry.arguments?.getString(ChatScreen.chatScreenArg)
                ChatScreen(
                    navigateToPayment = {navController.navigateSingleTopTo(Payment.route)},
                    userId = userId
                )
            }

            composable(route = Payment.route){
                PaymentMethodScreen()
               // NewPaymentMethodScreen()
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route){launchSingleTop = true}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRecommenderTopAppBar(
    currentScreen: Destinations,
    navigateUp: () -> Unit
){
    TopAppBar(
        title = {
            Text(text = currentScreen.title)
        },
        navigationIcon = {
            //if the current screen is not chatscreen or login screen(the two screens that dont require back button) then display back icon in top app bar
            if (currentScreen != ChatScreen && currentScreen != Login){
                IconButton(onClick = navigateUp){
                    Icon(Icons.Default.ArrowBack, contentDescription = null)}
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
    )
}