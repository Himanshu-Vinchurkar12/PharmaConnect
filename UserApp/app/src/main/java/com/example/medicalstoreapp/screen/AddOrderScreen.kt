package com.example.medicalstoreapp.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.AddShoppingCart
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Shop
import androidx.compose.material.icons.rounded.Textsms
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medicalstoreapp.R
import com.example.medicalstoreapp.screen.nav.Routes
import com.example.medicalstoreapp.screen.nav.Routes.AddOrderScreenRoutes
import com.example.medicalstoreapp.viewModel.MyViewModel

@Composable
fun AddOrderScreen(
    res: AddOrderScreenRoutes,
    viewModel: MyViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val state = viewModel.addOrderDetails.collectAsState()

    val name = remember { mutableStateOf("") }
    val shopname = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }
    val quantity = remember { mutableStateOf("") }
    val massage = remember { mutableStateOf("") }

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val scrollState = rememberScrollState()


    when {
        state.value.isLoading -> {
            CircularProgressIndicator()
        }

        state.value.isError != null -> {
            Toast.makeText(context, state.value.isError, Toast.LENGTH_SHORT).show()
        }

        state.value.isData != null -> {
            Log.d("TAG", "AddOrderScreen: ${state.value.isData}")
            Toast.makeText(context, "Order Added Successfully", Toast.LENGTH_SHORT).show()
            navHostController.navigate(Routes.HomeScreenRoutes(user_id = res.user_id))
        }


    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.White, Color(0xFF77EADE)),
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        if (res.category == "Tablets") {
            Spacer(Modifier.height(screenHeight * 0.05f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenWidth * 0.04f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size((screenWidth * 0.08f))
                        .clickable {
                            navHostController.popBackStack()
                        }
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Place Order",
                    fontWeight = FontWeight.Bold,
                    fontSize = (screenWidth.value * 0.07).sp,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenWidth * 0.05f, vertical = screenHeight * 0.01f)
                    .clip(RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            )
            {
                Spacer(Modifier.height(screenHeight * 0.01f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.tablets),
                        contentDescription = null,
                        modifier = Modifier
                            .size(screenWidth * 0.25f)
                            .clip(shape = RoundedCornerShape(16.dp))
                    )
                    Spacer(Modifier.width(screenWidth * 0.03f))

                    Column(
                        modifier = Modifier.weight(2f)
                    ) {

                        Text(
                            text = "${res.product_name}",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = (screenWidth.value * 0.05f).sp
                        )
                        Spacer(Modifier.height(10.dp))

                        Text(
                            "Category: ${res.category}",
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Normal,
                            fontSize = (screenWidth.value * 0.04f).sp
                        )
                        Spacer(Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()

                        ) {
                            Text(
                                text = " ₹ ${res.price}",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenWidth.value * 0.05f).sp
                            )
                            res.stock?.let {
                                if (it > 5) {
                                    Row(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFB1F5EC),
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .padding(4.dp)
                                    ) {
                                        Spacer(Modifier.width(5.dp))
                                        Text(
                                            "In Stock ",
                                            color = Color(0xFF038679),
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = (screenWidth.value * 0.035f).sp,
                                            letterSpacing = 1.sp
                                        )
                                        Spacer(Modifier.width(5.dp))
                                    }

                                } else if (it == 0) {
                                    Row(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFF6AEAC),
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                            .padding(2.dp)
                                    ) {
                                        Spacer(Modifier.width(5.dp))
                                        Text(
                                            "Out of Stock",
                                            color = Color.Red,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = (screenWidth.value * 0.03f).sp,
                                            letterSpacing = 1.sp
                                        )
                                        Spacer(Modifier.width(5.dp))
                                    }
                                } else {
                                    Row(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFF6AEAC),
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                            .padding(2.dp)
                                    ) {
                                        Spacer(Modifier.width(5.dp))
                                        Text(
                                            "Only ${res.stock} left",
                                            color = Color.Red,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = (screenWidth.value * 0.03f).sp,
                                            letterSpacing = 1.sp
                                        )
                                        Spacer(Modifier.width(5.dp))
                                    }

                                }
                            }

                        }
                        Spacer(Modifier.height(15.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AccessTime,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                            Spacer(Modifier.width((screenWidth.value * 0.01).dp))
                            Text(
                                text = "Delivery in 2-3 days",
                                fontSize = (screenWidth.value * 0.04).sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray
                            )

                        }


                    }
                }

                Spacer(Modifier.height(10.dp))

            }

            Spacer(Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "User Name",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = name.value,
                            onValueChange = {
                                name.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Person,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter your name",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "Shop Name",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = shopname.value,
                            onValueChange = {
                                shopname.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Business,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter shop name",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }
                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "Address",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = address.value,
                            onValueChange = {
                                address.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.LocationOn,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter complete address",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))


                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "Phone Number",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = phoneNumber.value,
                            onValueChange = {
                                phoneNumber.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Phone,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter 10-digit phone number",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))

                }


                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.01f))
                        Text(
                            text = "Stock Needed",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = quantity.value,
                            onValueChange = {
                                quantity.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Phone,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter quantity",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.01f))
                        Text(
                            text = "Additional Message (Optional)",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = massage.value,
                            onValueChange = {
                                massage.value = it
                            },
                            placeholder = {
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Outlined.Message,
                                        contentDescription = null,
                                        modifier = Modifier.align(Alignment.Top)
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Any special instructions or notes ",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp,
                                        modifier = Modifier.align(Alignment.Top)
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(screenHeight * 0.1f)
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }
                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.025f))

                val totalPrice = res.price?.times(
                    quantity.value.toFloatOrNull() ?: 0f
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ElevatedButton(
                        onClick = {
                            if (name.value.isEmpty() || shopname.value.isEmpty() || address.value.isEmpty() || phoneNumber.value.isEmpty() || quantity.value.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Please fill all the fields",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@ElevatedButton
                            } else {
                                Log.d("TAG", "UserId: ${res.user_id}")
                                viewModel.addOrderDetails(
                                    product_id = res.product_id,
                                    product_name = res.product_name,
                                    user_id = res.user_id,
                                    shope_name = shopname.value,
                                    is_Approved = 0,
                                    user_name = name.value,
                                    massege = massage.value,
                                    quantity = quantity.value.toInt(),
                                    category = res.category,
                                    price = res.price?.toFloat(),
                                    total_price = totalPrice?.toFloat(),
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight * 0.065f)
                            .padding(horizontal = (screenWidth.value * 0.06).dp),
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        elevation = ButtonDefaults.buttonElevation(8.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(Modifier.width(10.dp))
                        Text("Place Order", fontSize = (screenWidth.value * 0.05).sp)

                    }
                }

                Spacer(Modifier.height((screenHeight * 0.02f)))
            }

        } else if (res.category == "Syrup") {
            Spacer(Modifier.height(screenHeight * 0.05f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenWidth * 0.04f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size((screenWidth * 0.08f))
                        .clickable {
                            navHostController.popBackStack()
                        }
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Place Order",
                    fontWeight = FontWeight.Bold,
                    fontSize = (screenWidth.value * 0.07).sp,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenWidth * 0.05f, vertical = screenHeight * 0.01f)
                    .clip(RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            )
            {
                Spacer(Modifier.height(screenHeight * 0.01f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.syruplogo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(screenWidth * 0.25f)
                            .clip(shape = RoundedCornerShape(16.dp))
                    )
                    Spacer(Modifier.width(screenWidth * 0.03f))

                    Column(
                        modifier = Modifier.weight(2f)
                    ) {

                        Text(
                            text = "${res.product_name}",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = (screenWidth.value * 0.05f).sp
                        )
                        Spacer(Modifier.height(10.dp))

                        Text(
                            "Category: ${res.category}",
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Normal,
                            fontSize = (screenWidth.value * 0.04f).sp
                        )
                        Spacer(Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()

                        ) {
                            Text(
                                text = " ₹ ${res.price}",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenWidth.value * 0.05f).sp
                            )
                            res.stock?.let {
                                if (it > 5) {
                                    Row(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFB1F5EC),
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .padding(4.dp)
                                    ) {
                                        Spacer(Modifier.width(5.dp))
                                        Text(
                                            "In Stock ",
                                            color = Color(0xFF038679),
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = (screenWidth.value * 0.035f).sp,
                                            letterSpacing = 1.sp
                                        )
                                        Spacer(Modifier.width(5.dp))
                                    }

                                } else if (it == 0) {
                                    Row(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFF6AEAC),
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                            .padding(2.dp)
                                    ) {
                                        Spacer(Modifier.width(5.dp))
                                        Text(
                                            "Out of Stock",
                                            color = Color.Red,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = (screenWidth.value * 0.03f).sp,
                                            letterSpacing = 1.sp
                                        )
                                        Spacer(Modifier.width(5.dp))
                                    }
                                } else {
                                    Row(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFF6AEAC),
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                            .padding(2.dp)
                                    ) {
                                        Spacer(Modifier.width(5.dp))
                                        Text(
                                            "Only ${res.stock} left",
                                            color = Color.Red,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = (screenWidth.value * 0.03f).sp,
                                            letterSpacing = 1.sp
                                        )
                                        Spacer(Modifier.width(5.dp))
                                    }

                                }
                            }

                        }
                        Spacer(Modifier.height(15.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AccessTime,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                            Spacer(Modifier.width((screenWidth.value * 0.01).dp))
                            Text(
                                text = "Delivery in 2-3 days",
                                fontSize = (screenWidth.value * 0.04).sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray
                            )

                        }


                    }
                }

                Spacer(Modifier.height(10.dp))

            }

            Spacer(Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "User Name",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = name.value,
                            onValueChange = {
                                name.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Person,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter your name",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "Shop Name",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = shopname.value,
                            onValueChange = {
                                shopname.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Business,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter shop name",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }
                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "Address",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = address.value,
                            onValueChange = {
                                address.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.LocationOn,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter complete address",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))


                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "Phone Number",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = phoneNumber.value,
                            onValueChange = {
                                phoneNumber.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Phone,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter 10-digit phone number",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))

                }


                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.01f))
                        Text(
                            text = "Stock Needed",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = quantity.value,
                            onValueChange = {
                                quantity.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Phone,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter quantity",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.01f))
                        Text(
                            text = "Additional Message (Optional)",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = massage.value,
                            onValueChange = {
                                massage.value = it
                            },
                            placeholder = {
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Outlined.Message,
                                        contentDescription = null,
                                        modifier = Modifier.align(Alignment.Top)
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Any special instructions or notes ",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp,
                                        modifier = Modifier.align(Alignment.Top)
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(screenHeight * 0.1f)
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }
                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.025f))

                val totalPrice = res.price?.times(
                    quantity.value.toFloatOrNull() ?: 0f
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ElevatedButton(
                        onClick = {
                            if (name.value.isEmpty() || shopname.value.isEmpty() || address.value.isEmpty() || phoneNumber.value.isEmpty() || quantity.value.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Please fill all the fields",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@ElevatedButton
                            } else {
                                Log.d("TAG", "UserId: ${res.user_id}")
                                viewModel.addOrderDetails(
                                    product_id = res.product_id,
                                    product_name = res.product_name,
                                    user_id = res.user_id,
                                    shope_name = shopname.value,
                                    is_Approved = 0,
                                    user_name = name.value,
                                    massege = massage.value,
                                    quantity = quantity.value.toInt(),
                                    category = res.category,
                                    price = res.price?.toFloat(),
                                    total_price = totalPrice?.toFloat(),
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight * 0.065f)
                            .padding(horizontal = (screenWidth.value * 0.06).dp),
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        elevation = ButtonDefaults.buttonElevation(8.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(Modifier.width(10.dp))
                        Text("Place Order", fontSize = (screenWidth.value * 0.05).sp)

                    }
                }

                Spacer(Modifier.height((screenHeight * 0.02f)))
            }

        } else if (res.category == "Antiseptic") {
            Spacer(Modifier.height(screenHeight * 0.05f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenWidth * 0.04f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size((screenWidth * 0.08f))
                        .clickable {
                            navHostController.popBackStack()
                        }
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Place Order",
                    fontWeight = FontWeight.Bold,
                    fontSize = (screenWidth.value * 0.07).sp,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenWidth * 0.05f, vertical = screenHeight * 0.01f)
                    .clip(RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            )
            {
                Spacer(Modifier.height(screenHeight * 0.01f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.antisepticlogo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(screenWidth * 0.28f)
                            .clip(shape = RoundedCornerShape(16.dp))
                        , contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.width(screenWidth * 0.03f))

                    Column(
                        modifier = Modifier.weight(2f)
                    ) {

                        Text(
                            text = "${res.product_name}",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = (screenWidth.value * 0.05f).sp
                        )
                        Spacer(Modifier.height(10.dp))

                        Text(
                            "Category: ${res.category}",
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Normal,
                            fontSize = (screenWidth.value * 0.04f).sp
                        )
                        Spacer(Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()

                        ) {
                            Text(
                                text = " ₹ ${res.price}",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenWidth.value * 0.05f).sp
                            )
                            res.stock?.let {
                                if (it > 5) {
                                    Row(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFB1F5EC),
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .padding(4.dp)
                                    ) {
                                        Spacer(Modifier.width(5.dp))
                                        Text(
                                            "In Stock ",
                                            color = Color(0xFF038679),
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = (screenWidth.value * 0.035f).sp,
                                            letterSpacing = 1.sp
                                        )
                                        Spacer(Modifier.width(5.dp))
                                    }

                                } else if (it == 0) {
                                    Row(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFF6AEAC),
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                            .padding(2.dp)
                                    ) {
                                        Spacer(Modifier.width(5.dp))
                                        Text(
                                            "Out of Stock",
                                            color = Color.Red,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = (screenWidth.value * 0.03f).sp,
                                            letterSpacing = 1.sp
                                        )
                                        Spacer(Modifier.width(5.dp))
                                    }
                                } else {
                                    Row(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFF6AEAC),
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                            .padding(2.dp)
                                    ) {
                                        Spacer(Modifier.width(5.dp))
                                        Text(
                                            "Only ${res.stock} left",
                                            color = Color.Red,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = (screenWidth.value * 0.03f).sp,
                                            letterSpacing = 1.sp
                                        )
                                        Spacer(Modifier.width(5.dp))
                                    }

                                }
                            }

                        }
                        Spacer(Modifier.height(15.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AccessTime,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                            Spacer(Modifier.width((screenWidth.value * 0.01).dp))
                            Text(
                                text = "Delivery in 2-3 days",
                                fontSize = (screenWidth.value * 0.04).sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray
                            )

                        }


                    }
                }

                Spacer(Modifier.height(10.dp))

            }

            Spacer(Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "User Name",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = name.value,
                            onValueChange = {
                                name.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Person,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter your name",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "Shop Name",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = shopname.value,
                            onValueChange = {
                                shopname.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Business,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter shop name",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }
                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "Address",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = address.value,
                            onValueChange = {
                                address.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.LocationOn,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter complete address",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))


                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "Phone Number",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = phoneNumber.value,
                            onValueChange = {
                                phoneNumber.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Phone,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter 10-digit phone number",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))

                }


                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.01f))
                        Text(
                            text = "Stock Needed",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = quantity.value,
                            onValueChange = {
                                quantity.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Phone,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter quantity",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.01f))
                        Text(
                            text = "Additional Message (Optional)",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = massage.value,
                            onValueChange = {
                                massage.value = it
                            },
                            placeholder = {
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Outlined.Message,
                                        contentDescription = null,
                                        modifier = Modifier.align(Alignment.Top)
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Any special instructions or notes ",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp,
                                        modifier = Modifier.align(Alignment.Top)
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(screenHeight * 0.1f)
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }
                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.025f))

                val totalPrice = res.price?.times(
                    quantity.value.toFloatOrNull() ?: 0f
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ElevatedButton(
                        onClick = {
                            if (name.value.isEmpty() || shopname.value.isEmpty() || address.value.isEmpty() || phoneNumber.value.isEmpty() || quantity.value.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Please fill all the fields",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@ElevatedButton
                            } else {
                                Log.d("TAG", "UserId: ${res.user_id}")
                                viewModel.addOrderDetails(
                                    product_id = res.product_id,
                                    product_name = res.product_name,
                                    user_id = res.user_id,
                                    shope_name = shopname.value,
                                    is_Approved = 0,
                                    user_name = name.value,
                                    massege = massage.value,
                                    quantity = quantity.value.toInt(),
                                    category = res.category,
                                    price = res.price?.toFloat(),
                                    total_price = totalPrice?.toFloat(),
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight * 0.065f)
                            .padding(horizontal = (screenWidth.value * 0.06).dp),
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        elevation = ButtonDefaults.buttonElevation(8.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(Modifier.width(10.dp))
                        Text("Place Order", fontSize = (screenWidth.value * 0.05).sp)

                    }
                }

                Spacer(Modifier.height((screenHeight * 0.02f)))
            }

        } else {
            Log.d("TAG", "AddOrderScreen: $res")
            Spacer(Modifier.height(screenHeight * 0.05f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenWidth * 0.04f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size((screenWidth * 0.08f))
                        .clickable {
                            navHostController.popBackStack()
                        }
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Place Order",
                    fontWeight = FontWeight.Bold,
                    fontSize = (screenWidth.value * 0.07).sp,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenWidth * 0.05f, vertical = screenHeight * 0.01f)
                    .clip(RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            )
            {
                Spacer(Modifier.height(screenHeight * 0.01f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.equipmenticon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(screenWidth * 0.25f)
                            .clip(shape = RoundedCornerShape(16.dp))
                    )
                    Spacer(Modifier.width(screenWidth * 0.03f))

                    Column(
                        modifier = Modifier.weight(2f)
                    ) {

                        Text(
                            text = "${res.product_name}",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = (screenWidth.value * 0.05f).sp
                        )
                        Spacer(Modifier.height(10.dp))

                        Text(
                            "Category: ${res.category}",
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Normal,
                            fontSize = (screenWidth.value * 0.04f).sp
                        )
                        Spacer(Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()

                        ) {
                            Text(
                                text = " ₹ ${res.price}",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenWidth.value * 0.05f).sp
                            )
                            res.stock?.let {
                                if (it > 5) {
                                    Row(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFB1F5EC),
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .padding(4.dp)
                                    ) {
                                        Spacer(Modifier.width(5.dp))
                                        Text(
                                            "In Stock ",
                                            color = Color(0xFF038679),
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = (screenWidth.value * 0.035f).sp,
                                            letterSpacing = 1.sp
                                        )
                                        Spacer(Modifier.width(5.dp))
                                    }

                                } else if (it == 0) {
                                    Row(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFF6AEAC),
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                            .padding(2.dp)
                                    ) {
                                        Spacer(Modifier.width(5.dp))
                                        Text(
                                            "Out of Stock",
                                            color = Color.Red,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = (screenWidth.value * 0.03f).sp,
                                            letterSpacing = 1.sp
                                        )
                                        Spacer(Modifier.width(5.dp))
                                    }
                                } else {
                                    Row(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFF6AEAC),
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                            .padding(2.dp)
                                    ) {
                                        Spacer(Modifier.width(5.dp))
                                        Text(
                                            "Only ${res.stock} left",
                                            color = Color.Red,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = (screenWidth.value * 0.03f).sp,
                                            letterSpacing = 1.sp
                                        )
                                        Spacer(Modifier.width(5.dp))
                                    }

                                }
                            }

                        }
                        Spacer(Modifier.height(15.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AccessTime,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                            Spacer(Modifier.width((screenWidth.value * 0.01).dp))
                            Text(
                                text = "Delivery in 2-3 days",
                                fontSize = (screenWidth.value * 0.04).sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray
                            )

                        }


                    }
                }

                Spacer(Modifier.height(10.dp))

            }

            Spacer(Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "User Name",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = name.value,
                            onValueChange = {
                                name.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Person,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter your name",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "Shop Name",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = shopname.value,
                            onValueChange = {
                                shopname.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Business,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter shop name",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }
                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "Address",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = address.value,
                            onValueChange = {
                                address.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.LocationOn,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter complete address",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))


                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.015f))
                        Text(
                            text = "Phone Number",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = phoneNumber.value,
                            onValueChange = {
                                phoneNumber.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Phone,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter 10-digit phone number",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))

                }


                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.01f))
                        Text(
                            text = "Stock Needed",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = quantity.value,
                            onValueChange = {
                                quantity.value = it
                            },
                            placeholder = {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Phone,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Enter quantity",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }

                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.02f))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidth * 0.05f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenWidth * 0.05f)
                    ) {
                        Spacer(Modifier.height(screenHeight * 0.01f))
                        Text(
                            text = "Additional Message (Optional)",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (screenWidth.value * 0.045).sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = massage.value,
                            onValueChange = {
                                massage.value = it
                            },
                            placeholder = {
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Outlined.Message,
                                        contentDescription = null,
                                        modifier = Modifier.align(Alignment.Top)
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "Any special instructions or notes ",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04).sp,
                                        modifier = Modifier.align(Alignment.Top)
                                    )
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(screenHeight * 0.1f)
                                .padding(horizontal = screenWidth * 0.01f),
                            shape = RoundedCornerShape(16.dp)
                        )

                    }
                    Spacer(Modifier.height(screenHeight * 0.01f))

                }

                Spacer(Modifier.height(screenHeight * 0.025f))

                val totalPrice = res.price?.times(
                    quantity.value.toFloatOrNull() ?: 0f
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ElevatedButton(
                        onClick = {
                            if (name.value.isEmpty() || shopname.value.isEmpty() || address.value.isEmpty() || phoneNumber.value.isEmpty() || quantity.value.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Please fill all the fields",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@ElevatedButton
                            } else {
                                Log.d("TAG", "UserId: ${res.user_id}")
                                viewModel.addOrderDetails(
                                    product_id = res.product_id,
                                    product_name = res.product_name,
                                    user_id = res.user_id,
                                    shope_name = shopname.value,
                                    is_Approved = 0,
                                    user_name = name.value,
                                    massege = massage.value,
                                    quantity = quantity.value.toInt(),
                                    category = res.category,
                                    price = res.price?.toFloat(),
                                    total_price = totalPrice?.toFloat(),
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight * 0.065f)
                            .padding(horizontal = (screenWidth.value * 0.06).dp),
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        elevation = ButtonDefaults.buttonElevation(8.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(Modifier.width(10.dp))
                        Text("Place Order", fontSize = (screenWidth.value * 0.05).sp)

                    }
                }

                Spacer(Modifier.height((screenHeight * 0.02f)))
            }
        }
    }
}