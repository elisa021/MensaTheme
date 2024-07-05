package com.example.mensapalermo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuFragment : Fragment(), MenuAdapter.OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var menuAdapter: MenuAdapter



    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_tendina, container, false)

        recyclerView = view.findViewById(R.id.recycler_view_menu)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        // Ottieni l'array di opzioni di menu dalle risorse
        val menuOptions = resources.getStringArray(R.array.menu_options).toList()

        // Inizializza l'adapter e imposta il listener
        menuAdapter = MenuAdapter(menuOptions, this)
        recyclerView.adapter = menuAdapter

        return view
    }

    override fun onItemClick(item: String) {
        // Gestisci il clic sull'elemento qui
        Toast.makeText(requireContext(), "$item selezionato", Toast.LENGTH_SHORT).show()
        // Esegui altre azioni in base all'elemento selezionato
    }
}
