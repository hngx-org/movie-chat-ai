package com.essycynthia.moviechat.ui.home_screens

import com.shegs.hng_auth_library.model.AuthResponse

data class ChatScreenState(
    val isLoading: Boolean = false,
    val error: String? = "",
    val botResponse : String? = "",
    val userPrompt : String? = "",
)
