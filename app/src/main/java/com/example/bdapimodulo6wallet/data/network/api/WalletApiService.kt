package com.example.bdapimodulo6wallet.data.network.api

import com.example.bdapimodulo6wallet.data.model.LoginRequest
import com.example.bdapimodulo6wallet.data.model.TransactionDataRequest
import com.example.bdapimodulo6wallet.data.response.AccountResponse
import com.example.bdapimodulo6wallet.data.response.LoginResponse
import com.example.bdapimodulo6wallet.data.response.TransactionDataResponse
import com.example.bdapimodulo6wallet.data.response.TransactionListResponse
import com.example.bdapimodulo6wallet.data.response.UserInfoResponse
import com.example.bdapimodulo6wallet.data.response.UserListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * WalletApiService define las operaciones HTTP que se pueden realizar en la API del servicio de wallet digital.
 */
interface WalletApiService {

    /**
     * Inicia sesión en el servicio de wallet digital.
     * @param data objeto de solicitud de inicio de sesión que contiene las credenciales del usuario.
     * @return respuesta de inicio de sesión con la información necesaria (token, etc.).
     */
    @Headers("Content-type:application/json")
    @POST("auth/login")
    suspend fun login(@Body data: LoginRequest): LoginResponse

    /**
     * Obtiene la información del perfil del usuario actualmente autenticado.
     * @return respuesta con la información del usuario.
     */
    @GET("auth/me")
    suspend fun myProfile(): UserInfoResponse

    /**
     * Obtiene las cuentas asociadas al usuario actualmente autenticado.
     * @return lista de respuestas de cuenta.
     */
    @GET("accounts/me")
    suspend fun myAccount(): MutableList<AccountResponse>

    /**
     * Obtiene las transacciones realizadas por el usuario actualmente autenticado.
     * @return respuesta con la lista de transacciones.
     */
    @GET("transactions")
    suspend fun myTransactions(): TransactionListResponse

    /**
     * Obtiene la información de una cuenta específica por su ID.
     * @param id identificador de la cuenta.
     * @return respuesta con la información de la cuenta.
     */
    @GET("accounts/{id}")
    suspend fun getAccountById(@Path("id") id: Int): AccountResponse

    /**
     * Obtiene una lista de todos los usuarios en el sistema.
     * @return respuesta con la lista de usuarios.
     */
    @GET("users")
    suspend fun getAllUsers(): UserListResponse

    /**
     * Obtiene la información de un usuario específico por su ID.
     * @param id identificador del usuario.
     * @return respuesta con la información del usuario.
     */
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserInfoResponse

    /**
     * Realiza una transacción de ingreso de dinero en una cuenta específica.
     * @param accountId identificador de la cuenta.
     * @param transactionRequest objeto de solicitud de transacción que contiene los detalles de la transacción.
     * @return respuesta con los detalles de la transacción realizada.
     */
    @POST("accounts/{id}")
    suspend fun ingresarDinero(@Path("id") accountId: Int, @Body transactionRequest: TransactionDataRequest): TransactionDataResponse

    /**
     * Crea un nuevo usuario en el sistema.
     * @param user objeto de respuesta de usuario que contiene los detalles del nuevo usuario.
     * @return respuesta con los detalles del usuario creado.
     */
    @POST("/users")
    suspend fun createUser(@Body user: UserInfoResponse): UserInfoResponse
}