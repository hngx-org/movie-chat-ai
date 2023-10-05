package com.essycynthia.moviechat.ui.login_screens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shegs.hng_auth_library.authlibrary.AuthLibrary
import com.shegs.hng_auth_library.model.AuthResponse
import com.shegs.hng_auth_library.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.shegs.hng_auth_library.model.LoginRequest

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel(){
    private var _uiState = MutableStateFlow(LoginScreenState())
    val uiState: StateFlow<LoginScreenState> = _uiState.asStateFlow()

    val apiService = AuthLibrary.createAuthService()
    val dataStoreRepository = AuthLibrary.createDataStoreRepository(context)
    val loginRepository = AuthLibrary.createLoginRepository(apiService, dataStoreRepository)



    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            _uiState.value = LoginScreenState(isLoading = true)
            when (val result = loginRepository.login(loginRequest = loginRequest)){
                is ApiResponse.Success -> {
                    _uiState.value = LoginScreenState(loginSuccess = true, isLoading = false, userId = result.data.data.id)
                }
                is ApiResponse.Error -> {
                    _uiState.value = LoginScreenState(loginSuccess = false, isLoading = false, error = result.message)
                }
            }
        }
    }
    fun resetState(){
        _uiState.value = LoginScreenState()
    }
}
data class LoginScreenState(
    //val userDetails: UserLoginDetails = UserLoginDetails(),
    val loginSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val userId: String? = null
)
