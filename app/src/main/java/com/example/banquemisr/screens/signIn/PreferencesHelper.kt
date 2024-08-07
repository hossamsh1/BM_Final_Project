package com.example.banquemisr.screens.signIn

import android.content.Context
import android.content.SharedPreferences


class PreferencesHelper(context: Context) {

    private val sharedPreferencesSignIn: SharedPreferences =
        context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)

    fun saveCredentialsSignIn(email: String, password: String) {
        with(sharedPreferencesSignIn.edit()) {
            putString("email", email)
            putString("password", password)

            apply()
        }
    }

    private val sharedPreferencesSignUp: SharedPreferences =
        context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)

    fun saveCredentialsSignUp(
        email: String,
        password: String,
        fullName: String,
        country: String,
        birthDate: String
    ) {
        with(sharedPreferencesSignUp.edit()) {
            putString("email", email)
            putString("password", password)
            putString("fullName", fullName)
            putString("country", country)
            putString("birthDate", birthDate)
            apply()
        }
    }

    fun getEmail(): String? {
        return sharedPreferencesSignIn.getString("email", null)
    }

    fun getPassword(): String? {
        return sharedPreferencesSignIn.getString("password", null)
    }

    fun getFullName(): String? {
        return sharedPreferencesSignUp.getString("fullName", null)
    }

    fun getCountry(): String? {
        return sharedPreferencesSignUp.getString("country", null)
    }

    fun getBirthDate(): String? {
        return sharedPreferencesSignUp.getString("birthDate", null)
    }

    fun clearCredentialsSignIn() {
        with(sharedPreferencesSignIn.edit()) {
            clear()
            apply()
        }
        fun clearCredentialsSignUp() {
            with(sharedPreferencesSignUp.edit()) {
                clear()
                apply()
            }
        }
    }
}