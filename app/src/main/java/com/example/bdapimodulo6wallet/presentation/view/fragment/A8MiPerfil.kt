package com.example.bdapimodulo6wallet.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.bdapimodulo6wallet.R
class A8MiPerfil : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_a8_mi_perfil, container, false)

        val miInformacion = view.findViewById<View>(R.id.miInformacion)
        val miOtraInformacion = view.findViewById<View>(R.id.miOtraInformacion)
        val configuracion = view.findViewById<View>(R.id.configuracion)
        val ayuda = view.findViewById<View>(R.id.ayuda)

        // Set dynamic data
        setProfileSectionData(miInformacion, R.drawable.profileicon, "Mi información")
        setProfileSectionData(miOtraInformacion, R.drawable.profile_cardicon, "Otra información")
        setProfileSectionData(configuracion, R.drawable.profile_setting_icon, "Configuración")
        setProfileSectionData(ayuda, R.drawable.profile_help_icon, "Ayuda")

        return view
    }

    private fun setProfileSectionData(view: View, iconResId: Int, title: String) {
        val iconImage = view.findViewById<ImageView>(R.id.iconImage)
        val textTitle = view.findViewById<TextView>(R.id.textTitle)
        iconImage.setImageResource(iconResId)
        textTitle.text = title
    }
}
