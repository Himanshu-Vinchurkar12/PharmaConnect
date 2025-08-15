package com.example.medicaladminapp.repo

import android.util.Log
import com.example.medicaladminapp.api.ApiBuilder
import com.example.medicaladminapp.common.Results
import com.example.medicaladminapp.models.AddProductResponse
import com.example.medicaladminapp.models.ApproveOrderResponse
import com.example.medicaladminapp.models.OrderModules
import com.example.medicaladminapp.models.ProductModules
import com.example.medicaladminapp.models.UsersModels
import com.example.medicaladminapp.models.UsersModelsItem
import com.example.medicaladminapp.models.deleteSpecificOrder
import com.example.medicaladminapp.models.deleteSpecificProduct
import com.example.medicaladminapp.models.deleteSpecificUser
import com.example.medicaladminapp.models.getAllProductsResponse
import com.example.medicaladminapp.models.getSpecificUser
import com.example.medicaladminapp.models.updateUserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class Repo @Inject constructor( private  val apiServices: ApiBuilder) {


    suspend fun getAllUsers(): Flow<Results<Response<UsersModels>>> = flow {
        emit(Results.Loading)

        try {
            val response = apiServices.api.getAllUsers()
            emit(Results.Success(response))
        } catch (e: Exception) {
            emit(Results.Error(e.message.toString()))

        }

    }


    suspend fun getSpecificUsers(
        user_id: String
    ): Flow<Results<Response<getSpecificUser>>> = flow {

        emit(Results.Loading)

        try {
            val response = apiServices.api.getSpecificUsers(user_id)
            emit(Results.Success(response))
        } catch (e: Exception) {
            emit(Results.Error(e.message.toString()))

        }
    }

    suspend fun deleteSpecificUser(
        userId: String

    ): Flow<Results<Response<deleteSpecificUser>>> = flow {
        emit(Results.Loading)

        try {
            val responseofdelete = apiServices.api.deleteSpecificUser(
                userId
            )
            emit(Results.Success(responseofdelete))
        } catch (e: Exception) {
            emit(Results.Error(e.message.toString()))
        }
    }

    suspend fun deleteSpecificProduct(
        productId : String
    ) : Flow<Results<Response<deleteSpecificProduct>>> = flow {
        emit(Results.Loading)


        try {
            val responseofdelete = apiServices.api.deleteSpecificProduct(
                productId
            )
            emit(Results.Success(responseofdelete))

        }
        catch (e : Exception){
            emit(Results.Error(e.message.toString()))
        }

    }

    suspend fun deleteSpecificOrder(
        orderId : String
    ) : Flow<Results<Response<deleteSpecificOrder>>> = flow {
        emit(Results.Loading)

       try {
           val responseofdelete = apiServices.api.deleteSpecificOrder(orderId)
           emit(Results.Success(responseofdelete))
       }
       catch (e : Exception){
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

    suspend fun approveOrder(
        orderId: String?,
        is_Approved: Int?

    ): Flow<Results<Response<ApproveOrderResponse>>> = flow{
        emit(Results.Loading)

        try {
            val responseofapprove = apiServices.api.approveOrder(
                orderId = orderId,
                is_Approved = is_Approved
            )
            emit(Results.Success(responseofapprove))

        }
        catch (e: Exception) {
            emit(Results.Error(e.message.toString()))
        }
    }

    suspend fun addProduct(
        product_name: String,
        price: Float,
        category: String,
        stock: Int,

        ): Flow<Results<Response<AddProductResponse>>> = flow {
        emit(Results.Loading)


        try {
            val responseOfAddProduct = apiServices.api.addProduct(
                product_name,
                price,
                category,
                stock
            )
            emit(Results.Success(responseOfAddProduct))

        } catch (e: Exception) {
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

    suspend fun  getAllOrderDetails() : Flow<Results<Response<OrderModules>>> = flow {
        emit(Results.Loading)

        try {
            val responseOfGetallOrderDetails = apiServices.api.getAllOrderDetails()
            emit(Results.Success(responseOfGetallOrderDetails))

        }catch (e: Exception){
            emit(Results.Error(e.message.toString()))
        }


    }

}
