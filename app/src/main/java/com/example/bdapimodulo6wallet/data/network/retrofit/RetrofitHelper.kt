package com.example.bdapimodulo6wallet.data.network.retrofit

import android.content.Context
import com.example.bdapimodulo6wallet.data.network.api.WalletApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * RetrofitHelper es un objeto singleton que proporciona una instancia configurada de Retrofit.
 */
object RetrofitHelper {

    private const val BASE_URL = "http://wallet-main.eba-ccwdurgr.us-east-1.elasticbeanstalk.com/"

    /**
     * Proporciona una instancia de WalletApiService configurada con Retrofit.
     * @param context el contexto de la aplicación utilizado para crear el cliente HTTP.
     * @return una instancia de WalletApiService.
     */
    fun getInstance(context: Context): WalletApiService {
        // Interceptor para el registro de las solicitudes y respuestas HTTP
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Cliente HTTP configurado con los interceptores de registro y autenticación
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor(context))
            .build()

        // Construcción de la instancia de Retrofit
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WalletApiService::class.java)
    }
}