package com.essycynthia.moviechat.ui.register_screens

import com.essycynthia.moviechat.data.dto.responses.RegisterResponse

data class SignUpScreenState(
    val isLoading: Boolean = false,
    val error: String? = "",
    val success: RegisterResponse? = null
)
