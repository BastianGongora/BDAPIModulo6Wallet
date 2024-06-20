package com.example.bdapimodulo6wallet.data.repository

import android.content.Context
import com.example.bdapimodulo6wallet.data.model.LoginRequest
import com.example.bdapimodulo6wallet.data.model.TransactionDataRequest
import com.example.bdapimodulo6wallet.data.model.User
import com.example.bdapimodulo6wallet.data.network.api.WalletApiService
import com.example.bdapimodulo6wallet.data.network.retrofit.RetrofitHelper
import com.example.bdapimodulo6wallet.data.response.AccountResponse
import com.example.bdapimodulo6wallet.data.response.TransactionDataResponse
import com.example.bdapimodulo6wallet.data.response.TransactionListResponse
import com.example.bdapimodulo6wallet.data.response.UserInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementación concreta de AlkeWalletRepository que interactúa con la API de la wallet digital.
 * @param context el contexto de la aplicación utilizado para crear el servicio API.
 */
class AlkeWalletRepositoryImpl(context: Context) : AlkeWalletRepository {

    private val apiService: WalletApiService = RetrofitHelper.getInstance(context)

    /**
     * Inicia sesión en la aplicación de la wallet digital.
     * @param email dirección de correo electrónico del usuario.
     * @param password contraseña del usuario.
     * @return el token de acceso si el inicio de sesión es exitoso.
     */
    override suspend fun login(email: String, password: String): String {
        return withContext(Dispatchers.IO) {
            val loginRequest = LoginRequest(email, password)
            val response = apiService.login(loginRequest)
            response.accessToken
        }
    }

    /**
     * Obtiene el perfil del usuario actualmente autenticado.
     * @return la información del perfil del usuario.
     */
    override suspend fun getProfile(): UserInfoResponse {
        return withContext(Dispatchers.IO) {
            apiService.myProfile()
        }
    }

    /**
     * Obtiene una lista de todos los usuarios en el sistema.
     * @return una lista de usuarios.
     */
    override suspend fun getAllUsers(): MutableList<User> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getAllUsers()
            response.data
        }
    }

    /**
     * Obtiene las cuentas asociadas al usuario actualmente autenticado.
     * @return una lista de respuestas de cuenta.
     */
    override suspend fun myAccount(): MutableList<AccountResponse> {
        return withContext(Dispatchers.IO) {
            apiService.myAccount()
        }
    }

    /**
     * Obtiene la información de una cuenta específica por su ID.
     * @param id identificador de la cuenta.
     * @return la información de la cuenta.
     */
    override suspend fun getAccountById(id: Int): AccountResponse {
        return withContext(Dispatchers.IO) {
            apiService.getAccountById(id)
        }
    }

    /**
     * Obtiene la información de un usuario específico por su ID.
     * @param id identificador del usuario.
     * @return la información del usuario.
     */
    override suspend fun getUserById(id: Int): UserInfoResponse {
        return withContext(Dispatchers.IO) {
            apiService.getUserById(id)
        }
    }

    /**
     * Obtiene las transacciones realizadas por el usuario actualmente autenticado.
     * @return una respuesta con la lista de transacciones.
     */
    override suspend fun myTransactions(): TransactionListResponse {
        return withContext(Dispatchers.IO) {
            apiService.myTransactions()
        }
    }

    /**
     * Realiza una transacción de ingreso de dinero en una cuenta específica.
     * @param accountId identificador de la cuenta.
     * @param transactionRequest objeto de solicitud de transacción que contiene los detalles de la transacción.
     * @return respuesta con los detalles de la transacción realizada.
     */
    override suspend fun ingresarDinero(accountId: Int, transactionRequest: TransactionDataRequest): TransactionDataResponse {
        return withContext(Dispatchers.IO) {
            apiService.ingresarDinero(accountId, transactionRequest)
        }
    }

    /**
     * Crea un nuevo usuario en el sistema.
     * @param user objeto de respuesta de usuario que contiene los detalles del nuevo usuario.
     * @return respuesta con los detalles del usuario creado.
     */
    override suspend fun createUser(user: UserInfoResponse): UserInfoResponse {
        return withContext(Dispatchers.IO) {
            apiService.createUser(user)
        }
    }
}