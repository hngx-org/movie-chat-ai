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
    //private val loginRequest = _uiState.value.userDetails.toLoginRequest()

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            _uiState.value = LoginScreenState(isLoading = true)
            when (val response = repository.loginUser(loginRequest)){
                is Resource.Success -> {
                    _uiState.value = LoginScreenState(loginSuccess = true, isLoading = false)
                }
                is Resource.Error -> {
                    _uiState.value = LoginScreenState(loginSuccess = false, isLoading = false, error = response.message?: "Error")
                }
                is Resource.Loading -> {
                    _uiState.value = LoginScreenState(loginSuccess = false)
                }
            }
        }
    }

    /*fun updateUserDetails(userDetails: UserLoginDetails){
        _uiState.value = LoginScreenState(userDetails = userDetails)
    }*/
}

data class LoginScreenState(
    //val userDetails: UserLoginDetails = UserLoginDetails(),
    val loginSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
/*data class UserLoginDetails(
    val email: String = "",
    val password: String = ""
)*/

/*
fun UserLoginDetails.toLoginRequest() = LoginRequest(email = email, password = password)*/
