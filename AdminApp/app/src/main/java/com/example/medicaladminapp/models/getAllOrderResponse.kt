package com.example.medicaladminapp.models

data class getAllOrderResponse(
    val category: String,
    val date_of_order_creation: String,
    val id: Int,
    val is_Approved: Int,
    val massege: String,
    val order_id: String,
    val price: Double,
    val product_id: String,
    val product_name: String,
    val quantity: Int,
    val shope_name: String,
    val total_price: Double,
    val user_id: String,
    val user_name: String
)