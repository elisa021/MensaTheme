package com.example.mensapalermo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mensapalermo.retrofit.UserAPI
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrazioneActivity : AppCompatActivity() {

    private lateinit var userAPI: UserAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrazione)

        val retrofit = Retrofit.Builder()
            .baseUrl(UserAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userAPI = retrofit.create(UserAPI::class.java)

        val textNome : EditText = findViewById(R.id.textNome) // Assicurati che l'ID sia corretto
        val  textCognome : EditText = findViewById(R.id.textCognome)
        val textTelefono : EditText = findViewById(R.id.textTelefono)
        val textEmail : EditText = findViewById(R.id.textEmail)
        val textPassword : EditText= findViewById(R.id.textPassword)
        val textConfermaPassword : EditText = findViewById(R.id.textConferma_password)
        val buttonRegistrati : Button = findViewById(R.id.buttonRegistrati) // Assicurati che l'ID sia corretto
        val buttonAccedi : Button = findViewById(R.id.buttonAccedi)

        buttonRegistrati.setOnClickListener {
            val nome = textNome.text.toString()
            val cognome = textCognome.text.toString()
            val telefono = textTelefono.text.toString()
            val email = textEmail.text.toString()
            val password = textPassword.text.toString()
            val confermaPassword = textConfermaPassword.text.toString()

            // Validazione dei dati di input
            if (nome.isEmpty() || cognome.isEmpty() || telefono.isEmpty() || email.isEmpty() || password.isEmpty() || confermaPassword.isEmpty()) {
                Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confermaPassword) {
                Toast.makeText(this, "Le password non coincidono", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val jsonObject = JsonObject().apply {
                addProperty("nome", nome)
                addProperty("cognome", cognome)
                addProperty("telefono", telefono)
                addProperty("email", email)
                addProperty("password", password)
            }

            userAPI.createUtente(jsonObject).enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@RegistrazioneActivity,
                            "Registrazione completata",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@RegistrazioneActivity,
                            "Registrazione fallita",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toast.makeText(
                        this@RegistrazioneActivity,
                        "Errore di rete",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        // Gestire l'evento di click sul link "Accedi"
        buttonAccedi.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "Naviga alla schermata di accesso", Toast.LENGTH_SHORT).show()
        }
    }
}
