package com.example.medicalstoreapp.api

import com.example.medicalstoreapp.models.AddOrderResponse
import com.example.medicalstoreapp.models.CreateUserResponse
import com.example.medicalstoreapp.models.LoginResponse
import com.example.medicalstoreapp.models.ProductModules
import com.example.medicalstoreapp.models.UsersModels
import com.example.medicalstoreapp.models.UsersModelsItem
import com.example.medicalstoreapp.models.getSpecificUserResponse
import com.example.medicalstoreapp.models.updateUserResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface Api_Services {


    @GET("getAllUsers")
    suspend fun getAllUsers() : Response<UsersModels>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>


    @FormUrlEncoded
    @POST("createUser")
    suspend fun createUser(

        @Field("name") name: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("address") address : String,
        @Field("phoneNumber") phoneNumber : String,
        @Field("pin_code") pin_code : String,
        @Field("shope_name") shope_name : String,

    ) : Response<CreateUserResponse>


    @FormUrlEncoded
    @POST("getSpecificUsers")
    suspend fun getSpecificUsers(
        @Field("user_id") user_id : String
    ) : Response<getSpecificUserResponse>


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

    @GET("getAllproducts")
    suspend fun getAllproducts() : Response<ProductModules>



   @FormUrlEncoded
   @POST("addOrderDetails")
   suspend fun addOrderDetails(

       @Field("product_id") product_id : String?,
       @Field("product_name") product_name : String?,
       @Field("user_id") user_id : String?,
       @Field("shope_name") shope_name : String?,
       @Field("is_Approved") is_Approved : Int?,
       @Field("user_name") user_name : String?,
       @Field("massege") massege : String?,
       @Field("quantity") quantity : Int?,
       @Field("category") category : String?,
       @Field("price") price : Float?,
       @Field("total_price") total_price : Float?,

   ) : Response<AddOrderResponse>



}