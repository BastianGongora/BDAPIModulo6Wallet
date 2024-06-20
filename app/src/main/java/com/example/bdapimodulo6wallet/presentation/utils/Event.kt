package com.example.bdapimodulo6wallet.presentation.utils


/**
 * Event es una clase que encapsula datos que se manejan solo una vez.
 * @param content el contenido del evento.
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Permite lectura externa pero no escritura

    /**
     * Devuelve el contenido y previene su uso nuevamente.
     * @return el contenido si no ha sido manejado, de lo contrario, devuelve null.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Devuelve el contenido, incluso si ya ha sido manejado.
     * @return el contenido.
     */
    fun peekContent(): T = content
}