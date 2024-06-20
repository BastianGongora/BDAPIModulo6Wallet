package com.example.bdapimodulo6wallet.data.response

import com.example.bdapimodulo6wallet.data.model.User

/**
 * UserListResponse representa la estructura de datos de una respuesta que contiene una lista de usuarios
 * y la información de paginación.
 * @param previousPage el enlace a la página anterior de resultados, si existe.
 * @param nextPage el enlace a la página siguiente de resultados, si existe.
 * @param data la lista de usuarios.
 */
data class UserListResponse(
    val previousPage: String?,
    val nextPage: String?,
    val data: MutableList<User>
)
