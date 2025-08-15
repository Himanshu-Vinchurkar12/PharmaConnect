package com.example.medicaladminapp.api

import com.example.medicaladminapp.models.AddProductResponse
import com.example.medicaladminapp.models.ApproveOrderResponse
import com.example.medicaladminapp.models.OrderModules
import com.example.medicaladminapp.models.ProductModules
import com.example.medicaladminapp.models.UsersModels
import com.example.medicaladminapp.models.deleteSpecificOrder
import com.example.medicaladminapp.models.deleteSpecificProduct
import com.example.medicaladminapp.models.deleteSpecificUser
import com.example.medicaladminapp.models.getSpecificUser
import com.example.medicaladminapp.models.updateUserResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiServices {

    @GET("getAllUsers")
    suspend fun getAllUsers() : Response<UsersModels>


    @FormUrlEncoded
    @POST("getSpecificUsers")
    suspend fun getSpecificUsers(
        @Field("user_id") user_id : String
    ) : Response<getSpecificUser>


    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "deleteSpecificUser",hasBody = true)
    suspend fun deleteSpecificUser(
        @Field("userId") userId : String
    ) : Response<deleteSpecificUser>


    @FormUrlEncoded
    @PATCH("updateUserDetails")
    suspend fun updateUserDetails(
        @Field("userId") userId : String?,
        @Field("name") name: String?,
        @Field("password") password: String?,
        @Field("email") email: String?,
        @Field("address") address: String?,
        @Field("phone_number") phone_number: String?,
        @Field("pin_code") pin_code: String?,
        @Field("shope_name") shope_name: String?,
        @Field("is_Approved") is_Approved : Int?
    ): Response<updateUserResponse>


    @FormUrlEncoded
    @POST("addProduct")
    suspend fun addProduct(
        @Field("product_name") product_name : String,
        @Field("price") price : Float,
        @Field("category") category : String,
        @Field("stock") stock : Int,
    ) : Response<AddProductResponse>

    @GET("getAllproducts")
    suspend fun getAllproducts() : Response<ProductModules>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "deleteSpecificProduct", hasBody = true)
    suspend fun deleteSpecificProduct(
        @Field("productId") productId : String
    ) : Response<deleteSpecificProduct>


    @GET("getAllOrderDetails")
    suspend fun getAllOrderDetails() : Response<OrderModules>

    @FormUrlEncoded
    @PATCH("approveOrder")
    suspend fun approveOrder(
        @Field("orderId") orderId : String?,
        @Field("is_Approved") is_Approved : Int?
    ): Response<ApproveOrderResponse>


    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "deleteSpecificOrder", hasBody = true)
    suspend fun deleteSpecificOrder(
        @Field("orderId") orderId : String
    ): Response<deleteSpecificOrder>




}