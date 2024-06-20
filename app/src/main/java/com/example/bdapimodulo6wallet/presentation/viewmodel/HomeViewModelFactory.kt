package com.example.apibdwalletmodulo6.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bdapimodulo6wallet.data.repository.AlkeWalletRepository
import com.example.bdapimodulo6wallet.presentation.viewmodel.HomeViewModel

class HomeViewModelFactory(private val repository: AlkeWalletRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}