package com.example.banquemisr.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CardViewModel : ViewModel() {


    private val cardRepository = CardRepository()

    private val _addCardSuccess = MutableStateFlow<Boolean?>(null)
    val addCardSuccess = _addCardSuccess.asStateFlow()


    fun addCard(
        accountNumber: String,
        balance: Int,
        userId: Int,
        currency: String,
        cardNumber: String,
        cardholderName: String,
        cvv: Int,
        expirationDate: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val card = Card(
                accountNumber,
                balance,
                userId,
                currency,
                cardNumber,
                cardholderName,
                cvv,
                expirationDate
            )
            val result = cardRepository.addCard(card)
            _addCardSuccess.value = result
        }
    }
}

    class CardRepository {
        suspend fun addCard(card: Card): Boolean {
            return try {
                val response = cardAPIService.callable.addCard(card)
                response.isSuccessful
            } catch (e: Exception) {
                println(e)
                Log.d("trace", "error: ${e.message}")
                Log.e("TAG", "Error occurred: ${e.message}", e)
                false
            }
        }
    }
