package com.example.bdapimodulo6wallet.data.model
/**
 * TransactionDataRequest representa la estructura de datos para una solicitud de transacción en la wallet digital.
 * @param type el tipo de transacción (por ejemplo, "deposit", "withdrawal", "transfer").
 * @param concept el concepto o descripción de la transacción.
 * @param amount la cantidad de dinero involucrada en la transacción.
 */
data class TransactionDataRequest(
    val type: String,
    val concept: String,
    val amount: Int
)