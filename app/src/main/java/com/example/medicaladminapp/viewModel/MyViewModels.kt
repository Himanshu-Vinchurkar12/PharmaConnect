package com.example.medicaladminapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import com.example.medicaladminapp.models.getSpecificUser
import com.example.medicaladminapp.models.updateUserResponse
import com.example.medicaladminapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModels @Inject  constructor( private  val repo: Repo): ViewModel() {

    private val _getAllUsers = MutableStateFlow(GetAllUsers())
    val  getAllUsers  = _getAllUsers.asStateFlow()

    private  val _getSpecificUser = MutableStateFlow(GetSpecificUser())
    val getSpecificUser = _getSpecificUser.asStateFlow()

    private val _deleteSpecificUser = MutableStateFlow(DeleteSpecificUser())
    val deleteSpecificUser = _deleteSpecificUser.asStateFlow()

    private val _updateUserDetails = MutableStateFlow(UpdateUserDetails())
    val updateUserDetails = _updateUserDetails.asStateFlow()


    private  val _addProduct = MutableStateFlow(AddProduct())
    val addProduct = _addProduct.asStateFlow()

    private val _getAllproducts = MutableStateFlow(GetAllproducts())
    val getAllproducts = _getAllproducts.asStateFlow()

    private  val _deleteSpecificProduct = MutableStateFlow(DeleteSpecificProduct())
    val deleteSpecificProduct = _deleteSpecificProduct.asStateFlow()


    private val _getAllOrderDetails = MutableStateFlow(GetAllOrderDetails())
    val getAllOrderDetails = _getAllOrderDetails.asStateFlow()

    private val _deleteSpecificOrder = MutableStateFlow(DeleteSpecificOrder())
    val deleteSpecificOrder = _deleteSpecificOrder.asStateFlow()


    private val _approveOrder = MutableStateFlow(ApproveOrder())
    val approveOrder = _approveOrder.asStateFlow()





    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllUsers().collect {
                when(it){
                    is Results.Success ->{
                        _getAllUsers.value = GetAllUsers(isdata = it.data.body())
                    }
                    is Results.Error ->{
                        _getAllUsers.value = GetAllUsers(isError = it.message)
                    }
                    is Results.Loading ->{
                        _getAllUsers.value = GetAllUsers(isLoading = true)
                    }
                }
            }

        }
    }



    fun getSpecificUsers(
        user_id : String
    ){
        viewModelScope.launch(Dispatchers.IO){
            repo.getSpecificUsers(user_id).collect{
                when(it){
                    is Results.Loading -> {
                        _getSpecificUser.value = GetSpecificUser(isLoading = true)
                    }
                    is Results.Error -> {
                        _getSpecificUser.value = GetSpecificUser(isError = it.message)
                    }
                    is Results.Success -> {
                        _getSpecificUser.value = GetSpecificUser(isdata = it.data.body())
                    }

                }
            }

        }

    }


    fun deleteSpecificUser(
        userId : String
    )
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repo.deleteSpecificUser(userId).collect{
                when(it){
                    is Results.Success -> {
                        _deleteSpecificUser.value = DeleteSpecificUser(isdata = it.data.body())
                    }
                    is Results.Error -> {
                        _deleteSpecificUser.value = DeleteSpecificUser(isError = it.message)
                    }
                    is Results.Loading -> {
                        _deleteSpecificUser.value = DeleteSpecificUser(isLoading = true)
                    }
                }
            }

        }

    }

    fun deleteSpecificProduct(
        productId : String
    ){
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteSpecificProduct(productId).collect{
                when(it){
                    is Results.Loading -> {
                        _deleteSpecificProduct.value = DeleteSpecificProduct(isLoading = true)
                    }
                    is Results.Error -> {
                        _deleteSpecificProduct.value = DeleteSpecificProduct(isError = it.message, isLoading = false)
                    }
                    is Results.Success ->{
                        _deleteSpecificProduct.value = DeleteSpecificProduct(isdata = it.data.body(), isLoading = false)
                    }

                }
            }
        }

    }

    fun deleteSpecificOrder(
        orderId : String
    ){
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteSpecificOrder(orderId).collect {
                when(it){
                    is Results.Loading -> {
                        _deleteSpecificOrder.value = DeleteSpecificOrder(isLoading = true)
                    }
                    is Results.Error -> {
                        _deleteSpecificOrder.value = DeleteSpecificOrder(isError = it.message, isLoading = false)
                    }
                    is Results.Success -> {
                        _deleteSpecificOrder.value = DeleteSpecificOrder(isdata = it.data.body(), isLoading = false)
                    }
                }
            }
        }
    }


    fun updateUserDetails(
        userId : String? = null,
        name: String?= null,
        password: String?= null,
        email: String?= null,
        address: String?= null,
        phone_number: String?= null,
        pin_code: String?= null,
        shope_name: String?= null,
        is_Approved : Int? = null,

    ){
        viewModelScope.launch(Dispatchers.IO){
            repo.updateUserDetails(
                userId,name,password,email,address,phone_number,pin_code,shope_name,is_Approved
            ).collect{
                when(it){
                    is Results.Success -> {
                        _updateUserDetails.value = UpdateUserDetails(isdata = it.data.body())
                    }
                    is Results.Error -> {
                        _updateUserDetails.value = UpdateUserDetails(isError = it.message)
                    }
                    is Results.Loading -> {
                        _updateUserDetails.value = UpdateUserDetails(isLoading = true)
                    }

                }
            }

        }

    }

    fun approveOrder(
        orderId: String?,
        is_Approved: Int?
    ){
        viewModelScope.launch(Dispatchers.IO){
            repo.approveOrder(
                orderId,
                is_Approved
            ).collect{
                when(it){
                    is Results.Loading -> {
                        _approveOrder.value = ApproveOrder(isLoading = true)
                    }
                    is Results.Error -> {
                        _approveOrder.value = ApproveOrder(isError = it.message, isLoading = false)
                    }
                    is Results.Success -> {
                        _approveOrder.value = ApproveOrder(isdata = it.data.body(), isLoading = false)
                    }
                }
            }
        }

    }


    fun  addProduct(
        product_name : String,
        price : Float,
        category : String,
        stock : Int,
    ){
        viewModelScope.launch(Dispatchers.IO){
            repo.addProduct(
                product_name,
                price,
                category,
                stock
            ).collect{
                when(it){
                    is Results.Loading -> {
                        _addProduct.value = AddProduct(isLoading = true)
                    }
                    is Results.Error -> {
                        _addProduct.value = AddProduct(isError = it.message , isLoading = false)
                    }
                    is Results.Success -> {
                        _addProduct.value = AddProduct(isdata = it.data.body(), isLoading = false)
                    }
                }

            }
        }

    }
    fun getAllproducts(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getAllproducts().collect{
                when(it) {
                    is Results.Loading -> {
                        _getAllproducts.value = GetAllproducts(isLoading = true)
                    }

                    is Results.Error -> {
                        _getAllproducts.value = GetAllproducts(isError = it.message, isLoading = false)
                    }

                    is Results.Success -> {
                        _getAllproducts.value = GetAllproducts(isdata = it.data.body(), isLoading = false)


                    }
                }
            }
        }
    }

    fun getAllOrderDetails(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getAllOrderDetails().collect{
                when(it){
                    is Results.Loading -> {
                        _getAllOrderDetails.value = GetAllOrderDetails(isLoading = true)
                    }
                    is Results.Error -> {
                        _getAllOrderDetails.value = GetAllOrderDetails(isError = it.message, isLoading = false)
                    }
                    is Results.Success -> {
                        _getAllOrderDetails.value = GetAllOrderDetails(isdata = it.data.body(), isLoading = false)
                    }

                }
            }
        }
    }





}

data class GetAllUsers(
    val isLoading : Boolean = false,
    val  isdata : UsersModels?  = null,
    val isError : String? = null

)

data class GetSpecificUser(
    val isLoading: Boolean = false,
    val isdata: getSpecificUser? = null,
    val  isError: String? = null
)

data class DeleteSpecificUser(
    val isLoading : Boolean = false,
    val  isdata : deleteSpecificUser?  = null,
    val isError : String? = null
)

data class UpdateUserDetails(
    val isLoading : Boolean = false,
    val  isdata : updateUserResponse?  = null,
    val isError : String? = null
)


data class AddProduct(
    val isLoading : Boolean = false,
    val isdata : AddProductResponse? = null,
    val isError: String? = null
)

data class GetAllproducts(
    val isLoading : Boolean = false,
    val isdata : ProductModules? = null,
    val isError: String? = null
)

data class DeleteSpecificProduct(
    val isLoading: Boolean = false,
    val isdata: deleteSpecificProduct? = null,
    val isError: String? = null
)

data class DeleteSpecificOrder(
    val isLoading: Boolean = false,
    val isdata: deleteSpecificOrder? = null,
    val isError: String? = null
)

data class GetAllOrderDetails(
    val isLoading : Boolean = false,
    val isdata : OrderModules? = null,
    val isError: String? = null
)

data class ApproveOrder(
    val isLoading : Boolean = false,
    val isdata : ApproveOrderResponse? = null,
    val isError: String? = null

)