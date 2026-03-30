package com.example.newzapp.ui.theme

import com.example.newzapp.DataClass.JsonConveter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("1/latest")
    suspend fun getitem(
        @Query("q") search: String,
        @Query("apikey") apikey: String,
        @Query("page") page: String? = null
    ): JsonConveter

    companion object {
        val BASE_URL = "https://newsdata.io/api/"
        fun create(): ApiService {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)

        }

    }
}