package com.example.bdapimodulo6wallet.data.model

data class DepositRequest(
    val type: String,
    val concept: String,
    val amount: Int
)