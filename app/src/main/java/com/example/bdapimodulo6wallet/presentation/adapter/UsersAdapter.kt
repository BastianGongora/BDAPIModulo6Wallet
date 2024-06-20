package com.example.apibdwalletmodulo6.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bdapimodulo6wallet.data.model.User


import com.example.bdapimodulo6wallet.databinding.UserItemBinding

/**
 * UsersAdapter es un adaptador de RecyclerView para mostrar una lista de usuarios.
 * @param users la lista de usuarios a mostrar.
 * @param onItemClick un lambda que se ejecutará cuando se haga clic en un elemento de la lista.
 */
class UsersAdapter(
    private var users: List<User>,
    private val onItemClick: (User) -> Unit
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    /**
     * Crea una nueva instancia de UserViewHolder cuando no hay vistas reutilizables.
     * @param parent el ViewGroup al que la nueva vista será agregada después de ser vinculada a una posición de adaptador.
     * @param viewType el tipo de la nueva vista.
     * @return una nueva instancia de UserViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    /**
     * Reemplaza el contenido de la vista en una posición dada con un elemento de datos.
     * @param holder el ViewHolder que debe ser actualizado para representar el contenido del elemento en la posición dada.
     * @param position la posición del elemento dentro del conjunto de datos del adaptador.
     */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    /**
     * Retorna el tamaño del conjunto de datos que se mantiene en el adaptador.
     * @return el tamaño del conjunto de datos.
     */
    override fun getItemCount(): Int = users.size

    /**
     * Actualiza la lista de usuarios y notifica al adaptador para que refresque la vista.
     * @param newUsers la nueva lista de usuarios.
     */
    fun updateUsers(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }

    /**
     * UserViewHolder es una clase interna que mantiene las vistas para cada elemento de la lista.
     * @param binding el enlace de vista para un elemento de usuario.
     */
    inner class UserViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Vincula los datos de un usuario a las vistas correspondientes.
         * @param user el objeto User que contiene los datos a mostrar.
         */
        fun bind(user: User) {
            binding.userName.text = "${user.first_name} ${user.last_name}"
            binding.userEmail.text = user.email
            binding.root.setOnClickListener { onItemClick(user) }
        }
    }
}