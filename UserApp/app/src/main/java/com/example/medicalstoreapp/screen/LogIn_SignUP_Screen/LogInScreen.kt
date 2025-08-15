package com.example.medicalstoreapp.screen.LogIn_SignUP_Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalstoreapp.screen.nav.Routes
import com.example.medicalstoreapp.viewModel.MyViewModel

@Composable
fun LogInScreen(
    navController: NavController,
    viewModel: MyViewModel = hiltViewModel()
) {
    val state1 = viewModel.login.collectAsState()
    val context = LocalContext.current

    val password = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    // Handle login state changes
    LaunchedEffect(state1.value) {
        state1.value?.let { state ->
            if (state.isLoading) {
                // Show loading, handled below
            } else if (state.isError != null) {
                Toast.makeText(context, state.isError, Toast.LENGTH_SHORT).show()
            } else if (state.isData != null) {
                if (state.isData?.userId != null) {
                    Toast.makeText(context,"LogIn Successfully", Toast.LENGTH_SHORT).show()
                    navController.navigate(Routes.LogInReadyScreenRoutes)
                } else {
                    Toast.makeText(context, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.White, Color(0xFF77EADE)),
                )
            )
    ) {
        // Back Button
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(start = 25.dp, top = 60.dp)
                .size(35.dp)
                .clickable {
                    navController.navigate(Routes.StartScreenRoutes)
                }
        )

        // Welcome Text
        Text(
            text = "Welcome",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(start = 30.dp, top = 60.dp)
        )
        Text(
            text = "Back",
            modifier = Modifier.padding(start = 30.dp, top = 3.dp),
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
        )

        Spacer(Modifier.height(30.dp))

        // Login Form
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Email Input
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = {
                    Row {
                        Icon(imageVector = Icons.Rounded.Email, contentDescription = null)
                        Spacer(Modifier.width(5.dp))
                        Text(text = "Email", fontWeight = FontWeight.Bold)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(Modifier.height(20.dp))

            // Password Input
            var passwordVisible by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Rounded.Lock, contentDescription = null)
                        Spacer(Modifier.width(5.dp))
                        Text(text = "Password", fontWeight = FontWeight.Bold)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(0.8f),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = description)
                    }
                }
            )
            Spacer(Modifier.height(35.dp))

            // Login Button
            ElevatedButton(
                onClick = {
                    if (email.value.isEmpty() || password.value.isEmpty()) {
                        Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        viewModel.login(
                            email = email.value,
                            password = password.value
                        )
                    }
                },
                modifier = Modifier.size(width = 170.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(Color.Red),
                elevation = ButtonDefaults.buttonElevation(8.dp),
            ) {
                Text("LogIn", fontSize = 18.sp)
                Spacer(Modifier.width(5.dp))
                Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
            }

            Spacer(Modifier.height(10.dp))

            // Sign Up Option
            Text(
                text = "Sign Up",
                fontSize = 20.sp,
                color = Color.Blue,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    navController.navigate(Routes.SignUpScreenRoutes)
                }
            )
        }
    }

    // Show loading indicator if necessary
    if (state1.value?.isLoading == true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x88000000)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
