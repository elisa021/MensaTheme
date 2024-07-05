package com.example.mensapalermo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mensapalermo.retrofit.UserAPI
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {

    private lateinit var userAPI: UserAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmailAddress: EditText = findViewById(R.id.etEmailAddress)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val buttonAccedi: Button = findViewById(R.id.buttonAccedi)
        val textViewPswDim: TextView = findViewById(R.id.textViewPswDim)
        val textViewRegistrati: TextView = findViewById(R.id.textViewRegistrati)

        val retrofit = Retrofit.Builder()
            .baseUrl(UserAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userAPI = retrofit.create(UserAPI::class.java)

        buttonAccedi.setOnClickListener {
            val email = etEmailAddress.text.toString()
            val password = etPassword.text.toString()

            val jsonObject = JsonObject().apply {
                addProperty("email", email)
                addProperty("password", password)
            }

            userAPI.login(jsonObject).enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                        val intent = Intent(this@LoginActivity, HomePageActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Credenziali non valide",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Errore di rete: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        textViewPswDim.setOnClickListener {
            val intent = Intent(this, RecuperaPasswordActivity::class.java)
            startActivity(intent)
        }

        textViewRegistrati.setOnClickListener {
            val intent = Intent(this, RegistrazioneActivity::class.java)
            startActivity(intent)
        }
    }
}
