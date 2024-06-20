package com.example.apibdwalletmodulo6.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bdapimodulo6wallet.R
import com.example.bdapimodulo6wallet.data.response.TransactionDataResponse
/**
 * TransactionAdapter es un adaptador de RecyclerView para mostrar una lista de transacciones.
 * @param transactions la lista de transacciones a mostrar.
 */
class TransactionAdapter(private var transactions: List<TransactionDataResponse>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    /**
     * TransactionViewHolder es una clase interna que mantiene las vistas para cada elemento de la lista.
     * @param view la vista de un elemento de la lista.
     */
    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatarImageView: ImageView = view.findViewById(R.id.avatarImageView)
        val userNameTextView: TextView = view.findViewById(R.id.userNameTextView)
        val typeImageView: ImageView = view.findViewById(R.id.typeImageView)
        val amountTextView: TextView = view.findViewById(R.id.amountTextView)
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        val conceptTextView: TextView = view.findViewById(R.id.conceptTextView)
    }

    /**
     * Crea nuevas vistas (invocado por el layout manager).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    /**
     * Reemplaza el contenido de una vista (invocado por el layout manager).
     */
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.userNameTextView.text = transaction.userName // Asigna el nombre del usuario
        holder.amountTextView.text = transaction.amount.toString()
        holder.dateTextView.text = transaction.date.split("T")[0] // Formato de fecha YYYY-MM-DD
        holder.conceptTextView.text = transaction.concept // Asigna el concepto del pago

        // Asigna la imagen según el tipo de transacción
        holder.typeImageView.setImageResource(
            if (transaction.type == "topup") R.drawable.request_icon_2
            else R.drawable.send_icon
        )
    }

    /**
     * Devuelve el tamaño del dataset (invocado por el layout manager).
     */
    override fun getItemCount(): Int = transactions.size

    /**
     * Actualiza la lista de transacciones y notifica los cambios.
     * @param newTransactions la nueva lista de transacciones.
     */
    fun updateTransactions(newTransactions: List<TransactionDataResponse>) {
        transactions = newTransactions
        notifyDataSetChanged()
    }
}