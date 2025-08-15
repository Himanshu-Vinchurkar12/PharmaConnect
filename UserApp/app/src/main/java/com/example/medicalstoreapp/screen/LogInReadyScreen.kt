package com.example.medicalstoreapp.screen

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalstoreapp.screen.nav.Routes
import com.example.medicalstoreapp.viewModel.MyViewModel


@Composable
fun LogInReadyScreen(
    navController: NavController,
    viewModel: MyViewModel = hiltViewModel()
) {

    val userId = viewModel.userIdByPref.collectAsState()
    val user = viewModel.getSpecificUsers.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getUserIdByPref()
    }


    LaunchedEffect(userId.value) {
        userId.value?.let {
            viewModel.getSpecificUsers(it)
            Log.d("TAG", "LogInReadyScreen: ${userId.value}")
        }
    }


    when {
        user.value.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        user.value.isError != null -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = user.value.isError.toString())
                Log.d("TAG", "LogInReadyScreen: ${user.value.isError}")
            }
        }

        user.value.isData != null -> {
            val userData = user.value.isData
            if (userData?.any { it.is_Approved == 1 } == true) {
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.HomeScreenRoutes(user_id = userId.value.toString())) {
                        popUpTo(0) { inclusive = true } // clears backstack
                    }
                }
            } else {
                // Show not approved screen
                NotApprovedUI {
                    navController.navigate(Routes.LogInScreenRoutes) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }

        }
    }


}

@Composable
fun NotApprovedUI(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colors = listOf(Color.White, Color(0xFF77EADE)))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.Warning,
            contentDescription = "Not Approved",
            tint = Color.Red,
            modifier = Modifier.size(124.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Sorry for your inconvenience", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text(
            "You are not approved right now.",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            "Please wait for your approval by Admin.",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Back",
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { onBackClick() }
        )
    }
}