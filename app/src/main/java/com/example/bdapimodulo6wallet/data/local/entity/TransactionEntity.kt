package com.example.bdapimodulo6wallet.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bdapimodulo6wallet.data.response.TransactionDataResponse

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey val id: Int,
    val amount: Int,
    val concept: String,
    val date: String,
    val type: String,
    val accountId: Int,
    val userId: Int,
    val toAccountId: Int
)

fun TransactionDataResponse.toTransactionEntity(): TransactionEntity {
    return TransactionEntity(
        id = this.id,
        amount = this.amount,
        concept = this.concept,
        date = this.date,
        type = this.type,
        accountId = this.accountId,
        userId = this.userId,
        toAccountId = this.to_account_id // Asegúrate de que el nombre del campo coincida
    )
}

fun TransactionEntity.toTransactionDataResponse(): TransactionDataResponse {
    return TransactionDataResponse(
        id = this.id,
        amount = this.amount,
        concept = this.concept,
        date = this.date,
        type = this.type,
        accountId = this.accountId,
        userId = this.userId,
        to_account_id = this.toAccountId // Asegúrate de que el nombre del campo coincida
    )
}