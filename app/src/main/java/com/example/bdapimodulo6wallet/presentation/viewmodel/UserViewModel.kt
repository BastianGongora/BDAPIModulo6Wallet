package com.example.apibdwalletmodulo6.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bdapimodulo6wallet.data.model.User
import com.example.bdapimodulo6wallet.data.repository.AlkeWalletRepository
import kotlinx.coroutines.launch

/**
 * UserViewModel maneja la lógica de negocio relacionada con la carga de usuarios en la aplicación.
 * @param repository el repositorio utilizado para realizar operaciones de red.
 */
class UserViewModel(private val repository: AlkeWalletRepository) : ViewModel() {

    private val _users = MutableLiveData<MutableList<User>>()
    val users: LiveData<MutableList<User>> get() = _users

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    /**
     * Carga la lista de usuarios desde el repositorio.
     */
    fun loadUsers() {
        viewModelScope.launch {
            try {
                val userList = repository.getAllUsers()
                _users.postValue(userList)
                Log.d("UserViewModel", "Users loaded successfully")
            } catch (e: Exception) {
                _errorMessage.postValue(e.message)
                Log.e("UserViewModel", "Error loading users", e)
            }
        }
    }
}