package com.essycynthia.moviechat.ui.register_screens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.essycynthia.moviechat.data.dto.requests.RegisterRequest
import com.essycynthia.moviechat.domain.repository.MovieRepository
import com.essycynthia.moviechat.util.Resource
import com.shegs.hng_auth_library.authlibrary.AuthLibrary
import com.shegs.hng_auth_library.model.AuthResponse
import com.shegs.hng_auth_library.model.SignupRequest
import com.shegs.hng_auth_library.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(@ApplicationContext context: Context): ViewModel() {
    private val apiService = AuthLibrary.createAuthService()

        private val _userSignUpState = MutableStateFlow(SignUpScreenState())
    val userSignUpState: MutableStateFlow<SignUpScreenState> = _userSignUpState
    fun signup(signupRequest: SignupRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val signupRepository = AuthLibrary.createSignupRepository(apiService)

            val result: ApiResponse<AuthResponse> = signupRepository.signup(signupRequest)
            when (result) {
                is ApiResponse.Success -> {
                    val data = result.data
                    // Handle success data
                    _userSignUpState.value = SignUpScreenState(success = data)

                }

                is ApiResponse.Error -> {
                    val errorMessage = result.message
                    // Handle error message
                    _userSignUpState.value = SignUpScreenState(
                        error = errorMessage
                    )
                }
            }


        }
    }
}


