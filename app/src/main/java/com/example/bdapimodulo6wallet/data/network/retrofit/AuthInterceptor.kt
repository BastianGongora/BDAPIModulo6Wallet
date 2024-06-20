package com.example.bdapimodulo6wallet.data.network.retrofit

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * AuthInterceptor es un interceptor de OkHttp que agrega un encabezado de autorización a las solicitudes HTTP.
 * @param context el contexto de la aplicación utilizado para acceder a las preferencias compartidas.
 */
class AuthInterceptor(private val context: Context) : Interceptor {

    /**
     * Intercepta la cadena de solicitudes para agregar el token de autorización.
     * @param chain la cadena de solicitudes que se está interceptando.
     * @return la respuesta HTTP después de agregar el encabezado de autorización.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        // Obtener el token de acceso de las preferencias compartidas
        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("ACCESS_TOKEN", null)

        // Construir la solicitud con el encabezado de autorización
        val requestBuilder = chain.request().newBuilder()
        token?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
            Log.d("AuthInterceptor", "Token: $it")
        }

        // Construir y proceder con la solicitud
        val request = requestBuilder.build()
        Log.d("AuthInterceptor", "Request URL: ${request.url}")
        Log.d("AuthInterceptor", "Request Headers: ${request.headers}")
        return chain.proceed(request)
    }
}