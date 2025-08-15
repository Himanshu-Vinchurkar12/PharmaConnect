package com.example.medicalstoreapp.screen.TabScreenForProduct

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medicalstoreapp.R
import com.example.medicalstoreapp.models.getAllProductsResponse
import com.example.medicalstoreapp.screen.nav.Routes
import com.example.medicalstoreapp.viewModel.MyViewModel

@Composable
fun SyrupsScreens(
    navHostController: NavHostController,
    viewModels: MyViewModel = hiltViewModel(),
    userId: String
) {

    LaunchedEffect(key1 = Unit) {
        viewModels.getAllproducts()
        viewModels.getSpecificUsers(user_id = userId)
    }

    val state = viewModels.getAllproducts.collectAsState()
    val getSpecificUsers = viewModels.getSpecificUsers.collectAsState()



    when {
        getSpecificUsers.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return
        }

        getSpecificUsers.value.isError != null -> {
            Log.d("TAG", "GetUser: ${getSpecificUsers.value.isError}")
            Toast.makeText(LocalContext.current, getSpecificUsers.value.isError.toString(), Toast.LENGTH_SHORT).show()
            return
        }

        getSpecificUsers.value.isData != null -> {
            val user = getSpecificUsers.value.isData
            Log.d("TAG", "GetUserInEqu: $user")
        }
    }

    when {
        state.value.isLoading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.value.isError != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = state.value.isError.toString())
            }
        }

        state.value.isdata != null -> {

            val user = getSpecificUsers.value.isData?.firstOrNull()
            val actualuserId = user?.user_id ?:""

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color.White, Color(0xFF5EE5DA))
                        )
                    )
                    .padding(top = 5.dp)
            ) {

                Spacer(Modifier.height(60.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(text = "Syrups", fontSize = 40.sp, fontWeight = FontWeight.Bold)


                }
                Spacer(Modifier.height(50.dp))


                LazyColumn(

                ) {
                    val getSyrup = state.value.isdata
                    if (getSyrup != null) {
                        items(getSyrup) {
                            AllSyrup(
                                res = it,
                                navHostController = navHostController,
                                viewModels = viewModels,
                                userId = actualuserId
                            )
                        }
                    }


                }
            }
        }
    }

}


@Composable
fun AllSyrup(
    res: getAllProductsResponse,
    navHostController: NavHostController,
    viewModels: MyViewModel,
    userId: String
) {
    if (res.category.toString() == "Syrup") {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clickable {
                    Log.d("TAG", "AllEquipment in addorder in Syrup :${userId}")
                    navHostController.navigate(
                        Routes.AddOrderScreenRoutes(
                            product_name = res.product_name,
                            category = res.category,
                            price = res.price,
                            stock = res.stock,
                            user_id = userId,
                            product_id = res.product_id
                        )
                    )
                },
            colors = CardDefaults.cardColors(containerColor = Color(0xFFDFF6F4))
        ) {

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                Log.d("TAG", "AllEquipment in Syrup : ${userId.toString()}")
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(start = 10.dp)
                ) {
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Product Name : ${res.product_name}",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        "Category: ${res.category}",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        "Price: ₹ ${res.price}",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp
                    )
                    Spacer(Modifier.height(5.dp))
                    if (res.stock > 5) {
                        Text(
                            "In Stock ",
                            color = Color.Green,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            letterSpacing = 1.sp
                        )
                    }
                    else if (res.stock == 0) {
                        Text(
                            "Out of Stock",
                            color = Color.Red,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            letterSpacing = 1.sp
                        )
                    }
                    else {
                        Text(
                            "Only ${res.stock} left",
                            color = Color.Red,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            letterSpacing = 1.sp
                        )
                    }
                    Spacer(Modifier.height(10.dp))


                }


            }

        }

    }

}