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
    suspend fun login(email: String, password: String): String
    suspend fun getProfile(): UserInfoResponse
    suspend fun getAllUsers(): MutableList<User>
    suspend fun myAccount(): MutableList<AccountResponse>
    suspend fun getAccountById(id: Int): AccountResponse
    suspend fun myTransactions(): List<TransactionDataResponse>
    suspend fun getUserById(id: Int): UserInfoResponse
    suspend fun saveTransaction(transaction: TransactionDataResponse)
    suspend fun getAllTransactions(): List<TransactionDataResponse>
    suspend fun saveUserProfile(userProfile: UserInfoResponse)
    suspend fun getUserProfile(): UserInfoResponse?
    suspend fun ingresarDinero(accountId: Int, type: String, concept: String, amount: Int)
    /**
     * Crea un nuevo usuario en el sistema.
     * @param user objeto de respuesta de usuario que contiene los detalles del nuevo usuario.
     * @return respuesta con los detalles del usuario creado.
     */
    suspend fun createUser(user: UserInfoResponse): UserInfoResponse
}