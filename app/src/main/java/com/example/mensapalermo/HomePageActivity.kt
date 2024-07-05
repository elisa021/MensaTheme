package com.example.mensapalermo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val offerta = getOfferta() // Funzione ipotetica per ottenere i dati dell'offerta

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerMenu, MenuTendinaFragment())
                .commit()

            // Mostra il fragment MenuFragment nel fragmentContainerContent
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerContent, MenuFragment())
                .commit()
        }
    }

    private fun getOfferta(): Offerta {
        // Implementazione fittizia, sostituiscila con la tua logica di recupero dati
        return Offerta( titolo = "Offerta Speciale", descrizione = "Descrizione dell'offerta", prezzo = "€99.99")
    }
}



