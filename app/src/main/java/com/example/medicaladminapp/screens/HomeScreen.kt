package com.example.medicaladminapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medicaladminapp.screens.nav.Routes
import com.example.medicaladminapp.viewModel.MyViewModels

@Composable
fun HomeScreen(
    viewModels: MyViewModels = hiltViewModel(),
    navHostController: NavHostController
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val getAllOrders = viewModels.getAllOrderDetails.collectAsState()
    val getAllProducts = viewModels.getAllproducts.collectAsState()
    val getAllUsers = viewModels.getAllUsers.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModels.getAllproducts()
        viewModels.getAllOrderDetails()
        viewModels.getAllUsers()
    }

    var TotalProducts = getAllProducts.value.isdata
    val TotalOrder = getAllOrders.value.isdata
    val TotalUsers = getAllUsers.value.isdata

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.White, Color(0xFF84D7D2))
                )
            )
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Dashboard header section with full white background
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Dashboard",
                fontSize = (screenWidth.value * 0.08).sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

        Column(modifier = Modifier.padding(10.dp)) {
            Spacer(Modifier.height(10.dp))

            Text(
                text = "Overview",
                fontSize = (screenWidth.value * 0.06).sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(10.dp))
            StatsGrid(
                screenWidth = screenWidth,
                productsCount = TotalProducts?.size ?: 0,
                ordersCount = TotalOrder?.size ?: 0,
                usersCount = TotalUsers?.size ?: 0,
                approvedUsersCount = TotalUsers?.count { it.is_Approved == 1 } ?: 0
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Quick Actions",
                fontSize = (screenWidth.value * 0.07).sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(10.dp))
            QuickActions(navHostController, screenWidth)
        }
    }
}

@Composable
fun StatsGrid(
    screenWidth: Dp,
    productsCount: Int,
    ordersCount: Int,
    usersCount: Int,
    approvedUsersCount: Int
) {
    val items = listOf(
        Triple("Products", "$productsCount", Icons.Outlined.Inventory),
        Triple("Orders", "$ordersCount", Icons.Outlined.ShoppingCart),
        Triple("Users", "$usersCount", Icons.Outlined.Person),
        Triple("Approved User", "$approvedUsersCount", Icons.Outlined.HowToReg)
    )

    val showTotalLabel = listOf(true, true, true, false) // ✅ Only hide "Total" for last card

    val colors = listOf(
        Color(0xFF4053D3),
        Color(0xFFEA592E),
        Color(0xFF107E71),
        Color(0xFF107E71)
    )

    for (i in items.indices step 2) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                StatCard(
                    title = items[i].first,
                    value = items[i].second,
                    icon = items[i].third,
                    bgColor = colors[i],
                    screenWidth = screenWidth,
                    showTotalLabel = showTotalLabel[i]
                )
            }
            if (i + 1 < items.size) {
                Box(modifier = Modifier.weight(1f)) {
                    StatCard(
                        title = items[i + 1].first,
                        value = items[i + 1].second,
                        icon = items[i + 1].third,
                        bgColor = colors[i + 1],
                        screenWidth = screenWidth,
                        showTotalLabel = showTotalLabel[i + 1]
                    )
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: ImageVector,
    bgColor: Color,
    screenWidth: Dp,
    showTotalLabel: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(screenWidth * 0.12f)
                    .clip(CircleShape)
                    .background(bgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(screenWidth * 0.06f)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                if (showTotalLabel) {
                    Text("Total", fontSize = 14.sp, color = Color.Gray)
                }
                Text(title, fontSize = 14.sp, color = Color.Gray)
                Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun QuickActions(navHostController: NavHostController, screenWidth: Dp) {
    val actions = listOf(
        Triple("View Products", Icons.Outlined.Inventory, Routes.TabScreenForProduct),
        Triple("Manage Users", Icons.Outlined.Person, Routes.TabScreenRoutes),
        Triple("Add Product", Icons.Outlined.Add, Routes.AddProductScreenRoutes),
        Triple("View Orders", Icons.Outlined.ShoppingCart, Routes.TabScreenForOrder)
    )

    for (i in actions.indices step 2) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                QuickActionCard(actions[i], screenWidth, navHostController)
            }
            if (i + 1 < actions.size) {
                Box(modifier = Modifier.weight(1f)) {
                    QuickActionCard(actions[i + 1], screenWidth, navHostController)
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
fun QuickActionCard(action: Triple<String, ImageVector, Routes>, screenWidth: Dp, navHostController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { navHostController.navigate(action.third) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = action.second,
                contentDescription = null,
                tint = Color(0xFF00897B),
                modifier = Modifier.size(screenWidth * 0.08f)
            )
            Spacer(Modifier.height(8.dp))
            Text(action.first, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}