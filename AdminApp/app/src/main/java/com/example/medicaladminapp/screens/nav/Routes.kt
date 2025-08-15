package com.example.medicaladminapp.screens.nav

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    object ApprovedUserScreenRoutes : Routes()

    @Serializable
    object PendingUserScreenRoutes : Routes()

    @Serializable
    data class DetailScreenRoutes
        (
        val address: String? =null,
        val block: Int? =null,
        val date_of_account_creation: String? =null,
        val email: String? =null,
        val id: Int? =null,
        val is_Approved: String? =null,
        val name: String? =null,
        val password: String? =null,
        val phone_number: String? =null,
        val pin_code: String? =null,
        val shope_name: String? =null,
        val user_id: String? =null
    ) : Routes()

    @Serializable
    data class DetailScreenForOrderRoutes(
        val category: String? =null,
        val date_of_order_creation: String? =null,
        val id: Int? =null,
        val is_Approved: Int? =null,
        val massege: String? =null,
        val order_id: String? =null,
        val price: Double? =null,
        val product_id: String? =null,
        val product_name: String? =null,
        val quantity: Int? =null,
        val shope_name: String? =null,
        val total_price: Double? =null,
        val user_id: String? =null,
        val user_name: String? =null
    ) : Routes()


    @Serializable
    data class UpdateScreenRoutes(
        val address: String? =null,
        val block: Int? =null,
        val date_of_account_creation: String? =null,
        val email: String? =null,
        val id: Int? =null,
        val is_Approved: String? =null,
        val name: String? =null,
        val password: String? =null,
        val phone_number: String? =null,
        val pin_code: String? =null,
        val shope_name: String? =null,
        val user_id: String? =null
    ) : Routes()


    @Serializable
   object TabScreenRoutes : Routes()

    @Serializable
    object TabScreenForProduct : Routes()


    @Serializable
    object AddProductScreenRoutes : Routes()

    @Serializable
    object TabletsScreensRoutes : Routes()

    @Serializable
    object SyrupsScreensRoutes : Routes()

    @Serializable
    object AntisepticScreensRoutes : Routes()

    @Serializable
    object EquipmentsScreensRoutes : Routes()

    @Serializable
    object HomeScreenRoutes : Routes()

    @Serializable
    object TabScreenForOrder : Routes()

    @Serializable
    object ApprovedOrderScreenRoutes : Routes()

    @Serializable
    object PendingOrderScreenRoutes : Routes()

}