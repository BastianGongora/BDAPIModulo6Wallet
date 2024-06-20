package com.example.bdapimodulo6wallet.domain

import com.example.bdapimodulo6wallet.data.repository.AlkeWalletRepositoryImpl
/**
 * WalletalkeUseCase encapsula la lógica de negocio relacionada con la wallet digital.
 * @param walletRepository el repositorio que proporciona los métodos para interactuar con la API de la wallet digital.
 */
class WalletalkeUseCase(private val walletRepository: AlkeWalletRepositoryImpl) {

    suspend fun login(email: String, password: String): String {
        return walletRepository.login(email, password)
    }
}