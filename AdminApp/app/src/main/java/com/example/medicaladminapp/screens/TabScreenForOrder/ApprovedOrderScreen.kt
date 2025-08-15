package com.example.medicaladminapp.screens.TabScreenForOrder

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medicaladminapp.R
import com.example.medicaladminapp.models.UsersModelsItem
import com.example.medicaladminapp.models.getAllOrderResponse
import com.example.medicaladminapp.models.getAllProductsResponse
import com.example.medicaladminapp.screens.TabScreenForUsers.AllapprovedUser
import com.example.medicaladminapp.screens.nav.Routes
import com.example.medicaladminapp.viewModel.MyViewModels
import kotlin.String
import kotlin.collections.filter

@Composable
fun ApprovedOrderScreen(
    viewModels: MyViewModels = hiltViewModel(),
    navHostController: NavHostController
) {
    val getAllOrders = viewModels.getAllOrderDetails.collectAsState()
    var text = remember { mutableStateOf("") }
    var isSortByName = remember { mutableStateOf(true) }
    val dropDown = remember { mutableStateOf(false) }

    var context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModels.getAllOrderDetails()
    }

    when {
        getAllOrders.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        getAllOrders.value.isError != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = getAllOrders.value.isError.toString())
            }
        }

        getAllOrders.value.isdata != null -> {

        }
    }

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
        Spacer(Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            OutlinedTextField(
                value = text.value,
                onValueChange = { text.value = it },
                placeholder = { Text(text = "Search Order..") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.List,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                dropDown.value = true
                            }
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.DarkGray,
                    unfocusedBorderColor = Color.LightGray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )
        }

        // Box to Wrap  DropdownMenu for proper position
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            contentAlignment = Alignment.TopStart
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .wrapContentWidth()
            ) {
                // DropdownMenu Positioned Properly
                AnimatedVisibility(
                    visible = dropDown.value,
                    enter = scaleIn(tween(300)) + slideInVertically(
                        initialOffsetY = { -40 },
                        animationSpec = tween(300)
                    ),
                    exit = scaleOut(tween(300)) + slideOutVertically(
                        targetOffsetY = { -40 },
                        animationSpec = tween(300)
                    )
                )
                {
                    // DropdownMenu Beside Icon
                    DropdownMenu(
                        expanded = dropDown.value,
                        onDismissRequest = { dropDown.value = false },
                        modifier = Modifier
                            .width(150.dp)
                            .background(Color.White, shape = RoundedCornerShape(12.dp))
                            .padding(0.dp) // No extra padding for Dropdown
                    ) {

                        Column(modifier = Modifier.padding(vertical = 4.dp))
                        {
                            // Sort by Name
                            DropdownMenuItem(
                                text = { Text("Sort by Name") },
                                onClick = {
                                    dropDown.value = false
                                    isSortByName.value = true
                                    Toast.makeText(
                                        context,
                                        "Sorted by Name",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                },
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )

                            // Divider for separation
                            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

                            // Sort by Date
                            DropdownMenuItem(
                                text = { Text("Sort by Date") },
                                onClick = {
                                    dropDown.value = false
                                    isSortByName.value = false
                                    Toast.makeText(
                                        context,
                                        "Sorted by Date",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                },
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }
                }
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        val getApproveOrder = getAllOrders.value.isdata

        if (getApproveOrder != null) {
            val filteredUser = getApproveOrder.filter {
                it.shope_name.contains(text.value.trim(), ignoreCase = true)
            }.let { list ->
                if (isSortByName.value) list.sortedBy { it.shope_name }
                else list.sortedBy { it.date_of_order_creation }
            }

            if (filteredUser.isNotEmpty()) {
                Text(
                    if (isSortByName.value) "Sorted by Customers" else "Sorted by Date",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                )

            }
            if (filteredUser.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.no_contact_found),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(160.dp)
                            .clip(shape = CircleShape)
                            .shadow(elevation = 2.dp)
                    )
                    Spacer(Modifier.height(25.dp))
                    Text(
                        text = "No order Found",
                        color = Color.Gray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            } else {
                AnimatedContent(
                    targetState = filteredUser,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(300)) + slideInVertically(
                            initialOffsetY = { it }) togetherWith
                                fadeOut(animationSpec = tween(300)) + slideOutVertically(
                            targetOffsetY = { it })
                    },
                    label = "UserListAnimation"
                )
                { targetList ->
                    LazyColumn {
                        items(targetList) {
                            AllApprovedOrder(
                                res = it,
                                navHostController = navHostController,
                                viewModels = viewModels
                            )
                        }

                        item {
                            Spacer(Modifier.height(100.dp))
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun AllApprovedOrder(
    res: getAllOrderResponse,
    navHostController: NavHostController,
    viewModels: MyViewModels
) {
    val context = LocalContext.current
    if (res.is_Approved.toInt() == 1) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable {
                    navHostController.navigate(
                        Routes.DetailScreenForOrderRoutes(
                            category = res.category,
                            date_of_order_creation = res.date_of_order_creation,
                            id = res.id,
                            is_Approved = res.is_Approved,
                            massege = res.massege,
                            order_id = res.order_id,
                            price = res.price,
                            product_id = res.product_id,
                            product_name = res.product_name,
                            quantity = res.quantity,
                            shope_name = res.shope_name,
                            total_price = res.total_price,
                            user_id = res.user_id,
                            user_name = res.user_name
                        )
                    )
                },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
                hoveredElevation = 10.dp,
                focusedElevation = 10.dp
            ),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
        ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                // Top row with product name and status + delete icon
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = if ((res.product_name.length) > 15) res.product_name.take(15) + "..." else res.product_name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Text(
                            text = "Approved",
                            color = Color.Green,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    Toast
                                        .makeText(
                                            context,
                                            "${res.shope_name}'s Order Deleted Successfully",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                    viewModels.deleteSpecificOrder(
                                        orderId = res.order_id.toString()
                                    )
                                }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                // Shop Name
                Text(
                    text = "Shop : ${res.shope_name}",
                    fontSize = 18.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Quantity
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "Quantity:",
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                    Text(
                        text = "${res.quantity} units",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )

                }

                Spacer(modifier = Modifier.height(6.dp))

                // Total Price
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "Total Price :",
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                    Text(
                        text = "₹ ${res.total_price}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))

                // Order Date
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Order Date:",
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.align(Alignment.CenterStart)

                    )
                    Text(
                        text = res.date_of_order_creation,
                        fontSize = 15.sp,
                        modifier = Modifier.align(Alignment.CenterEnd)

                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                HorizontalDivider(color = Color.LightGray, thickness = 0.8.dp)

                // Order ID
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Order ID:",
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                    Text(
                        text = res.order_id.take(10) + "...",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }

            }
        }
    }
}


