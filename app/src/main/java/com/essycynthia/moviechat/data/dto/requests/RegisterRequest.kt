package com.essycynthia.moviechat.data.dto.requests

data class RegisterRequest(
    val confirm_password: String,
    val email: String,
    val name: String,
    val password: String
)