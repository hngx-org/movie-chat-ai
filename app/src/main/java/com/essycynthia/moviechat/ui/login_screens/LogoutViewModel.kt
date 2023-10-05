package com.essycynthia.moviechat.ui.login_screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shegs.hng_auth_library.authlibrary.AuthLibrary
import com.shegs.hng_auth_library.model.LogoutResponse
import com.shegs.hng_auth_library.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class LogoutViewModel() : ViewModel()  {
    private val apiService = AuthLibrary.createAuthService()
    fun logOut (){
        viewModelScope.launch {
            val logoutRepository = AuthLibrary.createLogoutRepository(apiService)

            val result: ApiResponse<LogoutResponse> = logoutRepository.logout()
        }
    }


}