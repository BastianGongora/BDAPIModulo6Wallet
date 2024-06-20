package com.example.bdapimodulo6wallet.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bdapimodulo6wallet.data.model.TransactionDataRequest
import com.example.bdapimodulo6wallet.data.model.User
import com.example.bdapimodulo6wallet.data.repository.AlkeWalletRepository
import com.example.bdapimodulo6wallet.data.response.AccountResponse
import com.example.bdapimodulo6wallet.data.response.TransactionDataResponse
import com.example.bdapimodulo6wallet.data.response.UserInfoResponse
import com.example.bdapimodulo6wallet.presentation.utils.Event
import kotlinx.coroutines.launch

/**
 * HomeViewModel maneja la lógica de negocio relacionada con las transacciones, la gestión de usuarios
 * y la obtención de información de cuentas en la aplicación.
 * @param repository el repositorio utilizado para realizar operaciones de red.
 */
class HomeViewModel(private val repository: AlkeWalletRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _transactions = MutableLiveData<List<TransactionDataResponse>>()
    val transactions: LiveData<List<TransactionDataResponse>> get() = _transactions

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> get() = _errorMessage

    private val _successMessage = MutableLiveData<Event<String>>()
    val successMessage: LiveData<Event<String>> get() = _successMessage

    private val _userInfo = MutableLiveData<UserInfoResponse>()
    val userInfo: LiveData<UserInfoResponse> get() = _userInfo

    private val _userAccount = MutableLiveData<AccountResponse>()
    val userAccount: LiveData<AccountResponse> get() = _userAccount

    /**
     * Carga las transacciones del usuario y asigna los nombres de usuario a las transacciones.
     */
    fun loadTransactions() {
        viewModelScope.launch {
            try {
                val transactionList = repository.myTransactions().data

                val updatedTransactions = transactionList.map { transaction ->
                    val account = repository.getAccountById(transaction.to_account_id)
                    val user = repository.getUserById(account.userId)
                    transaction.copy(userName = "${user.first_name} ${user.last_name}")
                }
                _transactions.postValue(updatedTransactions)
            } catch (e: Exception) {
                _errorMessage.postValue(Event(e.message ?: "Error desconocido"))
            }
        }
    }

    /**
     * Obtiene el perfil del usuario.
     */
    fun getProfile() {
        viewModelScope.launch {
            try {
                val profile = repository.getProfile()
                _userInfo.postValue(profile)
            } catch (e: Exception) {
                _errorMessage.postValue(Event(e.message ?: "Error desconocido"))
            }
        }
    }

    /**
     * Obtiene la cuenta del usuario.
     */
    fun getUserAccount() {
        viewModelScope.launch {
            try {
                val accounts = repository.myAccount()
                if (accounts.isNotEmpty()) {
                    _userAccount.postValue(accounts[0]) // Asigna la primera cuenta del usuario
                } else {
                    _errorMessage.postValue(Event("No se encontraron cuentas para el usuario"))
                }
            } catch (e: Exception) {
                _errorMessage.postValue(Event(e.message ?: "Error desconocido"))
            }
        }
    }

    /**
     * Realiza una transacción de ingreso de dinero.
     */
    fun ingresarDinero(accountId: Int, type: String, concept: String, amount: Int) {
        viewModelScope.launch {
            try {
                Log.d("HomeViewModel", "Ingresando dinero: Cuenta ID: $accountId, Tipo: $type, Concepto: $concept, Cantidad: $amount")
                val transactionRequest = TransactionDataRequest(
                    type = type,
                    concept = concept,
                    amount = amount
                )
                val transactionResponse = repository.ingresarDinero(accountId, transactionRequest)
                _successMessage.postValue(Event("Transacción exitosa"))
            } catch (e: Exception) {
                _errorMessage.postValue(Event(e.message ?: "Error desconocido"))
            }
        }
    }

    /**
     * Carga la lista de usuarios.
     */
    fun loadUsers() {
        viewModelScope.launch {
            try {
                val usersList = repository.getAllUsers()
                _users.postValue(usersList)
            } catch (e: Exception) {
                _errorMessage.postValue(Event(e.message ?: "Error desconocido"))
            }
        }
    }
}