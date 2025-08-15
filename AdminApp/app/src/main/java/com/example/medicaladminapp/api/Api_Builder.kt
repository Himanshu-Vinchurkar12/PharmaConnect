package com.example.medicaladminapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {


    val api: ApiServices = Retrofit.Builder()
        .client(OkHttpClient.Builder().build())
        .baseUrl("https://himanshu21.pythonanywhere.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiServices::class.java)
}
