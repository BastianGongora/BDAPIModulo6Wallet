package com.example.bdapimodulo6wallet.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.bdapimodulo6wallet.R
import com.example.bdapimodulo6wallet.databinding.FragmentA3LoginBinding
import com.example.bdapimodulo6wallet.presentation.viewmodel.AuthViewModel
import com.example.bdapimodulo6wallet.presentation.viewmodel.AuthViewModelFactory

/**
 * A3Login es un fragmento que maneja la lógica de la interfaz de usuario para el inicio de sesión.
 */
class A3Login : Fragment() {

    private var _binding: FragmentA3LoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(requireContext().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentA3LoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogeo.setOnClickListener {
            val email = binding.txtEmailLogin.text.toString()
            val password = binding.txtPassLogin.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                binding.txtEmailLogin.requestFocus()
            } else {
                viewModel.login(email, password)
            }
        }

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer { token ->
            if (token != null) {
                // Navegar al siguiente fragmento
                findNavController().navigate(R.id.action_a3Login_to_a5Home)
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            // Mostrar mensaje de error
            if (errorMessage != null) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}