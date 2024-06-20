package com.example.apibdwalletmodulo6.presentation.viewmodel

import androidx.lifecycle.*
import com.example.bdapimodulo6wallet.data.repository.AlkeWalletRepository
import com.example.bdapimodulo6wallet.data.response.UserInfoResponse
import com.example.bdapimodulo6wallet.presentation.utils.Event
import kotlinx.coroutines.launch

/**
 * SignUpViewModel maneja la lógica de negocio relacionada con la creación de nuevos usuarios en la aplicación.
 * @param repository el repositorio utilizado para realizar operaciones de red.
 */
class SignUpViewModel(private val repository: AlkeWalletRepository) : ViewModel() {

    private val _successMessage = MutableLiveData<Event<String>>()
    val successMessage: LiveData<Event<String>> get() = _successMessage

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> get() = _errorMessage

    /**
     * Crea un nuevo usuario con la información proporcionada.
     * @param firstName el nombre del usuario.
     * @param lastName el apellido del usuario.
     * @param email la dirección de correo electrónico del usuario.
     * @param password la contraseña del usuario.
     */
    fun createUser(firstName: String, lastName: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val newUser = UserInfoResponse(
                    id = 0,
                    email = email,
                    first_name = firstName,
                    last_name = lastName,
                    password = password,
                    points = 50, // Asignar puntos iniciales al nuevo usuario
                    roleId = 1 // Asignar un rol por defecto
                )
                val createdUser = repository.createUser(newUser)
                _successMessage.postValue(Event("Usuario creado exitosamente: ${createdUser.first_name} ${createdUser.last_name}"))
            } catch (e: Exception) {
                _errorMessage.postValue(Event(e.message ?: "Error desconocido"))
            }
        }
    }
}