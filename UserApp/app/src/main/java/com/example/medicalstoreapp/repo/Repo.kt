package com.example.medicalstoreapp.repo


import android.util.Log
import com.example.medicalstoreapp.api.Api_Builder
import com.example.medicalstoreapp.common.Results
import com.example.medicalstoreapp.models.AddOrderResponse
import com.example.medicalstoreapp.models.CreateUserResponse
import com.example.medicalstoreapp.models.LoginResponse
import com.example.medicalstoreapp.models.ProductModules
import com.example.medicalstoreapp.models.UsersModels
import com.example.medicalstoreapp.models.getSpecificUserResponse
import com.example.medicalstoreapp.models.updateUserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class Repo @Inject constructor(private val apiServices: Api_Builder) {

    suspend fun getAllUsers() : Flow<Results<Response<UsersModels>>> = flow {
        emit(Results.Loading)


        try {
            val response = apiServices.api.getAllUsers()
            emit(Results.Success(response))

        }
        catch (e : Exception){
            emit(Results.Error(e.message.toString()))

        }

    }
    suspend fun login(
        email : String,
        password : String

    ) : Flow<Results<Response<LoginResponse>>> = flow {
        emit(Results.Loading)


        try {
            val response = apiServices.api.login(
                email ,
                password
            )
            emit(Results.Success(response))

        }
        catch(e: Exception){
            emit(Results.Error(e.message.toString()))

        }
    }

    suspend fun createUser(
        name : String,
        password : String,
        email : String,
        address : String,
        phoneNumber : String,
        pin_code : String,
        shope_name : String
    ) : Flow<Results<Response<CreateUserResponse>>> = flow {
        emit(Results.Loading)

        try {
            val response = apiServices.api.createUser(
                name ,
                password ,
                email ,
                address ,
                phoneNumber ,
                pin_code ,
                shope_name
            )
            emit(Results.Success(response))

        }
        catch (e: Exception){
            emit(Results.Error(e.message.toString()))

        }

    }

    suspend fun getSpecificUsers(
        user_id : String
    ): Flow<Results<Response<getSpecificUserResponse>>> = flow {
        emit(Results.Loading)

        try {
            val response = apiServices.api.getSpecificUsers(user_id)
            if (response.isSuccessful) {
                emit(Results.Success(response))
                Log.d("Repo", "getSpecificUser: ${response.body()}")
            } else {
                emit(Results.Error("Error: ${response.code()} - ${response.message()}"))
            }
        }
        catch ( e : Exception){
            emit(Results.Error(e.message.toString()))
        }

    }

    suspend fun updateUserDetails(
        userId: String?,
        name: String?,
        password: String?,
        email: String?,
        address: String?,
        phone_number: String?,
        pin_code: String?,
        shope_name: String?,
        is_Approved: Int?

    ): Flow<Results<Response<updateUserResponse>>> = flow {
        emit(Results.Loading)

        try {
            val responseofupdate = apiServices.api.updateUserDetails(
                userId = userId,
                name = name,
                password = password,
                email = email,
                address = address,
                phone_number = phone_number,
                pin_code = pin_code,
                shope_name = shope_name,
                is_Approved = is_Approved

            )
            emit(Results.Success(responseofupdate))

        } catch (e: Exception) {
            emit(Results.Error(e.message.toString()))
        }
    }




    suspend fun addOrderDetails(
        product_id : String?,
        product_name : String?,
        user_id : String?,
        shope_name : String?,
        is_Approved : Int?,
        user_name : String?,
        massege : String?,
        quantity : Int?,
        category : String?,
        price : Float?,
        total_price : Float?,
    ): Flow<Results<Response<AddOrderResponse>>> = flow {
        emit(Results.Loading)


        try {
            val  responseaddOrder = apiServices.api.addOrderDetails(

                product_id = product_id,
                product_name = product_name,
                user_id = user_id,
                shope_name = shope_name,
                is_Approved = is_Approved,
                user_name = user_name,
                massege = massege,
                quantity = quantity,
                category = category,
                price = price,
                total_price = total_price,
            )
            emit(Results.Success(responseaddOrder))

        }
        catch (e : Exception){
            emit(Results.Error(e.message.toString()))

        }
    }




    suspend fun getAllproducts(): Flow<Results<Response<ProductModules>>> = flow {
        emit(Results.Loading)


        try {
            val responseOfGetallProduct = apiServices.api.getAllproducts()
            emit(Results.Success(responseOfGetallProduct))
        } catch (e: Exception) {
            emit(Results.Error(e.message.toString()))
        }

    }
}