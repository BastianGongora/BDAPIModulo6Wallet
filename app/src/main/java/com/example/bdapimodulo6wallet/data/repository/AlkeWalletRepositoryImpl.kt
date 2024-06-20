package com.example.bdapimodulo6wallet.data.repository



import android.content.Context
import androidx.room.Room

import com.example.bdapimodulo6wallet.data.local.WalletDatabase
import com.example.bdapimodulo6wallet.data.local.entity.toTransactionDataResponse
import com.example.bdapimodulo6wallet.data.local.entity.toTransactionEntity
import com.example.bdapimodulo6wallet.data.local.entity.toUserEntity
import com.example.bdapimodulo6wallet.data.local.entity.toUserInfoResponse
import com.example.bdapimodulo6wallet.data.model.LoginRequest
import com.example.bdapimodulo6wallet.data.model.TransactionDataRequest
import com.example.bdapimodulo6wallet.data.model.User
import com.example.bdapimodulo6wallet.data.network.api.WalletApiService
import com.example.bdapimodulo6wallet.data.network.retrofit.RetrofitHelper
import com.example.bdapimodulo6wallet.data.response.AccountResponse
import com.example.bdapimodulo6wallet.data.response.TransactionDataResponse
import com.example.bdapimodulo6wallet.data.response.UserInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlkeWalletRepositoryImpl(context: Context) : AlkeWalletRepository {

    private val apiService: WalletApiService = RetrofitHelper.getInstance(context)
    private val walletDatabase: WalletDatabase = Room.databaseBuilder(
        context,
        WalletDatabase::class.java, "wallet-database"
    ).build()

    override suspend fun login(email: String, password: String): String {
        return withContext(Dispatchers.IO) {
            val loginRequest = LoginRequest(email, password)
            val response = apiService.login(loginRequest)
            response.accessToken
        }
    }

    override suspend fun getProfile(): UserInfoResponse {
        return withContext(Dispatchers.IO) {
            val profile = apiService.myProfile()
            walletDatabase.userDao().insertUser(profile.toUserEntity())
            profile
        }
    }

    override suspend fun getAllUsers(): MutableList<User> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getAllUsers()
            response.data
        }
    }

    override suspend fun myAccount(): MutableList<AccountResponse> {
        return withContext(Dispatchers.IO) {
            apiService.myAccount()
        }
    }

    override suspend fun getAccountById(id: Int): AccountResponse {
        return withContext(Dispatchers.IO) {
            apiService.getAccountById(id)
        }
    }

    override suspend fun myTransactions(): List<TransactionDataResponse> {
        return withContext(Dispatchers.IO) {
            val response = apiService.myTransactions()
            response.data // Asegúrate de que 'data' es una lista de 'TransactionDataResponse'
        }
    }

    override suspend fun getUserById(id: Int): UserInfoResponse {
        return withContext(Dispatchers.IO) {
            apiService.getUserById(id)
        }
    }

    override suspend fun saveTransaction(transaction: TransactionDataResponse) {
        withContext(Dispatchers.IO) {
            walletDatabase.transactionDao().insertTransaction(transaction.toTransactionEntity())
        }
    }

    override suspend fun getAllTransactions(): List<TransactionDataResponse> {
        return withContext(Dispatchers.IO) {
            walletDatabase.transactionDao().getAllTransactions().map { it.toTransactionDataResponse() }
        }
    }

    override suspend fun saveUserProfile(userProfile: UserInfoResponse) {
        withContext(Dispatchers.IO) {
            walletDatabase.userDao().insertUser(userProfile.toUserEntity())
        }
    }

    override suspend fun getUserProfile(): UserInfoResponse? {
        return withContext(Dispatchers.IO) {
            walletDatabase.userDao().getUser()?.toUserInfoResponse()
        }
    }

    override suspend fun ingresarDinero(accountId: Int, type: String, concept: String, amount: Int) {
        withContext(Dispatchers.IO) {
            val transactionRequest = TransactionDataRequest(type, concept, amount)
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