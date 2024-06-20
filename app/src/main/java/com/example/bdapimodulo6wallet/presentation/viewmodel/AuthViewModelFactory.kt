package com.example.bdapimodulo6wallet.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bdapimodulo6wallet.data.repository.AlkeWalletRepositoryImpl

class AuthViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(AlkeWalletRepositoryImpl(context), context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}