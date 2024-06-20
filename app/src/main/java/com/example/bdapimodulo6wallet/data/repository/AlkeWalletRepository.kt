package com.example.bdapimodulo6wallet.data.repository

import com.example.bdapimodulo6wallet.data.model.TransactionDataRequest
import com.example.bdapimodulo6wallet.data.model.User
import com.example.bdapimodulo6wallet.data.response.AccountResponse
import com.example.bdapimodulo6wallet.data.response.TransactionDataResponse
import com.example.bdapimodulo6wallet.data.response.TransactionListResponse
import com.example.bdapimodulo6wallet.data.response.UserInfoResponse


/**
 * AlkeWalletRepository define las operaciones que se pueden realizar en la aplicación de la wallet digital.
 */
interface AlkeWalletRepository {

    /**
     * Inicia sesión en la aplicación de la wallet digital.
     * @param email dirección de correo electrónico del usuario.
     * @param password contraseña del usuario.
     * @return el token de acceso si el inicio de sesión es exitoso.
     */
    suspend fun login(email: String, password: String): String

    /**
     * Obtiene el perfil del usuario actualmente autenticado.
     * @return la información del perfil del usuario.
     */
    suspend fun getProfile(): UserInfoResponse

    /**
     * Obtiene una lista de todos los usuarios en el sistema.
     * @return una lista de usuarios.
     */
    suspend fun getAllUsers(): MutableList<User>

    /**
     * Obtiene las cuentas asociadas al usuario actualmente autenticado.
     * @return una lista de respuestas de cuenta.
     */
    suspend fun myAccount(): MutableList<AccountResponse>

    /**
     * Obtiene la información de una cuenta específica por su ID.
     * @param id identificador de la cuenta.
     * @return la información de la cuenta.
     */
    suspend fun getAccountById(id: Int): AccountResponse

    /**
     * Obtiene las transacciones realizadas por el usuario actualmente autenticado.
     * @return una respuesta con la lista de transacciones.
     */
    suspend fun myTransactions(): TransactionListResponse

    /**
     * Obtiene la información de un usuario específico por su ID.
     * @param id identificador del usuario.
     * @return la información del usuario.
     */
    suspend fun getUserById(id: Int): UserInfoResponse

    /**
     * Realiza una transacción de ingreso de dinero en una cuenta específica.
     * @param accountId identificador de la cuenta.
     * @param transactionRequest objeto de solicitud de transacción que contiene los detalles de la transacción.
     * @return respuesta con los detalles de la transacción realizada.
     */
    suspend fun ingresarDinero(accountId: Int, transactionRequest: TransactionDataRequest): TransactionDataResponse

    /**
     * Crea un nuevo usuario en el sistema.
     * @param user objeto de respuesta de usuario que contiene los detalles del nuevo usuario.
     * @return respuesta con los detalles del usuario creado.
     */
    suspend fun createUser(user: UserInfoResponse): UserInfoResponse
}