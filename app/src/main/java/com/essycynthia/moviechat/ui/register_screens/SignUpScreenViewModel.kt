package com.essycynthia.moviechat.ui.register_screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.essycynthia.moviechat.data.dto.requests.RegisterRequest
import com.essycynthia.moviechat.domain.repository.MovieRepository
import com.essycynthia.moviechat.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(private  val repository: MovieRepository): ViewModel() {
    private val _userSignUpState = MutableStateFlow(SignUpScreenState())
    val userSignUpState: MutableStateFlow<SignUpScreenState> = _userSignUpState

    fun signup(registerRequest: RegisterRequest){


        viewModelScope.launch(Dispatchers.IO){
            _userSignUpState.value = SignUpScreenState(isLoading = true, error = null)

            val response = repository.registerUser(registerRequest)
            when (response){
                is Resource.Success -> {
                    _userSignUpState.value = SignUpScreenState(success = response.data)
                }
                is Resource.Loading ->{
                    _userSignUpState.value = SignUpScreenState(isLoading = true)
                }
                is Resource.Error ->{
                    _userSignUpState.value = SignUpScreenState(
                        error = response.message ?: "Network error"
                    )
                }
            }
        }
    }
}
