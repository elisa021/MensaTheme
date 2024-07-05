package com.example.mensapalermo

import OffertaAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        val view = inflater.inflate(R.layout.fragment_offerte, container, false)

        // Creazione della lista di offerte (puoi popolarla con i dati desiderati)
        offerteList = createOfferteList()

        // Inizializzazione della RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view_offerte)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        // Creazione e impostazione dell'adapter per la RecyclerView
        offerteAdapter = OffertaAdapter(offerteList)
        recyclerView.adapter = offerteAdapter

        return view
    }

    private fun createOfferteList(): List<Offerta> {
        // Simuliamo la creazione di una lista di offerte (puoi sostituire con i tuoi dati reali)
        return listOf(
            Offerta("Sei uno studente?", "Mostra la tua carta studentesca alla cassa e prendi un menù completo e ottieni uno sconto ", "€15.00"),
            Offerta("Combo Meal", "Prendi un primo o un secondo con contorno, con una bibita", "€17.00"),
            Offerta("Buoni Pasto", "Accettiamo anche Buoni Pasto!", ""),
        )
    }
}

