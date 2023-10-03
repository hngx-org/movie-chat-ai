package com.essycynthia.moviechat.ui.login_screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.essycynthia.moviechat.data.dto.requests.LoginRequest
import com.essycynthia.moviechat.data.dto.responses.LoginResponseData
import com.essycynthia.moviechat.domain.repository.MovieRepository
import com.essycynthia.moviechat.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel(){
    private var _uiState = MutableStateFlow(LoginScreenState())
    val uiState: StateFlow<LoginScreenState> = _uiState.asStateFlow()
    var token: LoginResponseData? = null
    val loginRequest = _uiState.value.userDetails.toLoginRequest()

    fun loginUser(loginRequest: LoginRequest): LoginResponseData?{
        viewModelScope.launch {
            when (val response = repository.loginUser(loginRequest)){
                is Resource.Success -> {
                    token = response.data?.data
                    _uiState.value = LoginScreenState(loginSuccess = true, isLoading = false)
                }
                is Resource.Error -> {
                    _uiState.value = LoginScreenState(loginSuccess = false, isLoading = false)
                }
                is Resource.Loading -> {
                    _uiState.value = LoginScreenState(loginSuccess = false)
                }
            }
        }
        return token
    }

    fun updateUserDetails(userDetails: UserLoginDetails){
        _uiState.value = LoginScreenState(userDetails = userDetails)
    }
}

data class LoginScreenState(
    val userDetails: UserLoginDetails = UserLoginDetails(),
    val loginSuccess: Boolean = false,
    val isLoading: Boolean = true
)
data class UserLoginDetails(
    val email: String = "",
    val password: String = ""
)

fun UserLoginDetails.toLoginRequest(): LoginRequest{
    return LoginRequest(
        email = email,
        password = password
    )
}