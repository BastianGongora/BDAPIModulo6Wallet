package com.example.bdapimodulo6wallet.data.response
/**
 * UserInfoResponse representa la estructura de datos de la información del usuario en el sistema de wallet digital.
 * @param id el identificador único del usuario.
 * @param email la dirección de correo electrónico del usuario.
 * @param first_name el nombre del usuario.
 * @param last_name el apellido del usuario.
 * @param password la contraseña del usuario.
 * @param points los puntos acumulados por el usuario.
 * @param roleId el identificador del rol del usuario.
 */
data class UserInfoResponse(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val password: String,
    val points: Int,
    val roleId: Int
)