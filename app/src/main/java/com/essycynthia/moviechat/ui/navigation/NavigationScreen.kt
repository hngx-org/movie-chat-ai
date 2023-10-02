package com.essycynthia.moviechat.ui.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.essycynthia.moviechat.ui.home_screens.ChatScreen
import com.essycynthia.moviechat.ui.login_screens.ForgotPasswordScreen
import com.essycynthia.moviechat.ui.login_screens.LoginScreen
import com.essycynthia.moviechat.ui.login_screens.ResetPasswordScreen
import com.essycynthia.moviechat.ui.login_screens.SignUpScreen
import com.essycynthia.moviechat.ui.payment_verification_screens.PaymentMethodScreen
import com.essycynthia.moviechat.ui.payment_verification_screens.VerificationColumn

enum class NavigationRoutes(val title: String){
    SIGNUP(""),
    LOGIN(""),

    FORGOT("Forgot Password"),
    RESET("Reset Password"),
    VERIFICATION("Verify your Account"),
    PAYMENT("Add Payment Method"),
    CHAT_SCREEN("")
}

/*sealed class NavigationDestination(val route: String){
    object SignUp : NavigationDestination("")
    object Login : NavigationDestination("Login")
    object Verification : NavigationDestination("Verify your Account")
    object Payment : NavigationDestination("Payment")
    object Home : NavigationDestination("Home")
}**/



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRecommenderApp(
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NavigationRoutes.valueOf(backStackEntry?.destination?.route?: NavigationRoutes.SIGNUP.name)
    Scaffold(
        topBar = {
                 MovieRecommenderTopAppBar(currentScreen = currentScreen) { navController.navigateUp() }
        },
        bottomBar = {
            MovieRecommenderBottomAppBar(
                currentScreen = currentScreen,
                navigateToHome = {navController.navigate(NavigationRoutes.CHAT_SCREEN.name)},
                navigateToPayment = {navController.navigate(NavigationRoutes.PAYMENT.name)},
                navigateToVerify = {navController.navigate(NavigationRoutes.VERIFICATION.name)}
            )}
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.LOGIN.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(NavigationRoutes.LOGIN.name){
                LoginScreen(
                    navigateToForgot = { navController.navigate(NavigationRoutes.FORGOT.name) },
                    navigateToChat = {navController.navigate(NavigationRoutes.CHAT_SCREEN.name)},
                    navigateToSignUp = {navController.navigate(NavigationRoutes.SIGNUP.name)}
                    )
            }
            composable(NavigationRoutes.FORGOT.name){
                ForgotPasswordScreen(navigateToResetPassword = { navController.navigate(NavigationRoutes.RESET.name) })
            }
            composable(NavigationRoutes.RESET.name){
                ResetPasswordScreen(
                    navigateToLogin = { navController.navigate(NavigationRoutes.LOGIN.name) }
                )
            }
            composable(NavigationRoutes.SIGNUP.name){
                SignUpScreen(
                    navigateToVerify = {navController.navigate(NavigationRoutes.VERIFICATION.name)}
                )
            }
            composable(NavigationRoutes.VERIFICATION.name){
                VerificationColumn(
                    navigateToChat = {navController.navigate(NavigationRoutes.CHAT_SCREEN.name)}
                )
            }
            composable(NavigationRoutes.CHAT_SCREEN.name){
                ChatScreen()
            }

            composable(NavigationRoutes.PAYMENT.name){
                PaymentMethodScreen()
            }
        }
    }
}

@Composable
fun MovieRecommenderBottomAppBar(
    navigateToHome:()->Unit,
    navigateToPayment:() -> Unit,
    navigateToVerify:()->Unit,
    currentScreen: NavigationRoutes
){
    if (currentScreen == NavigationRoutes.CHAT_SCREEN || currentScreen == NavigationRoutes.PAYMENT ){
        BottomAppBar(
            containerColor = Color(0xFF209AFD),
            modifier = Modifier.height(60.dp)
        ){
            Row() {
                NavigationBarItem(selected = currentScreen.name.contains(NavigationRoutes.CHAT_SCREEN.name), onClick = navigateToHome, icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) })
                NavigationBarItem(selected = currentScreen.name.contains(NavigationRoutes.PAYMENT.name), onClick = navigateToPayment, icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null) })
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRecommenderTopAppBar(
    currentScreen: NavigationRoutes,
    navigateUp: () -> Unit
){
    TopAppBar(
        title = {
            Text(text = currentScreen.title)
        },
        navigationIcon = {
            if (currentScreen != NavigationRoutes.CHAT_SCREEN && currentScreen != NavigationRoutes.LOGIN){
                IconButton(onClick = navigateUp){
                    Icon(Icons.Default.ArrowBack, contentDescription = null)}
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
    )
}