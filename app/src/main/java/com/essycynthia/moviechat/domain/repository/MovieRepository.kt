package com.essycynthia.moviechat.domain.repository

import com.essycynthia.moviechat.data.MovieApi
import com.essycynthia.moviechat.data.dto.requests.LoginRequest
import com.essycynthia.moviechat.data.dto.requests.RegisterRequest
import com.essycynthia.moviechat.data.dto.responses.LoginResponse
import com.essycynthia.moviechat.data.dto.responses.LogoutResponse
import com.essycynthia.moviechat.data.dto.responses.RegisterResponse
import com.essycynthia.moviechat.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApi: MovieApi) {

    suspend fun registerUser(registerRequest: RegisterRequest): Resource<RegisterResponse> {
        return try {
            val response = movieApi.registerUser(registerRequest)

            Resource.Success(response)

        } catch (e: IOException) {
            Resource.Error("Network error")
        } catch (e: HttpException) {
            Resource.Error("HTTP error")
        }
    }

    suspend fun loginUser(loginRequest: LoginRequest): Resource<LoginResponse> {
        return try {
            val response = movieApi.loginUser(loginRequest)
            Resource.Success(response)
        } catch (e: IOException) {
            Resource.Error("Network error")
        } catch (e: HttpException) {
            Resource.Error("HTTP error")
        }
    }

    suspend fun logoutUser(): Resource<LogoutResponse> {
        return try {
            val response = movieApi.logoutUser()

            Resource.Success(response)


        } catch (e: IOException) {
            Resource.Error("Network error")
        } catch (e: HttpException) {
            Resource.Error("HTTP error")
        }
    }
}
