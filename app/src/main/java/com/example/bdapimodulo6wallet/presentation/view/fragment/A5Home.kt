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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apibdwalletmodulo6.presentation.adapter.TransactionAdapter
import com.example.apibdwalletmodulo6.presentation.viewmodel.HomeViewModelFactory
import com.example.bdapimodulo6wallet.R
import com.example.bdapimodulo6wallet.data.repository.AlkeWalletRepositoryImpl
import com.example.bdapimodulo6wallet.databinding.FragmentA5HomeBinding
import com.example.bdapimodulo6wallet.presentation.viewmodel.AuthViewModel
import com.example.bdapimodulo6wallet.presentation.viewmodel.AuthViewModelFactory
import com.example.bdapimodulo6wallet.presentation.viewmodel.HomeViewModel
/**
 * A5Home es un fragmento que maneja la lógica de la interfaz de usuario para la pantalla principal de la aplicación.
 */
class A5Home : Fragment() {

    private var _binding: FragmentA5HomeBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(requireContext().applicationContext)
    }

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(AlkeWalletRepositoryImpl(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentA5HomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el perfil y las cuentas del usuario cuando se crea la vista
        authViewModel.getProfile()
        authViewModel.loadAccounts()

        // Observa los datos del perfil del usuario y actualiza la interfaz
        authViewModel.userInfo.observe(viewLifecycleOwner, Observer { userInfo ->
            userInfo?.let {
                binding.txtSaludo.text = "Hola, ${it.first_name} ${it.last_name}"
            }
        })

        // Observa el balance y actualiza la interfaz
        authViewModel.balance.observe(viewLifecycleOwner, Observer { balance ->
            binding.txtbalance.text = balance
        })

        // Observa los mensajes de error y muestra un Toast si hay errores
        authViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })

        // Configura los botones de ingreso y envío de dinero
        binding.btnIngresoHome.setOnClickListener {
            findNavController().navigate(R.id.action_a5Home_to_a6IngresarDinero)
        }
        binding.btnEnvioHome.setOnClickListener {
            findNavController().navigate(R.id.action_a5Home_to_a7EnviarDinero)
        }

        // Configuración del RecyclerView para mostrar las transacciones
        val adapter = TransactionAdapter(emptyList())
        binding.transactionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        // Observa las transacciones y actualiza la interfaz
        homeViewModel.transactions.observe(viewLifecycleOwner, Observer { transactions ->
            transactions?.let {
                (binding.transactionsRecyclerView.adapter as TransactionAdapter).updateTransactions(it)
            }
        })

        // Carga las transacciones cuando se crea la vista
        homeViewModel.loadTransactions()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}