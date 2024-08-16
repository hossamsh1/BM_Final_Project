package com.example.banquemisr.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class BalanceRepository {
    suspend fun getBalance(userId: Int): Double {
        val response = BalanceApiService.callable.getBalance(userId)
        return response.currentBalance
    }
}

class CurantBalanceViewModel : ViewModel() {

    private val repository = BalanceRepository()

    private val _balance = MutableStateFlow<Double?>(null)
    val balance: StateFlow<Double?> = _balance.asStateFlow()

    fun getBalance(userId: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getBalance(userId)
                _balance.value = result
            } catch (e: Exception) {
                _balance.value = 100.0
            }
        }
    }
}