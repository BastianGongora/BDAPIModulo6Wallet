package com.example.bdapimodulo6wallet.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bdapimodulo6wallet.data.model.User
import com.example.bdapimodulo6wallet.data.repository.AlkeWalletRepository
import com.example.bdapimodulo6wallet.data.response.AccountResponse
import com.example.bdapimodulo6wallet.data.response.UserInfoResponse
import kotlinx.coroutines.launch


/**
 * AuthViewModel maneja la lógica de autenticación y la gestión de usuarios y cuentas en la aplicación.
 * @param repository el repositorio utilizado para realizar operaciones de red.
 * @param context el contexto de la aplicación utilizado para acceder a las preferencias compartidas.
 */
class AuthViewModel(private val repository: AlkeWalletRepository, private val context: Context) : ViewModel() {
    private val _loginResponse = MutableLiveData<String?>()
    val loginResponse: LiveData<String?> = _loginResponse

    private val _userInfo = MutableLiveData<UserInfoResponse?>()
    val userInfo: LiveData<UserInfoResponse?> = _userInfo

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _users = MutableLiveData<MutableList<User>>()
    val users: LiveData<MutableList<User>> get() = _users

    private val _accounts = MutableLiveData<MutableList<AccountResponse>>()
    val accounts: LiveData<MutableList<AccountResponse>> get() = _accounts

    private val _balance = MutableLiveData<String>()
    val balance: LiveData<String> get() = _balance

    /**
     * Carga la lista de usuarios desde el repositorio y actualiza la LiveData.
     */
    fun loadUsers() {
        viewModelScope.launch {
            try {
                val userList = repository.getAllUsers()
                _users.postValue(userList)
            } catch (e: Exception) {
                _errorMessage.postValue(e.message)
            }
        }
    }

    /**
     * Carga la lista de cuentas del usuario desde el repositorio y actualiza la LiveData.
     */
    fun loadAccounts() {
        viewModelScope.launch {
            try {
                val accountList = repository.myAccount()
                _accounts.postValue(accountList)
                accountList.firstOrNull()?.let {
                    _balance.postValue(it.money)
                }
            } catch (e: Exception) {
                _errorMessage.postValue(e.message)
            }
        }
    }

    /**
     * Realiza el inicio de sesión llamando al método login del repositorio.
     * @param email la dirección de correo electrónico del usuario.
     * @param password la contraseña del usuario.
     */
    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val token = repository.login(email, password)
                if (token != null) {
                    _loginResponse.postValue(token)
                    saveToken(token)
                    getProfile() // Intentar obtener el perfil después de guardar el token
                } else {
                    _errorMessage.postValue("Credenciales inválidas")
                }
            } catch (e: Exception) {
                _errorMessage.postValue("Fallo en ingreso de sesión: ${e.message}")
            }
        }
    }

    /**
     * Guarda el token de acceso en las preferencias compartidas.
     * @param token el token de acceso a guardar.
     */
    private fun saveToken(token: String) {
        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("ACCESS_TOKEN", token)
        editor.apply()
    }

    /**
     * Obtiene el perfil del usuario desde el repositorio y actualiza la LiveData.
     */
    fun getProfile() {
        viewModelScope.launch {
            try {
                val profile = repository.getProfile()
                _userInfo.postValue(profile)
            } catch (e: Exception) {
                _errorMessage.postValue("Fallo en obtener el perfil: ${e.message}")
            }
        }
    }

    /**
     * Obtiene el token de acceso desde las preferencias compartidas.
     * @return el token de acceso si existe, de lo contrario null.
     */
    fun getToken(): String? {
        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString("ACCESS_TOKEN", null)
    }
}