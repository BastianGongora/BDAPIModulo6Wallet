package com.example.bdapimodulo6wallet.data.model
/**
 * User representa la estructura de datos de un usuario en el sistema de la wallet digital.
 * @param id el identificador único del usuario.
 * @param first_name el nombre del usuario.
 * @param last_name el apellido del usuario.
 * @param email la dirección de correo electrónico del usuario.
 * @param password la contraseña del usuario.
 */
data class User (
    val id: Int = 0,
    val first_name: String = "",
    val last_name: String = "",
    val email: String = "",
    val password: String = ""
)