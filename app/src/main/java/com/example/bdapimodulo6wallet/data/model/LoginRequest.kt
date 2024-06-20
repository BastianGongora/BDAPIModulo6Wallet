package com.example.bdapimodulo6wallet.data.model
/**
 * LoginRequest representa la estructura de datos que se enviará al realizar una solicitud de inicio de sesión.
 * @param email dirección de correo electrónico del usuario.
 * @param password contraseña del usuario.
 */
data class LoginRequest(
    val email: String,
    val password: String
)
