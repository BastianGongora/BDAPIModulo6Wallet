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
import com.example.apibdwalletmodulo6.presentation.viewmodel.SignUpViewModel
import com.example.apibdwalletmodulo6.presentation.viewmodel.SignUpViewModelFactory
import com.example.bdapimodulo6wallet.R
import com.example.bdapimodulo6wallet.data.repository.AlkeWalletRepositoryImpl
import com.example.bdapimodulo6wallet.databinding.FragmentA4SignUpBinding
/**
 * A4SignUp es un fragmento que maneja la lógica de la interfaz de usuario para el registro de nuevos usuarios.
 */
class A4SignUp : Fragment() {

    private var _binding: FragmentA4SignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels {
        SignUpViewModelFactory(AlkeWalletRepositoryImpl(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentA4SignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.successMessage.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_a4SignUp_to_a3Login)
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnCrearSignup.setOnClickListener {
            val firstName = binding.editTextText3.text.toString()
            val lastName = binding.editTextText4.text.toString()
            val email = binding.editTextText5.text.toString()
            val password = binding.editTextText6.text.toString()
            val confirmPassword = binding.editTextText7.text.toString()

            if (password == confirmPassword) {
                viewModel.createUser(firstName, lastName, email, password)
            } else {
                Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtYaCuentaSignup.setOnClickListener {
            findNavController().navigate(R.id.action_a4SignUp_to_a3Login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}