package com.example.mensapalermo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class OffertaAdapter(private val listaOfferte: List<Offerta>) : RecyclerView.Adapter<OffertaAdapter.OffertaViewHolder>() {

    class OffertaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitolo: TextView = itemView.findViewById(R.id.text_titolo)
        val textDescrizione: TextView = itemView.findViewById(R.id.text_descrizione)
        val textPrezzo: TextView = itemView.findViewById(R.id.text_prezzo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffertaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_offerta, parent, false)
        return OffertaViewHolder(view)
    }

    override fun onBindViewHolder(holder: OffertaViewHolder, position: Int) {
        val offerta = listaOfferte[position]
        holder.textTitolo.text = offerta.titolo
        holder.textDescrizione.text = offerta.descrizione
        holder.textPrezzo.text = offerta.prezzo
    }

    override fun getItemCount(): Int {
        return listaOfferte.size
    }
}

