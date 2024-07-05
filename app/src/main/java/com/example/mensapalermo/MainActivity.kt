package com.example.mensapalermo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mensapalermo.retrofit.UserAPI
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var userAPI: UserAPI

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl(UserAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userAPI = retrofit.create(UserAPI::class.java)

        val isAuthenticated = checkAuthentication()

        if (isAuthenticated) {
            // Se l'utente è autenticato, avvia l'HomePage
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        } else {
            // Se l'utente non è autenticato, avvia loginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        finish()

        findViewById<Button>(R.id.buttonRecupera).setOnClickListener {
            val intent = Intent(this, RecuperaPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkAuthentication(): Boolean {
        var isAuthenticated = false

        userAPI.createUtente().enqueue(/* callback = */ object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) =
                if (response.isSuccessful) {
                    // Se la chiamata API ha avuto successo, l'utente è autenticato
                    isAuthenticated = true
                } else {
                    // Se la chiamata API ha fallito, l'utente non è autenticato
                    isAuthenticated = false
                }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                // Se c'è stato un errore di rete, l'utente non è autenticato
                isAuthenticated = false
            }
        })

        return isAuthenticated
    }
}


