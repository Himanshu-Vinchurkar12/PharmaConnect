package com.example.medicalstoreapp.screen.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.medicalstoreapp.screen.AddOrderScreen
import com.example.medicalstoreapp.screen.ProfileSection.ContactUsScreen
import com.example.medicalstoreapp.screen.ProfileSection.EditScreen
import com.example.medicalstoreapp.screen.HomeScreen
import com.example.medicalstoreapp.screen.LogInReadyScreen
import com.example.medicalstoreapp.screen.LogIn_SignUP_Screen.LogInScreen
import com.example.medicalstoreapp.screen.LogIn_SignUP_Screen.SignUpScreen
import com.example.medicalstoreapp.screen.ProfileSection.PrivacySecurityScreen
import com.example.medicalstoreapp.screen.ProfileSection.ProfileScreen
import com.example.medicalstoreapp.screen.StartScreen
import com.example.medicalstoreapp.screen.TabScreenForProduct.AntisepticScreens
import com.example.medicalstoreapp.screen.TabScreenForProduct.EquipmentScreen
import com.example.medicalstoreapp.screen.ProfileSection.HelpcenterScreen
import com.example.medicalstoreapp.screen.ProfileSection.OrderHistoryScreen
import com.example.medicalstoreapp.screen.ProfileSection.PaymentScreen
import com.example.medicalstoreapp.screen.TabScreenForProduct.SyrupsScreens
import com.example.medicalstoreapp.screen.TabScreenForProduct.TabScreenForProduct
import com.example.medicalstoreapp.screen.TabScreenForProduct.TabletsScreens
import com.example.medicalstoreapp.screen.WaitingScreen
import com.example.medicalstoreapp.viewModel.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun App(viewModel: MyViewModel = hiltViewModel()) {
    val navController = rememberNavController()


    val coroutineScope = rememberCoroutineScope()
    val userId = viewModel.userIdByPref.collectAsState()

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            viewModel.getUserIdByPref()
        }
    }


    val actualStartScreen = remember { mutableStateOf<Routes?>(null) }

    LaunchedEffect(userId.value) {
        delay(200)
        actualStartScreen.value = if (userId.value.isNullOrEmpty()) {
            Routes.StartScreenRoutes
        } else {
            Routes.LogInReadyScreenRoutes
        }
    }


    if (actualStartScreen.value == null) {
        // Optional: show splash or loading while delay is in progress
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        NavHost(navController = navController, startDestination = actualStartScreen.value!!) {

            composable<Routes.SignUpScreenRoutes> {
                SignUpScreen(navController = navController)
            }

            composable<Routes.WaitingScreenRoutes> {
                WaitingScreen(navController = navController)
            }

            composable<Routes.LogInScreenRoutes> {
                LogInScreen(navController = navController)
            }

            composable<Routes.StartScreenRoutes> {
                StartScreen(navController = navController)
            }

            composable<Routes.HomeScreenRoutes> {
                val data = it.toRoute<Routes.HomeScreenRoutes>()
                HomeScreen(user_id = data.user_id, navController = navController)
            }

            composable<Routes.LogInReadyScreenRoutes> {
                LogInReadyScreen(navController = navController)
            }

            composable<Routes.ProfileScreenRoutes> {
                val data = it.toRoute<Routes.ProfileScreenRoutes>()
                ProfileScreen(
                    navHostController = navController,
                    userId = data.user_id.toString()
                )
            }

            composable<Routes.EditScreenRoutes> {
                val data = it.toRoute<Routes.EditScreenRoutes>()
                EditScreen(
                    navHostController = navController,
                    userId = data.user_id.toString()
                )
            }

            composable<Routes.OrderHistoryScreenRoutes>{
                val data = it.toRoute<Routes.OrderHistoryScreenRoutes>()
                OrderHistoryScreen(
                    navHostController = navController,
                    userId = data.user_id.toString()
                )
            }

            composable<Routes.PaymentScreenRoutes>{
                val data = it.toRoute<Routes.PaymentScreenRoutes>()
                PaymentScreen(
                    navHostController = navController,
                    userId = data.user_id.toString()
                )
            }


            composable<Routes.HelpcenterScreenRoutes> {
                val data = it.toRoute<Routes.HelpcenterScreenRoutes>()
                HelpcenterScreen(
                    navHostController = navController,
                    userId = data.user_id.toString()
                )
            }

            composable<Routes.ContactUsScreenRoutes> {
                val data = it.toRoute<Routes.ContactUsScreenRoutes>()
                ContactUsScreen(
                    navHostController = navController,
                    userId = data.user_id.toString()
                )
            }

            composable<Routes.PrivacySecurityScreenRoutes> {
                val data = it.toRoute<Routes.PrivacySecurityScreenRoutes>()
                PrivacySecurityScreen(
                    navHostController = navController,
                    userId = data.user_id.toString()
                )
            }


            composable<Routes.AntisepticScreensRoutes> {

                val data = it.toRoute<Routes.TabletsScreensRoutes>()
                AntisepticScreens(
                    navHostController = navController,
                    userId = data.user_id.toString()
                )
            }

            composable<Routes.SyrupsScreensRoutes> {
                val data = it.toRoute<Routes.TabletsScreensRoutes>()
                SyrupsScreens(
                    navHostController = navController,
                    userId = data.user_id.toString()
                )

            }
            composable<Routes.TabletsScreensRoutes> {
                val data = it.toRoute<Routes.TabletsScreensRoutes>()
                TabletsScreens(
                    navHostController = navController,
                    userId = data.user_id.toString()
                )
            }

            composable<Routes.TabScreenForProduct> {
                val data = it.toRoute<Routes.TabScreenForProduct>()
                TabScreenForProduct(
                    navHostController = navController,
                    userId = data.user_id.toString()
                )
            }

            composable<Routes.EquipmentScreenRoutes> {
                val data = it.toRoute<Routes.EquipmentScreenRoutes>()
                EquipmentScreen(
                    navHostController = navController,
                    userId = data.user_id.toString()
                )
            }

            composable<Routes.AddOrderScreenRoutes> {
                val data = it.toRoute<Routes.AddOrderScreenRoutes>()
                AddOrderScreen(
                    res = data,
                    navHostController = navController
                )
            }


        }
    }







}