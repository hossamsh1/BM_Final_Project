package com.example.banquemisr.models


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private val SignInRepository = SignInRepository()

    private val _signInSuccess = MutableStateFlow<Boolean?>(null)
    val signInSuccess = _signInSuccess.asStateFlow()

    fun signIn(

        email: String,
        password: String,

        ) {
        viewModelScope.launch(Dispatchers.IO) {
            val signInRequest = SignIn.SignInRequest(
                email,
                password
            )
            val result = SignInRepository.signIn(signInRequest)
            _signInSuccess.value = result
        }
    }


}

class SignInRepository {
    suspend fun signIn(signInRequest: SignIn.SignInRequest): Boolean {
        return try {
            val response = SignInAPIService.callable.signIn(signInRequest)
            response.isSuccessful
        } catch (e: Exception) {
            println(e)
            Log.d("trace", "error: ${e.message}")
            Log.e("TAG", "Error occurred: ${e.message}", e)
            false
        }
    }
}


