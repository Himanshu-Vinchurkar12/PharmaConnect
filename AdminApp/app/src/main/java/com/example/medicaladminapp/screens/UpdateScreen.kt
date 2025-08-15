package com.example.medicaladminapp.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Block
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Shop
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medicaladminapp.screens.nav.Routes
import com.example.medicaladminapp.viewModel.MyViewModels
import kotlin.text.toInt

@Composable
fun UpdateScreen(
    res: Routes.UpdateScreenRoutes,
    navHostController: NavHostController,
    viewModels: MyViewModels = hiltViewModel()
) {
    val updateUserDetails = viewModels.updateUserDetails.collectAsState()

    var name = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    var address = remember { mutableStateOf("") }
    var phone_number = remember { mutableStateOf("") }
    var pin_code = remember { mutableStateOf("") }
    var shope_name = remember { mutableStateOf("") }


    LaunchedEffect(updateUserDetails) {
        viewModels.updateUserDetails
    }


    LaunchedEffect(key1 = Unit) {
        name.value = res.name.toString()
        password.value = res.password.toString()
        email.value = res.email.toString()
        address.value = res.address.toString()
        phone_number.value = res.phone_number.toString()
        pin_code.value = res.pin_code.toString()
        shope_name.value = res.shope_name.toString()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.White, Color(0xFF9AD9D4))
                )
            )
    ) {
        val context = LocalContext.current
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(16.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size((screenWidth.value * 0.078).dp)
                    .clickable { navHostController.popBackStack() },
                tint = Color.DarkGray
            )
            Spacer(Modifier.width(20.dp))

            Column(
                modifier = Modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = " Edit User Details",
                    fontSize = (screenWidth.value * 0.08).sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "Update user information",
                    fontSize = (screenWidth.value * 0.04).sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.LightGray,
            thickness = 1.dp
        )
        Spacer(Modifier.height(15.dp))

        LazyColumn {
            item {
                Spacer(Modifier.height(20.dp))
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Spacer(Modifier.height(10.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size((screenWidth.value * 0.26).dp)
                                .clip(shape = CircleShape)
                                .background(color = Color(0xFF0F5D52)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = res.name?.firstOrNull()?.uppercase() ?: "",
                                fontSize = (screenWidth.value * 0.06).sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = "Full Name",
                            fontSize = (screenWidth.value * 0.05).sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(8.dp))
                        OutlinedTextField(
                            value = name.value,
                            onValueChange = { name.value = it },
                            label = { Text("Full Name") },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Gray,
                                unfocusedBorderColor = Color.LightGray,
                            )
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = "Shop Name",
                            fontSize = (screenWidth.value * 0.05).sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(8.dp))
                        OutlinedTextField(
                            value = shope_name.value,
                            onValueChange = {
                                shope_name.value = it
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Gray,
                                unfocusedBorderColor = Color.LightGray,
                            ),
                            modifier = Modifier
                        )
                        Spacer(Modifier.height(8.dp))
                    }
                }
            }
            item {
                Spacer(Modifier.height(20.dp))
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Spacer(Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = null,
                                modifier = Modifier.size((screenWidth.value * 0.07).dp),
                                tint = Color.Green
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Personal information",
                                fontSize = (screenWidth.value * 0.06).sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )

                        }
                        Spacer(Modifier.height(18.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Internal ID",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                OutlinedTextField(
                                    value = res.id.toString(),
                                    onValueChange = {},
                                    readOnly = true,
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Gray,
                                        unfocusedBorderColor = Color.LightGray,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Full Name",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                OutlinedTextField(
                                    value = name.value,
                                    onValueChange = { name.value = it },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Gray,
                                        unfocusedBorderColor = Color.LightGray,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Account Created",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                OutlinedTextField(
                                    value = res.date_of_account_creation.toString(),
                                    onValueChange = {},
                                    readOnly = true,
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Gray,
                                        unfocusedBorderColor = Color.LightGray,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Password",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                                OutlinedTextField(
                                    value = password.value,
                                    onValueChange = { password.value = it },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Gray,
                                        unfocusedBorderColor = Color.LightGray,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                    }
                }
            }
            item {
                Spacer(Modifier.height(20.dp))
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Spacer(Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Outlined.Mail,
                                contentDescription = null,
                                modifier = Modifier.size((screenWidth.value * 0.07).dp),
                                tint = Color.Green
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Contact information",
                                fontSize = (screenWidth.value * 0.06).sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )

                        }
                        Spacer(Modifier.height(18.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Email Address",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                OutlinedTextField(
                                    value = email.value,
                                    onValueChange = { email.value = it },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Gray,
                                        unfocusedBorderColor = Color.LightGray,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Phone Number",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                OutlinedTextField(
                                    value = phone_number.value,
                                    onValueChange = { phone_number.value = it },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Gray,
                                        unfocusedBorderColor = Color.LightGray,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Address",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                OutlinedTextField(
                                    value = address.value,
                                    onValueChange = { address.value = it },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Gray,
                                        unfocusedBorderColor = Color.LightGray,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "PIN Code",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                OutlinedTextField(
                                    value = pin_code.value,
                                    onValueChange = { pin_code.value = it },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Gray,
                                        unfocusedBorderColor = Color.LightGray,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }
                        }
                        Spacer(Modifier.height(16.dp))
                    }
                }
            }
            item {
                Spacer(Modifier.height(20.dp))
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Spacer(Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Outlined.AccountBox,
                                contentDescription = null,
                                modifier = Modifier.size((screenWidth.value * 0.07).dp),
                                tint = Color.Green
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Business information",
                                fontSize = (screenWidth.value * 0.06).sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )

                        }
                        Spacer(Modifier.height(18.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Shop Name",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                OutlinedTextField(
                                    value = shope_name.value,
                                    onValueChange = { shope_name.value = it },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Gray,
                                        unfocusedBorderColor = Color.LightGray,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Approval Status",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                OutlinedTextField(
                                    value = if (res.is_Approved!!.toInt() == 1) "Approved" else "Pending",
                                    onValueChange = {},
                                    readOnly = true,
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Gray,
                                        unfocusedBorderColor = Color.LightGray,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Account Status",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                OutlinedTextField(
                                    value = if (res.is_Approved!!.toInt() == 1) "Active" else "Inactive",
                                    onValueChange = {},
                                    readOnly = true,
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Gray,
                                        unfocusedBorderColor = Color.LightGray,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }
                        }
                        Spacer(Modifier.height(16.dp))
                    }
                }
            }
            item {
                Spacer(Modifier.height(20.dp))
            }

            //Buttons
            item {
                ElevatedButton(
                    onClick = {
                        viewModels.updateUserDetails(
                            name = name.value,
                            password = password.value,
                            email = email.value,
                            address = address.value,
                            phone_number = phone_number.value,
                            pin_code = pin_code.value,
                            shope_name = shope_name.value,
                            userId = res.user_id.toString(),
                            is_Approved = res.is_Approved?.toInt()
                        )
                        navHostController.popBackStack()
                        Toast.makeText(
                            context,
                            "User Details Updated Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0F5D52),
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 10.dp
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Save,
                            contentDescription = null,
                            modifier = Modifier.size((screenWidth.value * 0.05).dp),
                            tint = Color.White
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = "Save User Details",
                            fontSize = (screenWidth.value * 0.05).sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            }

            item {
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}