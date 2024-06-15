package com.example.mensapalermo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val etEmailAddress: EditText = findViewById(R.id.etEmailAddress)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val buttonAccedi: Button = findViewById(R.id.buttonAccedi)
        val textViewPswDim: TextView = findViewById(R.id.textViewPswDim)
        val textViewRegistrati: TextView = findViewById(R.id.textViewRegistrati)

        buttonAccedi.setOnClickListener {
            val email = etEmailAddress.text.toString()
            val password = etPassword.text.toString()

            // A seguito l'autenticazione l'utente viene riportato all'HomePage
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }

        textViewPswDim.setOnClickListener {
            // L'utente viene riportato alla per riprendere la password
            val intent = Intent(this, RecuperaPassword::class.java)
            startActivity(intent)
        }

        textViewRegistrati.setOnClickListener{
            //L'utente viene riportato alla pagina per registrarsi
            //val intent = Intent((this, ::class.java)
         //   startActivity(intent))
        }




    }
}



