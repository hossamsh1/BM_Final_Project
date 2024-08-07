package com.example.banquemisr.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val repository = Repository()

    private val _signUpSuccess = MutableStateFlow<Boolean?>(null)
    val signUpSuccess = _signUpSuccess.asStateFlow()

     fun signUp(
        fullName: String,
        email: String,
        phoneNumber: String,
        username: String,
        password: String,
        confirmPassword: String,
        gender: String,
        country: String,
        birthDate: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val signUpRequest = SignUpRequest(
                fullName,
                email,
                phoneNumber,
                username,
                password,
                confirmPassword,
                gender,
                country,
                birthDate
            )
            val result = repository.signUp(signUpRequest)
            _signUpSuccess.value = result
        }
    }


}

//class Repository {
//    suspend fun signUp(signUpRequest: SignUpRequest): Boolean {
//        return try {
//            val response = SignUpAPIService.callable.signUp(signUpRequest)
//            response.isSuccessful
//        } catch (e: Exception) {
//            println(e)
//            Log.d("trace", "error: ${e.message}")
//            Log.e("TAG", "Error occurred: ${e.message}", e)
//            false
//        }
//    }
//}




class Repository {
    suspend fun signUp(signUpRequest: SignUpRequest): Boolean {
        return try {
            val response = SignUpAPIService.callable.signUp(signUpRequest)
            if (response.isSuccessful) {
                Log.d("Repository", "SignUp successful: ${response.body()}")
                true
            } else {
                Log.e("Repository", "SignUp failed: ${response.code()} - ${response.message()}")
                false
            }
        } catch (e: Exception) {
            Log.e("Repository", "Error occurred: ${e.message}", e)
            false
        }
    }
}
