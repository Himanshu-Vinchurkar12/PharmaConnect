package com.example.medicalstoreapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalstoreapp.common.Results
import com.example.medicalstoreapp.models.AddOrderResponse
import com.example.medicalstoreapp.models.CreateUserResponse
import com.example.medicalstoreapp.models.LoginResponse
import com.example.medicalstoreapp.models.ProductModules
import com.example.medicalstoreapp.models.UsersModels
import com.example.medicalstoreapp.models.getSpecificUserResponse
import com.example.medicalstoreapp.models.updateUserResponse
import com.example.medicalstoreapp.prefData.MyPreference
import com.example.medicalstoreapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repo: Repo, private val prefs: MyPreference) :
    ViewModel() {

    private val _getAllUsers = MutableStateFlow(GetAllUsers())
    val getAllUsers = _getAllUsers.asStateFlow()

    private val _createUser = MutableStateFlow(CreateUserState())
    val createUser = _createUser.asStateFlow()

    private val _login = MutableStateFlow<LoginState?>(null)
    val login = _login.asStateFlow()


    private val _getSpecificUsers = MutableStateFlow(GetSpecificUsers())
    val getSpecificUsers = _getSpecificUsers.asStateFlow()

    private val _updateUserDetails = MutableStateFlow(UpdateUserDetails())
    val updateUserDetails = _updateUserDetails.asStateFlow()

    private val _getAllproducts = MutableStateFlow(GetAllproducts())
    val getAllproducts = _getAllproducts.asStateFlow()

    private val _addOrderDetails = MutableStateFlow(AddOrderDetailsState())
    val addOrderDetails = _addOrderDetails.asStateFlow()


    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllUsers().collect {
                when (it) {
                    is Results.Success -> {
                        _getAllUsers.value = GetAllUsers(isdata = it.data.body())

                    }

                    is Results.Error -> {
                        _getAllUsers.value = GetAllUsers(isError = it.message)
                    }

                    is Results.Loading -> {
                        _getAllUsers.value = GetAllUsers(isLoading = true)
                    }
                }
            }

        }
    }

    fun login(
        email: String,
        password: String

    ) {

        viewModelScope.launch(Dispatchers.IO) {
            repo.login(
                email,
                password
            ).collect {
                when (it) {
                    is Results.Loading -> {
                        _login.value = LoginState(isLoading = true)
                    }

                    is Results.Success -> {
                        val response = it.data.body()
                        _login.value = LoginState(isData = response, isLoading = false)

                        response?.userId.let { userId ->
                            prefs.saveUserId(userId.toString())
                        }
                    }

                    is Results.Error -> {
                        _login.value = LoginState(isError = it.message, isLoading = false)
                    }
                }
            }
        }

    }


    fun createUser(
        name: String,
        password: String,
        email: String,
        address: String,
        phoneNumber: String,
        pin_code: String,
        shope_name: String

    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.createUser(
                name,
                password,
                email,
                address,
                phoneNumber,
                pin_code,
                shope_name
            ).collect {
                when (it) {
                    is Results.Loading -> {
                        _createUser.value = CreateUserState(isLoading = true)
                    }

                    is Results.Success -> {
                        _createUser.value = CreateUserState(data = it.data.body(), isLoading = false)
                    }

                    is Results.Error -> {
                        _createUser.value = CreateUserState(error = it.message, isLoading = false)
                    }
                }

            }
        }

    }

    val _userIdByPref = MutableStateFlow<String?>(null)
    val userIdByPref = _userIdByPref.asStateFlow()


    suspend fun getUserIdByPref() {
        prefs.GetUser.collect {
            _userIdByPref.value = it
            Log.d("TAG", "UserId from Pref: $it")
        }
    }

    fun getSpecificUsers(
        user_id: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getSpecificUsers(user_id).collect {
                when (it) {
                    is Results.Loading -> {
                        _getSpecificUsers.value = GetSpecificUsers(isLoading = true)
                    }

                    is Results.Error -> {
                        _getSpecificUsers.value =
                            GetSpecificUsers(isError = it.message, isLoading = false)
                    }

                    is Results.Success -> {
                        _getSpecificUsers.value =
                            GetSpecificUsers(isData = it.data.body(), isLoading = false)


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

    fun addOrderDetails(
        product_id: String?,
        product_name: String?,
        user_id: String?,
        shope_name: String?,
        is_Approved: Int?,
        user_name: String?,
        massege: String?,
        quantity: Int?,
        category: String?,
        price: Float?,
        total_price: Float?,

        ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addOrderDetails(
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
            ).collect {
                when (it) {
                    is Results.Loading -> {
                        _addOrderDetails.value = AddOrderDetailsState(isLoading = true)

                    }

                    is Results.Error -> {
                        _addOrderDetails.value =
                            AddOrderDetailsState(isError = it.message, isLoading = false)
                    }

                    is Results.Success -> {
                        _addOrderDetails.value =
                            AddOrderDetailsState(isData = it.data.body(), isLoading = false)


                    }
                }
            }
        }

    }

    fun getAllproducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllproducts().collect {
                when (it) {
                    is Results.Loading -> {
                        _getAllproducts.value = GetAllproducts(isLoading = true)
                    }

                    is Results.Error -> {
                        _getAllproducts.value =
                            GetAllproducts(isError = it.message, isLoading = false)
                    }

                    is Results.Success -> {
                        _getAllproducts.value =
                            GetAllproducts(isdata = it.data.body(), isLoading = false)


                    }
                }
            }
        }
    }


    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            prefs.clearUserId()
            _userIdByPref.value = null // clear the state as well
        }
    }

}


data class GetAllUsers(
    val isLoading: Boolean = false,
    val isdata: UsersModels? = null,
    val isError: String? = null

)
data class CreateUserState(
    val data: CreateUserResponse? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)
data class LoginState(
    val isData: LoginResponse? = null,
    val isError: String? = null,
    val isLoading: Boolean = false
)
data class GetSpecificUsers(
    val isLoading: Boolean = false,
    val isError: String? = null,
    val isData: getSpecificUserResponse? = null
)
data class GetAllproducts(
    val isLoading: Boolean = false,
    val isdata: ProductModules? = null,
    val isError: String? = null
)
data class AddOrderDetailsState(
    val isLoading: Boolean = false,
    val isData: AddOrderResponse? = null,
    val isError: String? = null
)
data class UpdateUserDetails(
    val isLoading : Boolean = false,
    val  isdata : updateUserResponse?  = null,
    val isError : String? = null
)



