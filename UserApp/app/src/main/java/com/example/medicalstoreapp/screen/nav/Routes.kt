package com.example.medicalstoreapp.screen.nav

import kotlinx.serialization.Serializable

sealed class Routes {


    @Serializable
    data class TabScreenForProduct(val user_id: String? = null)


    @Serializable
    object SignUpScreenRoutes : Routes()

    @Serializable
    object WaitingScreenRoutes : Routes()

    @Serializable
    object LogInReadyScreenRoutes : Routes()

    @Serializable
    object LogInScreenRoutes : Routes()

    @Serializable
    object StartScreenRoutes : Routes()

    @Serializable
    data class HomeScreenRoutes(
        val user_id: String? = null,
    ) : Routes()

    @Serializable
    data class ProfileScreenRoutes(
        val user_id: String? = null,
    ) : Routes()

    @Serializable
    data class EditScreenRoutes(
        val user_id: String? = null
    ): Routes()

    @Serializable
   data class   OrderHistoryScreenRoutes(
       val user_id: String? = null
   ) : Routes()

    @Serializable
    data class   PaymentScreenRoutes(
        val user_id: String? = null
    ) : Routes()

    @Serializable
    data class AntisepticScreensRoutes(val user_id: String? = null)
    @Serializable
    data class TabletsScreensRoutes(val user_id: String? = null)

    @Serializable
    data class SyrupsScreensRoutes(val user_id: String? = null)

    @Serializable
    data class EquipmentScreenRoutes(val user_id: String? = null)

    @Serializable
    data class HelpcenterScreenRoutes(val user_id: String? = null)

    @Serializable
    data class ContactUsScreenRoutes(val user_id: String? = null)

    @Serializable
    data class PrivacySecurityScreenRoutes(val user_id: String? = null)

    @Serializable
    data class AddOrderScreenRoutes(
        val user_id: String? = null,
        val product_id: String? = null,
        val product_name: String? = null,
        val category: String? = null,
        val price: Double? = null,
        val stock: Int? = null,
    ) : Routes()




}