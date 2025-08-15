package com.example.medicalstoreapp.screen.ProfileSection

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medicalstoreapp.screen.nav.Routes
import com.example.medicalstoreapp.viewModel.MyViewModel

@Composable
fun PrivacySecurityScreen(
    viewModel: MyViewModel = hiltViewModel(),
    navHostController: NavHostController,
    userId: String
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var password = remember { mutableStateOf("") }
    var confirmnewpassword = remember { mutableStateOf("") }


    LaunchedEffect(key1 = Unit) {
        viewModel.getSpecificUsers(user_id = userId)
    }

    val getSpecificUsers = viewModel.getSpecificUsers.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            getSpecificUsers.value.isLoading -> {
                CircularProgressIndicator()
            }

            getSpecificUsers.value.isError != null -> {
                Log.d("TAG", "GetUser: ${getSpecificUsers.value.isError}")
                Toast.makeText(
                    context,
                    getSpecificUsers.value.isError.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }

            getSpecificUsers.value.isData != null -> {
                val user = getSpecificUsers.value.isData

                val scrollState = rememberScrollState()


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color.White, Color(0xFF77EADE))
                            )
                        )
                        .verticalScroll(scrollState)
                        .padding(horizontal = screenWidth * 0.04f)
                ) {
                    Spacer(modifier = Modifier.height(screenHeight * 0.05f))

                    if (user.isNullOrEmpty()) {
                        Text(text = "No user data available", color = Color.Red)
                    }
                    else {

                        for (i in user) {
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
                                            navHostController.navigate(
                                                Routes.ProfileScreenRoutes(
                                                    user_id = userId
                                                )
                                            )
                                        }
                                )
                                Spacer(modifier = Modifier.width(16.dp))

                                Text(
                                    text = "Privacy & Security",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = (screenWidth.value * 0.07).sp,
                                    color = Color.Black
                                )
                            }

                            Spacer(modifier = Modifier.height(screenHeight * 0.035f))

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = screenWidth * 0.04f),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                                colors = CardDefaults.cardColors(Color.White)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            horizontal = screenWidth * 0.05f,
                                            vertical = screenHeight * 0.02f
                                        ),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(screenWidth * 0.23f)
                                            .background(
                                                color = Color(0xFF0E796B),
                                                shape = CircleShape
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Security,
                                            contentDescription = null,
                                            modifier = Modifier.size(screenWidth * 0.07f),
                                            tint = Color.White
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(screenWidth * 0.04f))

                                    Text(
                                        text = "We understand your priorities. That's why your data is secure and cannot be altered by anyone.",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = (screenWidth.value * 0.04f).sp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(screenWidth * 0.03f))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Lock,
                                            contentDescription = null,
                                            modifier = Modifier.size(screenWidth * 0.05f),
                                            tint = Color(0xFF057367)
                                        )
                                        Spacer(modifier = Modifier.width(screenWidth * 0.04f))
                                        Text(
                                            text = "End-to-End Encrypted",
                                            fontWeight = FontWeight.Medium,
                                            fontSize = (screenWidth.value * 0.04f).sp,
                                            color = Color(0xFF057367)
                                        )
                                    }
                                }
                            }


                            Spacer(modifier = Modifier.height(screenHeight * 0.035f))

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = screenWidth * 0.04f),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                                colors = CardDefaults.cardColors(Color.White)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            horizontal = screenWidth * 0.05f,
                                            vertical = screenHeight * 0.02f
                                        ),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Spacer(Modifier.height(screenHeight * 0.01f))
                                    Text(
                                        text = "Change Password",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = (screenWidth.value * 0.05f).sp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))
                                    Text(
                                        text = "New Password",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = (screenWidth.value * 0.045f).sp,
                                        color = Color.Black
                                    )

                                    Spacer(Modifier.height(screenHeight * 0.01f))

                                    var passwordVisible by remember { mutableStateOf(false) }

                                    OutlinedTextField(
                                        value = password.value,
                                        onValueChange = { password.value = it },
                                        placeholder = {
                                            Text(
                                                text = "Enter new password",
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                        singleLine = true,
                                        shape = RoundedCornerShape(20.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = screenWidth * 0.02f),
                                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                        trailingIcon = {
                                            val icon =
                                                if (passwordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                                            val description =
                                                if (passwordVisible) "Hide password" else "Show password"
                                            IconButton(onClick = {
                                                passwordVisible = !passwordVisible
                                            }) {
                                                Icon(
                                                    imageVector = icon,
                                                    contentDescription = description
                                                )
                                            }
                                        }
                                    )



                                    Spacer(Modifier.height(screenHeight * 0.015f))

                                    Text(
                                        text = " Confirm New Password",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = (screenWidth.value * 0.045f).sp,
                                        color = Color.Black
                                    )

                                    Spacer(Modifier.height(screenHeight * 0.01f))

                                    OutlinedTextField(
                                        value = confirmnewpassword.value,
                                        onValueChange = { confirmnewpassword.value = it },
                                        placeholder = {
                                            Text(
                                                text = " Confirm new password",
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                        singleLine = true,
                                        shape = RoundedCornerShape(20.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = screenWidth * 0.02f),
                                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                        trailingIcon = {
                                            val icon =
                                                if (passwordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                                            val description =
                                                if (passwordVisible) "Hide password" else "Show password"
                                            IconButton(onClick = {
                                                passwordVisible = !passwordVisible
                                            }) {
                                                Icon(
                                                    imageVector = icon,
                                                    contentDescription = description
                                                )
                                            }
                                        }
                                    )

                                    Spacer(Modifier.height(screenHeight * 0.02f))

                                    Button(
                                        onClick = {
                                            if (password.value == confirmnewpassword.value) {
                                                viewModel.updateUserDetails(
                                                    name = i.name,
                                                    password = password.value,
                                                    email = i.email,
                                                    address = i.address,
                                                    phone_number = i.phone_number,
                                                    pin_code = i.pin_code,
                                                    shope_name = i.shope_name,
                                                    userId = i.user_id,
                                                    is_Approved = i.is_Approved
                                                )
                                                Toast.makeText(
                                                    context,
                                                    "Password updated successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                navHostController.navigate(
                                                    Routes.ProfileScreenRoutes(
                                                        user_id = userId
                                                    )
                                                )
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Password not match",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = screenWidth * 0.02f),
                                        shape = RoundedCornerShape(16.dp),
                                        colors = ButtonDefaults.buttonColors(Color(0xFF057367)),
                                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp)
                                    ) {
                                        Text(text = "Update password", color = Color.White)
                                    }

                                }

                                Spacer(Modifier.height(screenHeight * 0.02f))
                            }

                            Spacer(modifier = Modifier.height(screenHeight * 0.035f))

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = screenWidth * 0.04f),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                                colors = CardDefaults.cardColors(Color.White)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            horizontal = screenWidth * 0.05f,
                                            vertical = screenHeight * 0.02f
                                        ),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Spacer(Modifier.height(screenHeight * 0.01f))
                                    Text(
                                        text = "Security Features",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = (screenWidth.value * 0.05f).sp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                horizontal = screenWidth * 0.01f,
                                            )
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(color = Color(0xFFA8F3EB))
                                            .padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Security,
                                            contentDescription = null,
                                            modifier = Modifier.size(screenWidth * 0.06f),
                                            tint = Color(0xFF057367)
                                        )
                                        Spacer(modifier = Modifier.width(screenWidth * 0.05f))
                                        Column(
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = "Data Encrypted",
                                                fontWeight = FontWeight.Medium,
                                                fontSize = (screenWidth.value * 0.04f).sp,
                                                color = Color.Black
                                            )
                                            Spacer(modifier = Modifier.height(2.dp))

                                            Text(
                                                text = "Your data is encrypted with AES-256",
                                                fontWeight = FontWeight.Normal,
                                                fontSize = (screenWidth.value * 0.035f).sp,
                                                color = Color.DarkGray
                                            )

                                        }

                                    }

                                    Spacer(Modifier.height(screenHeight * 0.015f))

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                horizontal = screenWidth * 0.01f,
                                            )
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(color = Color(0xFFA8F3EB))
                                            .padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Lock,
                                            contentDescription = null,
                                            modifier = Modifier.size(screenWidth * 0.06f),
                                            tint = Color(0xFF057367)
                                        )
                                        Spacer(modifier = Modifier.width(screenWidth * 0.05f))
                                        Column(
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = "Security Authentication",
                                                fontWeight = FontWeight.Medium,
                                                fontSize = (screenWidth.value * 0.04f).sp,
                                                color = Color.Black
                                            )
                                            Spacer(modifier = Modifier.height(2.dp))

                                            Text(
                                                text = "Protected login with advanced security",
                                                fontWeight = FontWeight.Normal,
                                                fontSize = (screenWidth.value * 0.035f).sp,
                                                color = Color.DarkGray
                                            )

                                        }

                                    }

                                }
                                Spacer(Modifier.height(screenHeight * 0.01f))
                            }
                            Spacer(Modifier.height(screenHeight * 0.03f))

                        }
                    }
                }
            }
        }
    }

}