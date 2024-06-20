package com.example.bdapimodulo6wallet.presentation.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bdapimodulo6wallet.R
/**
 * A1SplashScreen es un fragmento que muestra una pantalla de bienvenida y redirige al usuario a otro fragmento después de un retraso.
 */
class A1SplashScreen : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            // Puedes manejar argumentos aquí si es necesario
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        return inflater.inflate(R.layout.fragment_a1_splash_screen, container, false)
    }

    /**
     * Método para redirigir al siguiente fragmento con un tiempo de retraso.
     *
     * @param duracionPantalla contiene el tiempo que tardará antes de redirigir al siguiente fragmento.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val duracionPantalla = 2000L // Duración en milisegundos

        Handler(Looper.getMainLooper()).postDelayed({
            // Redirige una vez el tiempo ha transcurrido
            findNavController().navigate(R.id.a2SignUpLogin)
        }, duracionPantalla)
    }
}