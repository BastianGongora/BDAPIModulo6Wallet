package com.example.bdapimodulo6wallet.presentation.view.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.apibdwalletmodulo6.presentation.viewmodel.HomeViewModelFactory
import com.example.bdapimodulo6wallet.R
import com.example.bdapimodulo6wallet.data.repository.AlkeWalletRepositoryImpl
import com.example.bdapimodulo6wallet.databinding.FragmentA6IngresarDineroBinding
import com.example.bdapimodulo6wallet.presentation.viewmodel.HomeViewModel

/**
 * A6IngresarDinero es un fragmento que maneja la lógica de la interfaz de usuario para ingresar dinero en una cuenta de usuario.
 */
class A6IngresarDinero : Fragment() {

    private var _binding: FragmentA6IngresarDineroBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(AlkeWalletRepositoryImpl(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentA6IngresarDineroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observa los datos del perfil del usuario y actualiza la interfaz
        viewModel.userInfo.observe(viewLifecycleOwner, Observer { userInfo ->
            userInfo?.let {
                binding.txtminombreusuario.text = "${it.first_name} ${it.last_name}"
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
                findNavController().navigate(R.id.action_a6IngresarDinero_to_a5Home)
            }
        })

        // Configura el botón para ingresar dinero
        binding.btnID.setOnClickListener {
            val amount = binding.txtcantidadingresar.text.toString().toIntOrNull()
            val concept = binding.txtnotas.text.toString()

            if (amount == null) {
                Toast.makeText(requireContext(), "Por favor, ingresa una cantidad válida.", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.userAccount.value?.let { userAccount ->
                    Log.d("A6IngresarDineroFragment", "Iniciando ingreso de dinero: Cantidad: $amount, Concepto: $concept")
                    viewModel.enviarDinero(userAccount.id, "topup", concept, amount)
                }
            }
        }

        // Obtiene el perfil del usuario y la cuenta del usuario al crear la vista
        viewModel.getProfile()
        viewModel.getUserAccount()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}