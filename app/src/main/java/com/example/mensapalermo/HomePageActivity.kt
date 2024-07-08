package com.example.mensapalermo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomePageActivity : AppCompatActivity() {

    val fragmentContainer: FrameLayout  = findViewById(R.id.fragmentContainerContent)
    val dropdownMenu: Button = findViewById(R.id.fragmentButton)
    val home : ImageButton = findViewById(R.id.imageButtonHome)
    val menu : ImageButton = findViewById(R.id.imageButtonMenu)
    val ordine :  ImageButton = findViewById(R.id.imageButtonOrdine)
    val notifiche : ImageButton = findViewById(R.id.imageButtonNotifiche)

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Prende i dati dell'offerta
        val offerta = getOfferte()

        setupRecyclerView()

        dropdownMenu.setOnClickListener { view ->
            showPopupMenu(view)
        }
        // Carica il fragment OfferteFragment
        loadFragment(OfferteFragment())

        home.setOnClickListener {
            startActivity(Intent(this, HomePageActivity ::class.java))
        }
        menu.setOnClickListener {
            startActivity(Intent(this, HomePageActivity ::class.java))
        }
        notifiche.setOnClickListener {
            startActivity(Intent(this, HomePageActivity ::class.java))
        }
        ordine.setOnClickListener {
            startActivity(Intent(this, HomePageActivity ::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // Carica il fragment passato nel contenitore
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerContent, fragment)
        transaction.commit()
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.item_portafoglio -> {
                    val intent = Intent(this, PortafoglioDigitaleActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.item_sede -> {
                    val intent = Intent(this, VisualizzaSedeActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.item_recensione -> {
                    val intent = Intent(this, RecensioniActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }

    private fun setupRecyclerView() {
        // Recupera la RecyclerView dal layout
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val items = getOfferte()

        // Imposta l'adapter per la RecyclerView
        val adapter = OffertaAdapter(items)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun getOfferte(): List<Offerta> {
        return listOf(
            Offerta(
                "Sei uno studente?",
                "Mostra la tua carta studentesca alla cassa e prendi un menù completo e ottieni uno sconto",
                "€15.00"
            ),
            Offerta(
                "Combo Meal",
                "Prendi un primo o un secondo con contorno, con una bibita",
                "€17.00"
            ),
            Offerta(
                "Buoni Pasto",
                "Accettiamo anche Buoni Pasto!",
                ""),
        )
    }
}





