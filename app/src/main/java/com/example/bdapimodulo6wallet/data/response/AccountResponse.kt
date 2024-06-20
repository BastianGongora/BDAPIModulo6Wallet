package com.example.bdapimodulo6wallet.data.response
/**
 * AccountResponse representa la estructura de datos de una cuenta en el sistema de wallet digital.
 * @param createdAt la fecha y hora de creación de la cuenta.
 * @param creationDate la fecha de creación de la cuenta.
 * @param id el identificador único de la cuenta.
 * @param isBlocked indica si la cuenta está bloqueada.
 * @param money el saldo de la cuenta en formato de cadena.
 * @param updatedAt la fecha y hora de la última actualización de la cuenta.
 * @param userId el identificador del usuario al que pertenece la cuenta.
 */
data class AccountResponse(
    val createdAt: String,
    val creationDate: String,
    val id: Int,
    val isBlocked: Boolean,
    val money: String,
    val updatedAt: String,
    val userId: Int

)
