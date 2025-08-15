package com.example.medicaladminapp.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.rounded.AddBox
import androidx.compose.material.icons.rounded.AddCard
import androidx.compose.material.icons.rounded.AddShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medicaladminapp.screens.nav.Routes
import com.example.medicaladminapp.viewModel.MyViewModels

@Composable
fun AddProductScreen(
    viewModels: MyViewModels = hiltViewModel(),
    navHostController: NavHostController
) {


    val ProductName = remember { mutableStateOf("") }
    val Price = remember { mutableStateOf("") }
    val Category = remember { mutableStateOf("") }
    val Stock = remember { mutableStateOf("") }

    val addProduct = viewModels.addProduct.collectAsState()


    val context = LocalContext.current

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    when {
        addProduct.value.isLoading -> {
            CircularProgressIndicator()
        }

        addProduct.value.isError != null -> {
            Toast.makeText(context, addProduct.value.isError, Toast.LENGTH_SHORT).show()
        }

        addProduct.value.isdata != null -> {

        }


    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.White, Color(0xFF84D7D2))
                )
            ),
    ) {
        Spacer(Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(25.dp))
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        navHostController.navigate(Routes.HomeScreenRoutes)
                    }
                    .size((screenWidth.value * 0.08).dp)
            )
            Spacer(Modifier.width(15.dp))
            Text(
                text = "Add Product",
                fontSize = (screenWidth.value * 0.08).sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.width(15.dp))
            Icon(
                imageVector = Icons.Rounded.AddShoppingCart,
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        }
        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp),

            ) {

            Text(text = "Product Name", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = ProductName.value,
                onValueChange = { ProductName.value = it },
                placeholder = {
                    Text(
                        text = "Enter Product Name",
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White
                )
            )
            Spacer(Modifier.height(12.dp))
            Text(text = "Category", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = Category.value,
                onValueChange = { Category.value = it },
                placeholder = {
                    Text(
                        "Enter Category",
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White
                )
            )

            Spacer(Modifier.height(12.dp))
            Text(text = "Price (₹)", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = Price.value,
                onValueChange = { Price.value = it },
                placeholder = {
                    Text(
                        "Enter Price",
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White
                )
            )

            Spacer(Modifier.height(12.dp))
            Text(text = "Product Stock", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = Stock.value,
                onValueChange = { Stock.value = it },
                placeholder = {
                    Text(
                        text = "Product Stock",
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White
                ),
                shape = RoundedCornerShape(15.dp),

                )

            Spacer(Modifier.height(30.dp))
            ElevatedButton(
                onClick = {
                    if (ProductName.value.isEmpty() || Price.value.isEmpty() || Category.value.isEmpty() || Stock.value.isEmpty()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@ElevatedButton
                    } else {
                        viewModels.addProduct(
                            product_name = ProductName.value,
                            price = Price.value.toFloat(),
                            category = Category.value,
                            stock = Stock.value.toInt()
                        )
                        Toast.makeText(context, "Product Added", Toast.LENGTH_SHORT).show()
                        navHostController.navigate(Routes.TabScreenForProduct)
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF026C60)),
                elevation = ButtonDefaults.buttonElevation(8.dp),
            ) {
                Text(text = "Add Product", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            }


        }


    }

}