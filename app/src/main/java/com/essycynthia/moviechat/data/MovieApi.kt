package com.essycynthia.moviechat.data

import com.essycynthia.moviechat.data.dto.requests.LoginRequest
import com.essycynthia.moviechat.data.dto.requests.RegisterRequest
import com.essycynthia.moviechat.data.dto.responses.LoginResponse
import com.essycynthia.moviechat.data.dto.responses.LogoutResponse
import com.essycynthia.moviechat.data.dto.responses.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MovieApi {
    @POST("/api/auth/register")
    fun registerUser(@Body request: RegisterRequest): RegisterResponse

    @POST("/api/auth/login")
    fun loginUser(@Body request: LoginRequest): LoginResponse

    @GET("/api/auth/logout")
    suspend fun logoutUser(): LogoutResponse

}