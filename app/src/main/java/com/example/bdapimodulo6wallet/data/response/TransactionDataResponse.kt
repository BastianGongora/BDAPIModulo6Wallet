package com.example.bdapimodulo6wallet.data.response
/**
 * TransactionDataResponse representa la estructura de datos de una transacción en el sistema de wallet digital.
 * @param id el identificador único de la transacción.
 * @param accountId el identificador de la cuenta desde la cual se realizó la transacción.
 * @param amount la cantidad de dinero involucrada en la transacción.
 * @param concept el concepto o descripción de la transacción.
 * @param date la fecha en que se realizó la transacción.
 * @param to_account_id el identificador de la cuenta de destino en caso de una transferencia.
 * @param type el tipo de transacción (por ejemplo, "deposit", "withdrawal", "transfer").
 * @param userId el identificador del usuario que realizó la transacción.
 * @param userName el nombre del usuario que realizó la transacción (opcional).
 */
data class TransactionDataResponse(

    val id: Int,
    val accountId: Int,
    val amount: Int,
    val concept: String,
    val date: String,
    val to_account_id: Int,
    val type: String,
    val userId: Int,
    val userName: String? = null // Añadir este campo para el nombre del usuario
)
