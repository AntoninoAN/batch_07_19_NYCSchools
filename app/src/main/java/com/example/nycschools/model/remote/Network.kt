package com.example.nycschools.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    val service: NYCApi by lazy {
        initRetrofit()
    }

    private fun initRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NYCApi::class.java)
}