package com.example.medicaladminapp.screens.nav

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorDirection
import com.example.bottombar.model.IndicatorStyle
import com.example.medicaladminapp.screens.AddProductScreen
import com.example.medicaladminapp.screens.HomeScreen
import com.example.medicaladminapp.screens.TabScreenForOrder.ApprovedOrderScreen
import com.example.medicaladminapp.screens.TabScreenForOrder.DetailScreenForOrder
import com.example.medicaladminapp.screens.TabScreenForOrder.PendingOrderScreen
import com.example.medicaladminapp.screens.TabScreenForOrder.TabScreenForOrder
import com.example.medicaladminapp.screens.TabScreenForProduct.AntisepticScreens
import com.example.medicaladminapp.screens.TabScreenForProduct.EquipmentsScreens
import com.example.medicaladminapp.screens.TabScreenForProduct.SyrupsScreens
import com.example.medicaladminapp.screens.TabScreenForProduct.TabScreenForProduct
import com.example.medicaladminapp.screens.TabScreenForProduct.TabletsScreens
import com.example.medicaladminapp.screens.TabScreenForUsers.ApprovedUserScreen
import com.example.medicaladminapp.screens.TabScreenForUsers.DetailScreen
import com.example.medicaladminapp.screens.TabScreenForUsers.PendingUserScreen
import com.example.medicaladminapp.screens.TabScreenForUsers.TabScreen
import com.example.medicaladminapp.screens.UpdateScreen
import com.example.medicaladminapp.screens.nav.Routes.HomeScreenRoutes
import com.example.medicaladminapp.screens.nav.Routes.TabScreenRoutes
import com.example.medicaladminapp.viewModel.MyViewModels


@Composable
fun App(viewModels: MyViewModels = hiltViewModel()) {


    val navHostController = rememberNavController()

    val selectedItem = remember { mutableIntStateOf(0) }

    val showBottomBar = remember { mutableStateOf(false) }

//    val navBackStackEntry = navHostController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry.value
//
//    LaunchedEffect(currentRoute) {
//        selectedItem.intValue = when (currentRoute) {
//            TabScreenRoutes -> 0
//            Routes.TabScreenForProduct -> 1
//            Routes.AddProductScreenRoutes -> 2
//            Routes.HomeScreenRoutes -> 3
//            else -> selectedItem.intValue
//        }
//    }

    val isDarkTheme = isSystemInDarkTheme()
    val iconAndTextColor = if (isDarkTheme) Color.White else Color.Black
    val backgroundColor = if (isDarkTheme) Color.DarkGray else Color.White

    val bottomNavItem = listOf(

        BottomItem(
            title = "Home",
            icon = Icons.Outlined.Home,
            filledicon = Icons.Filled.Home
        ),
        BottomItem(
            title = "Product",
            icon = Icons.AutoMirrored.Outlined.List,
            filledicon = Icons.AutoMirrored.Filled.List
        ),
        BottomItem(
            title = "Add",
            icon = Icons.Outlined.Add,
            filledicon = Icons.Filled.Add
        ),
        BottomItem(
            title = "User",
            icon = Icons.Outlined.PersonAdd,
            filledicon = Icons.Filled.PersonAdd,
        ),
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {

            if (showBottomBar.value == true) {
                AnimatedBottomBar(
                    selectedItem = selectedItem.intValue,
                    itemSize = bottomNavItem.size,
                    containerColor = backgroundColor,
                    indicatorStyle = IndicatorStyle.FILLED,
                    indicatorColor = Color(0xFF39C5B8),
                    indicatorDirection = IndicatorDirection.BOTTOM
                ) {
                    bottomNavItem.forEachIndexed { index, items ->

                        BottomBarItem(
                            selected = selectedItem.intValue == index,
                            imageVector = items.filledicon,
                            label = items.title,
                            containerColor = Color.Transparent,
                            iconColor = iconAndTextColor,
                            textColor = iconAndTextColor,
                            onClick = {
                                selectedItem.intValue = index

                                when (index) {
                                    0 -> {
                                        navHostController.navigate(Routes.HomeScreenRoutes)
                                        {
                                            popUpTo(HomeScreenRoutes) {
                                                inclusive = true
                                            }
                                        }
                                    }

                                    1 -> {
                                        navHostController.navigate(Routes.TabScreenForProduct)
                                        {
                                            popUpTo(HomeScreenRoutes) {
                                                inclusive = true
                                            }
                                        }
                                    }

                                    2 -> {
                                        navHostController.navigate(Routes.AddProductScreenRoutes)
                                        {
                                            popUpTo(HomeScreenRoutes) {
                                                inclusive = true
                                            }

                                        }
                                    }

                                    3 -> {
                                        navHostController.navigate(Routes.TabScreenRoutes)
                                        {
                                            popUpTo(HomeScreenRoutes) {
                                                inclusive = true
                                            }
                                        }
                                    }


                                }
                            }

                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController = navHostController , startDestination = Routes.HomeScreenRoutes){


            composable<Routes.HomeScreenRoutes>{
                showBottomBar.value = true
                HomeScreen(
                    navHostController = navHostController
                )
            }


            composable<Routes.DetailScreenRoutes>{ backStack ->
                showBottomBar.value = false
                val data : Routes.DetailScreenRoutes = backStack.toRoute()
                DetailScreen(
                    res = data,
                    navHostController = navHostController
                )
            }

            composable<Routes.DetailScreenForOrderRoutes> { backStack ->
                showBottomBar.value = false
                val data: Routes.DetailScreenForOrderRoutes = backStack.toRoute()
                DetailScreenForOrder(
                    res = data,
                    navHostController = navHostController
                )
            }

            composable<Routes.UpdateScreenRoutes>{ backStack ->
                showBottomBar.value = false
                val data : Routes.UpdateScreenRoutes= backStack.toRoute()
                UpdateScreen(
                    res = data,
                    navHostController = navHostController
                )

            }

            composable<Routes.ApprovedUserScreenRoutes>{
                showBottomBar.value = true
                ApprovedUserScreen(
                    navHostController = navHostController
                )
            }

            composable<Routes.PendingUserScreenRoutes>{
                showBottomBar.value = true
                PendingUserScreen(
                    navHostController = navHostController
                )
            }

            composable<TabScreenRoutes>{
                showBottomBar.value = true
                TabScreen(navHostController = navHostController)
            }

            composable<Routes.TabScreenForProduct>{
                showBottomBar.value = true
                TabScreenForProduct(navHostController = navHostController)
            }

            composable<Routes.AddProductScreenRoutes>{
                AddProductScreen(navHostController = navHostController)
            }

            composable<Routes.TabletsScreensRoutes>{
                TabletsScreens(navHostController = navHostController)
            }

            composable<Routes.SyrupsScreensRoutes>{
                SyrupsScreens(navHostController = navHostController)
            }

            composable<Routes.AntisepticScreensRoutes>{
                AntisepticScreens(navHostController = navHostController)
            }

            composable<Routes.EquipmentsScreensRoutes>{
                EquipmentsScreens(navHostController = navHostController)
            }

            composable<Routes.TabScreenForOrder>{
                showBottomBar.value = false
                TabScreenForOrder( navHostController = navHostController)
            }
            composable<Routes.ApprovedOrderScreenRoutes>{
                ApprovedOrderScreen(navHostController = navHostController)
            }

            composable<Routes.PendingOrderScreenRoutes>{
                PendingOrderScreen(navHostController = navHostController)
            }



        }
    }

}


data class BottomItem(
    val title: String,
    val icon: ImageVector,
    val filledicon: ImageVector
)
