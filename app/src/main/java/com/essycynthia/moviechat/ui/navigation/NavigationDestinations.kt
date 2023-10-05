package com.essycynthia.moviechat.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destinations{
    val route: String
    val title: String
}

object Login : Destinations{
    override val route: String = "login"
    override val title: String = ""
}

object Signup : Destinations{
    override val route: String = "signup"
    override val title: String = ""
}

object Forgot : Destinations{
    override val route: String = "forgot"
    override val title: String = "Forgot Password"
}

object Reset : Destinations{
    override val route: String = "reset"
    override val title: String = "Reset Password"
}

object Verification : Destinations{
    override val route: String = "verification"
    override val title: String = "Verify your Account"
}

object Payment : Destinations{
    override val route: String = "payments"
    override val title: String = "Add Payment Method"
}

object ChatScreen : Destinations{
    override val route: String = "chat-screen"
    override val title: String = ""
    const val chatScreenArg: String = "userId"
    val routeWithArgs = "${route}/{${chatScreenArg}}"
    val arguments = listOf(
        navArgument(chatScreenArg){type = NavType.StringType}
    )
}

val movieAppScreens = listOf(Login, Signup, Forgot, Reset, Verification, Payment, ChatScreen)