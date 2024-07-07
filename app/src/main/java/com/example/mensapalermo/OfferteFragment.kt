package com.example.mensapalermo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OfferteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var offerteAdapter: OffertaAdapter
    private lateinit var offerteList: List<Offerta>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main, container, false)

        // Creazione della lista di offerte
        offerteList = createOfferteList()

        // Inizializzazione della RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        // Aggiunge lo spazio tra gli elementi della RecyclerView
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        // Crea e imposta l'adapter per la RecyclerView
        offerteAdapter = OffertaAdapter(offerteList)
        recyclerView.adapter = offerteAdapter

        return view
    }

    private fun createOfferteList(): List<Offerta> {
        // Creazione la lista di offerte
        return listOf(
            Offerta("Sei uno studente?", "Mostra la tua carta studentesca alla cassa e prendi un menù completo e ottieni uno sconto ", "€15.00"),
            Offerta("Combo Meal", "Prendi un primo o un secondo con contorno, con una bibita", "€17.00"),
            Offerta("Buoni Pasto", "Accettiamo anche Buoni Pasto!", ""),
        )
    }
}
