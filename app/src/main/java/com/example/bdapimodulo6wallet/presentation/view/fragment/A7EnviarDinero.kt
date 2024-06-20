package com.example.bdapimodulo6wallet.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.apibdwalletmodulo6.presentation.viewmodel.HomeViewModelFactory
import com.example.bdapimodulo6wallet.R
import com.example.bdapimodulo6wallet.data.model.User
import com.example.bdapimodulo6wallet.data.repository.AlkeWalletRepositoryImpl
import com.example.bdapimodulo6wallet.databinding.FragmentA7EnviarDineroBinding
import com.example.bdapimodulo6wallet.presentation.viewmodel.HomeViewModel



/**
 * A7EnviarDinero es un fragmento que maneja la lógica de la interfaz de usuario para enviar dinero a otro usuario.
 */
class A7EnviarDinero : Fragment() {

    private var _binding: FragmentA7EnviarDineroBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(AlkeWalletRepositoryImpl(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentA7EnviarDineroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observa la lista de usuarios y configura el spinner cuando se actualice la lista
        viewModel.users.observe(viewLifecycleOwner, Observer { users ->
            users?.let {
                setupSpinner(it)
            }
        })

        // Observa los mensajes de error y muestra un Toast si hay errores
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage.getContentIfNotHandled()?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })

        // Observa los mensajes de éxito y muestra un Toast si la operación fue exitosa
        viewModel.successMessage.observe(viewLifecycleOwner, Observer { successMessage ->
            successMessage.getContentIfNotHandled()?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_a7EnviarDinero_to_a5Home)
            }
        })

        // Configura el botón para enviar dinero
        binding.btnID.setOnClickListener {
            val selectedUserPosition = binding.spinnerUsers.selectedItemPosition
            val amount = binding.txtcantidadingresar.text.toString().toIntOrNull()
            val concept = binding.txtnotas.text.toString()
            val userId = viewModel.users.value?.get(selectedUserPosition)?.id

            if (amount == null || userId == null) {
                Toast.makeText(requireContext(), "Por favor, ingresa una cantidad válida y selecciona un usuario.", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("A7EnviarDineroFragment", "Iniciando envío de dinero: Usuario ID: $userId, Cantidad: $amount, Concepto: $concept")
                viewModel.enviarDinero(userId, "payment", concept, amount)
            }
        }

        viewModel.loadUsers() // Cargar usuarios cuando se crea la vista
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Configura el spinner con la lista de usuarios.
     * @param users la lista de usuarios disponibles.
     */
    private fun setupSpinner(users: List<User>) {
        val userNames = users.map { "${it.first_name} ${it.last_name}" }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, userNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerUsers.adapter = adapter
    }
}