package com.example.medicalstoreapp.api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Api_Builder {


    val api: Api_Services = Retrofit.Builder()
        .client(OkHttpClient.Builder().build())
        .baseUrl("https://himanshu21.pythonanywhere.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api_Services::class.java)
}

