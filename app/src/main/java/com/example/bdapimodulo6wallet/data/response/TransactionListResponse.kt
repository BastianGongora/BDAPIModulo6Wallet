package com.example.bdapimodulo6wallet.data.response
/**
 * TransactionListResponse representa la estructura de datos de una respuesta que contiene una lista de transacciones
 * y la información de paginación.
 * @param data la lista de transacciones.
 * @param nextPage el enlace a la página siguiente de resultados, si existe.
 * @param previousPage el enlace a la página anterior de resultados, si existe.
 */
data class TransactionListResponse(

    val data: MutableList<TransactionDataResponse>,
    val nextPage: String?,
    val previousPage: String?

)
