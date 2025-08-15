package com.example.medicalstoreapp.screen.ProfileSection

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medicalstoreapp.R
import com.example.medicalstoreapp.screen.nav.Routes
import com.example.medicalstoreapp.viewModel.MyViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileScreen(
    userId: String,
    viewModel: MyViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

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
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                }
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

                            Row(modifier = Modifier.fillMaxWidth()) {
                                Image(
                                    painter = painterResource(R.drawable.ic_back),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size((screenWidth * 0.1).dp)
                                        .clickable {
                                            navHostController.navigate(
                                                Routes.HomeScreenRoutes(
                                                    user_id = userId
                                                )
                                            )
                                        }
                                )
                            }

                            // Top Profile Header
                            val profileImageSize = (screenWidth * 0.26).dp
                            val notificationIconSize = (screenWidth * 0.14).dp
                            val spaceBetweenNameAndIcons = (screenWidth * 0.02).dp  // ⬅️ Increased
                            val spaceBetweenIcons = 2.dp
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "Hi,",
                                        fontSize = (screenWidth * 0.05f).sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 5.dp)
                                    )

                                    Text(
                                        text = i.name.uppercase(),
                                        fontSize = (screenWidth * 0.06f).sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 5.dp, top = 5.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(spaceBetweenNameAndIcons))

                                Image(
                                    painter = painterResource(R.drawable.ic_notification),
                                    contentDescription = "Notification",
                                    modifier = Modifier.size(notificationIconSize)
                                )

                                Spacer(modifier = Modifier.width(spaceBetweenIcons))

                                Image(
                                    painter = painterResource(R.drawable.profile1),
                                    contentDescription = "Profile",
                                    modifier = Modifier
                                        .size(profileImageSize)
                                        .clip(RoundedCornerShape(100.dp))
                                )
                            }


                            Spacer(modifier = Modifier.height(16.dp))

                            val seemore = remember { mutableStateOf(false) }
                            val labelFontSize = (configuration.screenWidthDp * 0.04).sp
                            val valueFontSize = (configuration.screenWidthDp * 0.045).sp

                            val infoRowSpacing = 10.dp

                            @Composable
                            fun InfoRow(icon: ImageVector, label: String, value: String) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(icon, contentDescription = label, tint = Color.DarkGray)

                                    Column {
                                        Text(
                                            text = label,
                                            fontSize = labelFontSize,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = value,
                                            fontSize = valueFontSize,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.SansSerif
                                        )
                                    }
                                }
                            }

                            Card(
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                colors = CardDefaults.cardColors(Color.White)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(infoRowSpacing)
                                ) {
                                    InfoRow(Icons.Default.Person, "Name", i.name)
                                    InfoRow(Icons.Default.Phone, "Phone Number", i.phone_number)
                                    InfoRow(Icons.Default.Email, "E-mail", i.email)

                                    if (seemore.value) {
                                        InfoRow(Icons.Default.LocationOn, "Address", i.address)
                                        InfoRow(Icons.Default.MyLocation, "Pin-Code", i.pin_code)
                                        InfoRow(Icons.Default.Shop, "Shoppe Name", i.shope_name)
                                        InfoRow(
                                            Icons.Default.DateRange,
                                            "Created On",
                                            i.date_of_account_creation
                                        )

                                        Text(
                                            text = "See Less...",
                                            color = Color.Red,
                                            modifier = Modifier
                                                .align(Alignment.End)
                                                .clickable { seemore.value = false }
                                        )
                                    } else {
                                        Text(
                                            text = "See more...",
                                            color = Color.Blue,
                                            modifier = Modifier
                                                .align(Alignment.End)
                                                .clickable { seemore.value = true }
                                        )
                                    }
                                }
                            }
                        }


                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            "Account Settings",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        SettingsItem(
                            icon = Icons.Default.Person,
                            title = "Edit Profile",
                            subtitle = "Update your personal info",
                            onClick = {
                                navHostController.navigate(Routes.EditScreenRoutes(
                                    user_id = userId
                                ))
                            }
                        )
                        SettingsItem(
                            icon = Icons.Default.History,
                            title = "Order History",
                            subtitle = "View your past orders",
                            onClick = {
                                navHostController.navigate(
                                    Routes.OrderHistoryScreenRoutes( user_id = userId)
                                )
                            }
                        )
                        SettingsItem(
                            icon = Icons.Default.Payment,
                            title = "Payment Methods",
                            subtitle = "Manage your payment options",
                            onClick = {
                                navHostController.navigate(
                                    Routes.PaymentScreenRoutes(
                                        user_id = userId
                                    )
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text("Support", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        SettingsItem(
                            icon = Icons.Default.Security,
                            title = "Privacy & Security",
                            subtitle = "Manage your privacy",
                            onClick = {
                                navHostController.navigate(Routes.PrivacySecurityScreenRoutes(
                                    user_id = userId
                                ))
                            }
                        )
                        SettingsItem(
                            icon = Icons.AutoMirrored.Default.Help,
                            title = "Help Center",
                            subtitle = "FAQs and troubleshooting",
                            onClick = {
                                navHostController.navigate(Routes.HelpcenterScreenRoutes(
                                    user_id = userId
                                ))
                            }
                        )
                        SettingsItem(
                            icon = Icons.Default.Email,
                            title = "Contact Us",
                            subtitle = "Get in touch with our team",
                            onClick = {
                                navHostController.navigate(Routes.ContactUsScreenRoutes(
                                    user_id = userId
                                ))
                            }
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        // Logout Button
                        ElevatedButton(
                            onClick = {
                                viewModel.logout()
                                navHostController.navigate(Routes.LogInScreenRoutes) {
                                    popUpTo(0) // clear the backstack
                                }
                            },
                            colors = ButtonDefaults.outlinedButtonColors(Color.Red),
                            elevation = ButtonDefaults.buttonElevation(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(vertical = 8.dp)
                        ) {
                            Icon(
                                Icons.AutoMirrored.Default.ExitToApp,
                                contentDescription = "Logout",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "Logout",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp)) // Space for bottom nav
                    }

                }
            }
        }
    }
}



@Composable
fun SettingsItem(icon: ImageVector, title: String, subtitle: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = title,
                tint = Color(0xFF77EADE),
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.SemiBold)
                Text(subtitle, fontSize = 12.sp, color = Color.Gray)
            }
            Icon(Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}



