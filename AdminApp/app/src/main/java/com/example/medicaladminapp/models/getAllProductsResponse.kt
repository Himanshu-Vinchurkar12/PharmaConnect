package com.example.medicaladminapp.models

data class getAllProductsResponse(
    val category: String,
    val id: Int,
    val price: Double,
    val product_id: String,
    val product_name: String,
    val stock: Int
)