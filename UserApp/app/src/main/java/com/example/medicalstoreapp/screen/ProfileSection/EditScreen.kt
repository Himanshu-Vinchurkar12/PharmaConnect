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
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
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
fun EditScreen(
    userId: String,
    viewModel: MyViewModel = hiltViewModel(),
    navHostController: NavHostController,
){


    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var name = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    var address = remember { mutableStateOf("") }
    var phone_number = remember { mutableStateOf("") }
    var pin_code = remember { mutableStateOf("") }
    var shope_name = remember { mutableStateOf("") }

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
                        .padding(horizontal = 16.dp)
                )
                {
                    Spacer(modifier = Modifier.height(32.dp))
                    if (user.isNullOrEmpty()) {
                        Text(text = "No user data available", color = Color.Red)

                    } else {
                        for (i in user) {

                            LaunchedEffect(key1 = Unit) {
                                name.value = i.name
                                password.value = i.password
                                email.value = i.email
                                address.value = i.address
                                phone_number.value = i.phone_number
                                pin_code.value = i.pin_code
                                shope_name.value = i.shope_name
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
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
                                    text = "Edit Profile",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = (screenWidth.value * 0.07).sp,
                                    color = Color.Black
                                )
                            }

                            Spacer(Modifier.height(16.dp))
                            Card(
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                colors = CardDefaults.cardColors(Color.White)
                            )
                            {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(screenWidth * 0.3f)
                                                .clip(
                                                    CircleShape
                                                )
                                                .background(Color(0xFF046C62)),
                                            contentAlignment = Alignment.Center
                                        ){
                                            Icon(
                                                imageVector = Icons.Rounded.Person,
                                                contentDescription = null,
                                                modifier = Modifier.size(screenWidth * 0.15f),
                                                tint = Color.White
                                            )

                                        }
                                    }
                                    Spacer(modifier = Modifier.height(16.dp))

                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp),
                                        verticalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Text(
                                            text = "Full Name",
                                            fontWeight = FontWeight.Medium,
                                            fontSize = (screenWidth.value * 0.05).sp,
                                            color = Color.Black
                                        )
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Person,
                                                contentDescription = null,
                                                modifier = Modifier.size(screenWidth * 0.07f),
                                                tint = Color.Gray
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))

                                            OutlinedTextField(
                                                value = name.value,
                                                onValueChange = { name.value = it },
                                                shape = RoundedCornerShape(12.dp),
                                                modifier = Modifier.weight(1f),
                                                colors = OutlinedTextFieldDefaults.colors(
                                                    focusedBorderColor = Color.Gray,
                                                    unfocusedBorderColor = Color.LightGray,
                                                    focusedContainerColor = Color.LightGray,
                                                    unfocusedContainerColor = Color.White
                                                )
                                            )

                                        }

                                        Text(
                                            text = "Phone Number",
                                            fontWeight = FontWeight.Medium,
                                            fontSize = (screenWidth.value * 0.05).sp,
                                            color = Color.Black
                                        )
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Phone,
                                                contentDescription = null,
                                                modifier = Modifier.size(screenWidth * 0.07f),
                                                tint = Color.Gray
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))

                                            OutlinedTextField(
                                                value = phone_number.value,
                                                onValueChange = { phone_number.value = it },
                                                shape = RoundedCornerShape(12.dp),
                                                colors = OutlinedTextFieldDefaults.colors(
                                                    focusedBorderColor = Color.Gray,
                                                    unfocusedBorderColor = Color.LightGray,
                                                    focusedContainerColor = Color.LightGray,
                                                    unfocusedContainerColor = Color.White
                                                )
                                            )

                                        }
                                        Text(
                                            text = "E-mail",
                                            fontWeight = FontWeight.Medium,
                                            fontSize = (screenWidth.value * 0.05).sp,
                                            color = Color.Black
                                        )
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Mail,
                                                contentDescription = null,
                                                modifier = Modifier.size(screenWidth * 0.07f),
                                                tint = Color.Gray
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))

                                            OutlinedTextField(
                                                value = email.value,
                                                onValueChange = { email.value = it },
                                                shape = RoundedCornerShape(12.dp),
                                                colors = OutlinedTextFieldDefaults.colors(
                                                    focusedBorderColor = Color.Gray,
                                                    unfocusedBorderColor = Color.LightGray,
                                                    focusedContainerColor = Color.LightGray,
                                                    unfocusedContainerColor = Color.White
                                                )
                                            )

                                        }
                                        Text(
                                            text = "Address",
                                            fontWeight = FontWeight.Medium,
                                            fontSize = (screenWidth.value * 0.05).sp,
                                            color = Color.Black
                                        )
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.LocationOn,
                                                contentDescription = null,
                                                modifier = Modifier.size(screenWidth * 0.07f),
                                                tint = Color.Gray
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))

                                            OutlinedTextField(
                                                value = address.value,
                                                onValueChange = { address.value = it },
                                                shape = RoundedCornerShape(12.dp),
                                                colors = OutlinedTextFieldDefaults.colors(
                                                    focusedBorderColor = Color.Gray,
                                                    unfocusedBorderColor = Color.LightGray,
                                                    focusedContainerColor = Color.LightGray,
                                                    unfocusedContainerColor = Color.White
                                                )
                                            )

                                        }
                                        Text(
                                            text = "Pin Code",
                                            fontWeight = FontWeight.Medium,
                                            fontSize = (screenWidth.value * 0.05).sp,
                                            color = Color.Black
                                        )
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.MyLocation,
                                                contentDescription = null,
                                                modifier = Modifier.size(screenWidth * 0.07f),
                                                tint = Color.Gray
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))

                                            OutlinedTextField(
                                                value = pin_code.value,
                                                onValueChange = { pin_code.value = it },
                                                shape = RoundedCornerShape(12.dp),
                                                colors = OutlinedTextFieldDefaults.colors(
                                                    focusedBorderColor = Color.Gray,
                                                    unfocusedBorderColor = Color.LightGray,
                                                    focusedContainerColor = Color.LightGray,
                                                    unfocusedContainerColor = Color.White
                                                )
                                            )

                                        }
                                        Text(
                                            text = "Shop Name",
                                            fontWeight = FontWeight.Medium,
                                            fontSize = (screenWidth.value * 0.05).sp,
                                            color = Color.Black
                                        )
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Storefront,
                                                contentDescription = null,
                                                modifier = Modifier.size(screenWidth * 0.07f),
                                                tint = Color.Gray
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))

                                            OutlinedTextField(
                                                value = shope_name.value,
                                                onValueChange = { shope_name.value = it },
                                                shape = RoundedCornerShape(12.dp),
                                                colors = OutlinedTextFieldDefaults.colors(
                                                    focusedBorderColor = Color.Gray,
                                                    unfocusedBorderColor = Color.LightGray,
                                                    focusedContainerColor = Color.LightGray,
                                                    unfocusedContainerColor = Color.White
                                                )
                                            )

                                        }


                                        Spacer(modifier = Modifier.height(10.dp))

                                        // Logout Button
                                        ElevatedButton(
                                            onClick = {
                                                viewModel.updateUserDetails(
                                                    name = name.value,
                                                    password = password.value,
                                                    email = email.value,
                                                    address = address.value,
                                                    phone_number = phone_number.value,
                                                    pin_code = pin_code.value,
                                                    shope_name = shope_name.value,
                                                    userId = i.user_id,
                                                    is_Approved = i.is_Approved
                                                )
                                                navHostController.navigate(Routes.ProfileScreenRoutes(
                                                    user_id = i.user_id
                                                ))
                                            },
                                            colors = ButtonDefaults.outlinedButtonColors(Color(0xFF046C62)),
                                            elevation = ButtonDefaults.buttonElevation(8.dp),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(60.dp)
                                                .padding(vertical = 8.dp)
                                        ) {
                                            Icon(
                                                Icons.Outlined.Save,
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                "Save Changes",
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 20.sp
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(5.dp))
                                    }

                                }

                            }
                        }

                    }

                    Spacer(modifier = Modifier.height(32.dp))

                }
            }
        }
    }



}