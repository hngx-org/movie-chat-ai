package com.essycynthia.moviechat.ui.register_screens

import com.shegs.hng_auth_library.model.AuthResponse
import kotlinx.coroutines.flow.MutableStateFlow

data class SignUpScreenState(
    val isLoading: Boolean = false,
    val error: String? = "",
    val success: AuthResponse? = null
)
