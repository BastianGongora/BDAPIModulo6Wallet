package com.example.bdapimodulo6wallet.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bdapimodulo6wallet.R
import com.example.bdapimodulo6wallet.databinding.FragmentA2SignUpLoginBinding


/**
 * A2SignUpLogin es un fragmento que maneja la lógica de la interfaz de usuario para la creación de cuentas y el inicio de sesión.
 */
class A2SignUpLogin : Fragment() {
    private var _binding: FragmentA2SignUpLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentA2SignUpLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navega al fragmento de registro cuando se hace clic en el botón "Crear Cuenta"
        binding.btnCrearCuenta.setOnClickListener {
            findNavController().navigate(R.id.action_a2SignUpLogin_to_a4SignUp)
        }

        // Navega al fragmento de inicio de sesión cuando se hace clic en el texto "Ya tengo una cuenta"
        binding.txtYaCuenta.setOnClickListener {
            findNavController().navigate(R.id.action_a2SignUpLogin_to_a3Login)
        }
    }

    /**
     * Método llamado cuando la vista asociada con el fragmento está siendo destruida.
     * Aquí se evita la fuga de memoria desasignando el enlace de vista.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Evita fugas de memoria
    }
}