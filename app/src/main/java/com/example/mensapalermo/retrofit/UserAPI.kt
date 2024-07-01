package com.example.mensapalermo.retrofit

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.*

interface UserAPI {


    @DELETE("$USER_URI/{id}")
    fun deleteUtente(@Path("id") id: Int): Call<JsonObject>

    @PUT("$USER_URI/{id}")
    fun updateUtente(@Path("id") id: Int, @Body body: JsonObject): Call<JsonObject>

    @POST(USER_URI)
    fun createUtente(@Body body: JsonObject): Call<JsonObject>

    @GET(USER_URI)
    fun getUtenti(): Call<JsonArray>
    abstract fun createUtente(): Call<JsonObject>

    @POST("$USER_URI/recupera-password")
    fun recuperaPassword(@Body body: JsonObject): Call<JsonObject>

    @POST("$USER_URI/login")
    fun login(@Body body: JsonObject): Call<JsonObject>

    companion object {
        const val BASE_URL = "http://172.20.10.3:9000/"
        const val USER_URI = "pwm/utenti"
    }
}

