package com.example.mensapalermo

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mensapalermo.retrofit.UserAPI
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecuperaPasswordActivity : AppCompatActivity() {

    private lateinit var userAPI: UserAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recupera_password)

        val etEmailRecuperaPassword: EditText = findViewById(R.id.etEmailRecupero)
        val buttonInviaRecuperaPassword: Button = findViewById(R.id.buttonRecupera)
        val customButton: LinearLayout = findViewById(R.id.custom_button)

        customButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(UserAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userAPI = retrofit.create(UserAPI::class.java)

        buttonInviaRecuperaPassword.setOnClickListener {
            val email = etEmailRecuperaPassword.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Inserisci il tuo indirizzo email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Inserisci un indirizzo email valido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val jsonObject = JsonObject().apply {
                addProperty("email", email)
            }

            userAPI.recuperaPassword(jsonObject).enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@RecuperaPasswordActivity,
                            "Email inviata",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@RecuperaPasswordActivity,
                            "Recupero password fallito: ${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toast.makeText(
                        this@RecuperaPasswordActivity,
                        "Errore di rete: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }
}
