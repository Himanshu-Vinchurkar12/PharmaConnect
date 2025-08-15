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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medicalstoreapp.screen.nav.Routes
import com.example.medicalstoreapp.viewModel.MyViewModel

@Composable
fun ContactUsScreen(
    viewModel: MyViewModel = hiltViewModel(),
    navHostController: NavHostController,
    userId: String
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

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
                                text = "Contact Us",
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
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = screenWidth * 0.05f,
                                        vertical = screenHeight * 0.02f
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(screenWidth * 0.15f)
                                        .background(
                                            color = Color(0xFF90DA93),
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Phone,
                                        contentDescription = null,
                                        modifier = Modifier.size(screenWidth * 0.07f),
                                        tint = Color(0xFF04675D)
                                    )
                                }

                                Spacer(modifier = Modifier.width(screenWidth * 0.05f))

                                Column {
                                    Text(
                                        text = "Phone",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = (screenWidth.value * 0.05f).sp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "+91 12345 67890",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = (screenWidth.value * 0.04f).sp,
                                        color = Color.DarkGray
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "Available 24/7",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04f).sp,
                                        color = Color.Gray
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
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = screenWidth * 0.05f,
                                        vertical = screenHeight * 0.02f
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(screenWidth * 0.15f)
                                        .background(
                                            color = Color(0xFF83B5E1),
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Email,
                                        contentDescription = null,
                                        modifier = Modifier.size(screenWidth * 0.07f),
                                        tint = Color(0xFF044985)
                                    )
                                }

                                Spacer(modifier = Modifier.width(screenWidth * 0.05f))

                                Column {
                                    Text(
                                        text = "Email",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = (screenWidth.value * 0.05f).sp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "+support@raj.com",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = (screenWidth.value * 0.04f).sp,
                                        color = Color.DarkGray
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "Response within 24 hours",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04f).sp,
                                        color = Color.Gray
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
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = screenWidth * 0.05f,
                                        vertical = screenHeight * 0.02f
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(screenWidth * 0.15f)
                                        .background(
                                            color = Color(0xFFE17A77),
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.LocationOn,
                                        contentDescription = null,
                                        modifier = Modifier.size(screenWidth * 0.07f),
                                        tint = Color(0xFFA4120E)
                                    )
                                }

                                Spacer(modifier = Modifier.width(screenWidth * 0.05f))

                                Column {
                                    Text(
                                        text = "Address",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = (screenWidth.value * 0.05f).sp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "Raj Medical pharma-shop",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = (screenWidth.value * 0.04f).sp,
                                        color = Color.DarkGray
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "xyz, Mumbai , Maharashtra - 400001",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04f).sp,
                                        color = Color.Gray
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
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = screenWidth * 0.05f,
                                        vertical = screenHeight * 0.02f
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(screenWidth * 0.15f)
                                        .background(
                                            color = Color(0xFFA686E7),
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Timer,
                                        contentDescription = null,
                                        modifier = Modifier.size(screenWidth * 0.07f),
                                        tint = Color(0xFF5021AF)
                                    )
                                }

                                Spacer(modifier = Modifier.width(screenWidth * 0.05f))

                                Column {
                                    Text(
                                        text = "Business Hours",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = (screenWidth.value * 0.05f).sp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "Mon-Sat: 8:00 AM-10:00 AM",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = (screenWidth.value * 0.04f).sp,
                                        color = Color.DarkGray
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "Sunday: Closed",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.04f).sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(screenHeight * 0.04f))


                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFCE1E1))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Emergency?",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = (screenWidth.value * 0.05f).sp,
                                    color = Color(0xFF6B0404)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "For urgent medical queries, call our 24/7 helpline",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = (screenWidth.value * 0.035f).sp,
                                    color = Color(0xFF8F1212)
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Button(
                                    onClick = {
//                                            navHostController.navigate(Routes())
                                    },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(Color(0xFFB70F0F)),
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                ) {
                                    Text(
                                        text = "Call Emergency Line",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = (screenWidth.value * 0.04f).sp,
                                        color = Color.White
                                    )
                                }

                            }

                        }


                    }
                    Spacer(modifier = Modifier.height(screenHeight * 0.05f))
                }
            }
        }
    }

}
