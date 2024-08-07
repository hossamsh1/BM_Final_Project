package com.example.banquemisr.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class FavoriteViewModel : ViewModel() {
    var favoriteList by mutableStateOf<List<Favorite>>(emptyList())
        private set

    fun getFavorites(token: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.getFavorites(token).execute()
                }
                if (response.isSuccessful) {
                    favoriteList = response.body() ?: emptyList()
                }
            } catch (e: Exception) {
                // Handle the exception appropriately (e.g., show a message to the user)
            }
        }
    }

    fun addFavorite(token: String, fullName: String, accountNumber: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.addFavorite(token, AddFavoriteRequest(fullName, accountNumber)).execute()
                }
                if (response.isSuccessful) {
                    getFavorites(token)
                }
            } catch (e: Exception) {
                // Handle the exception appropriately (e.g., show a message to the user)
            }
        }
    }

    fun deleteFavorite(token: String, favouriteId: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.deleteFavorite(token, favouriteId).execute()
                }
                if (response.isSuccessful) {
                    getFavorites(token)
                }
            } catch (e: Exception) {
                // Handle the exception appropriately (e.g., show a message to the user)
            }
        }
    }
}
