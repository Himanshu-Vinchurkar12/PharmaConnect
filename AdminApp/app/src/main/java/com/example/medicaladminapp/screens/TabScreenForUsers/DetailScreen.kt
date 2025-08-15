package com.example.medicaladminapp.screens.TabScreenForUsers

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
import androidx.compose.material.icons.outlined.Shop
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.medicaladminapp.screens.nav.Routes.DetailScreenRoutes
import com.example.medicaladminapp.viewModel.MyViewModels

@Composable
fun DetailScreen(
    res: DetailScreenRoutes,
    navHostController: NavHostController,
    viewModels: MyViewModels = hiltViewModel()
) {
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
        val screenHeight = configuration.screenHeightDp.dp

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
                    .size((screenWidth.value * 0.075).dp)
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
                    text = "User Details",
                    fontSize = (screenWidth.value * 0.08).sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "Complete users information",
                    fontSize = (screenWidth.value * 0.04).sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(), color = Color.LightGray, thickness = 1.dp
        )

        Spacer(Modifier.height(15.dp))

        //Row for Edit Icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ElevatedButton(
                onClick = {
                    navHostController.navigate(
                        Routes.UpdateScreenRoutes(
                            address = res.address,
                            block = res.block,
                            date_of_account_creation = res.date_of_account_creation,
                            email = res.email,
                            id = res.id,
                            is_Approved = res.is_Approved.toString(),
                            name = res.name,
                            password = res.password,
                            phone_number = res.phone_number,
                            pin_code = res.pin_code,
                            shope_name = res.shope_name,
                            user_id = res.user_id
                        )
                    )
                },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 10.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Row(
                    modifier = Modifier.wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null,
                        modifier = Modifier.size((screenWidth.value * 0.05).dp)
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        text = "Edit",
                        fontSize = (screenWidth.value * 0.05).sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                    )

                }
            }
            Spacer(Modifier.width(16.dp))
            //Approved or Not
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .border(
                        width = 1.dp,
                        color = if (res.is_Approved!!.toInt() == 1) Color(0xFF0F6413) else Color(
                            0xFFB90E06
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .height(screenHeight * 0.05f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        color = if (res.is_Approved.toInt() == 1) Color(0xFFA6EEBA) else Color(
                            0xFFE3C9C9
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(16.dp))
                Text(
                    text = if (res.is_Approved.toInt() == 1) "Approved" else "Pending",
                    fontSize = (screenWidth.value * 0.05).sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (res.is_Approved.toInt() == 1) Color(0xFF07600A) else Color.Red
                )
                Spacer(Modifier.width(16.dp))
            }
        }

        Spacer(Modifier.height(8.dp))

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
                            text = "${res.name}",
                            fontSize = (screenWidth.value * 0.05).sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "${res.shope_name}",
                            fontSize = (screenWidth.value * 0.04).sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "${res.user_id}",
                            fontSize = (screenWidth.value * 0.03).sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
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
                            Spacer(Modifier.width(12.dp))
                            Text(
                                text = "#",
                                fontSize = (screenWidth.value * 0.06).sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray
                            )
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Internal ID",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = "${res.id}",
                                    fontSize = (screenWidth.value * 0.04).sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = null,
                                modifier = Modifier.size((screenWidth.value * 0.06).dp),
                                tint = Color.DarkGray
                            )
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Full Name",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = "${res.name?.replaceFirstChar { it.uppercase() }}",
                                    fontSize = (screenWidth.value * 0.04).sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Outlined.CalendarMonth,
                                contentDescription = null,
                                modifier = Modifier.size((screenWidth.value * 0.05).dp),
                                tint = Color.DarkGray
                            )
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Account Created",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = "${res.date_of_account_creation}",
                                    fontSize = (screenWidth.value * 0.04).sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Outlined.Key,
                                contentDescription = null,
                                modifier = Modifier.size((screenWidth.value * 0.05).dp),
                                tint = Color.DarkGray
                            )
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Password",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                                Text(
                                    text = ".".repeat(res.password!!.length),
                                    fontSize = (screenWidth.value * 0.06).sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.DarkGray
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
                            Spacer(Modifier.width(12.dp))
                            Icon(
                                imageVector = Icons.Outlined.Mail,
                                contentDescription = null,
                                modifier = Modifier.size((screenWidth.value * 0.05).dp),
                                tint = Color.DarkGray
                            )
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Email Address",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = "${res.email}",
                                    fontSize = (screenWidth.value * 0.04).sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Outlined.Phone,
                                contentDescription = null,
                                modifier = Modifier.size((screenWidth.value * 0.06).dp),
                                tint = Color.DarkGray
                            )
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Phone Number",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = "${res.phone_number}",
                                    fontSize = (screenWidth.value * 0.04).sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = null,
                                modifier = Modifier.size((screenWidth.value * 0.05).dp),
                                tint = Color.DarkGray
                            )
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Address",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = "${res.address}",
                                    fontSize = (screenWidth.value * 0.04).sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Outlined.MyLocation,
                                contentDescription = null,
                                modifier = Modifier.size((screenWidth.value * 0.05).dp),
                                tint = Color.DarkGray
                            )
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "PIN Code",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Text(
                                    text = "${(res.pin_code)}",
                                    fontSize = (screenWidth.value * 0.04).sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray
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
                            Spacer(Modifier.width(12.dp))
                            Icon(
                                imageVector = Icons.Outlined.Shop,
                                contentDescription = null,
                                modifier = Modifier.size((screenWidth.value * 0.05).dp),
                                tint = Color.DarkGray
                            )
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Shop Name",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = "${res.shope_name}",
                                    fontSize = (screenWidth.value * 0.04).sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(10.dp))
                            Box(
                                modifier = Modifier
                                    .size((screenWidth.value * 0.05).dp)
                                    .clip(CircleShape)
                                    .background(Color.Green),
                            )
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Approval Status",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = if (res.is_Approved!!.toInt() == 1) "Approved" else "Pending",
                                    fontSize = (screenWidth.value * 0.04).sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray
                                )
                            }


                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(10.dp))
                            Box(
                                modifier = Modifier
                                    .size((screenWidth.value * 0.056).dp)
                                    .clip(CircleShape)
                                    .background(Color.Green),
                            )
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Account Status",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = if (res.is_Approved!!.toInt() == 1) "Active" else "Inactive",
                                    fontSize = (screenWidth.value * 0.04).sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray
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
                            Text(
                                text = "#",
                                fontSize = (screenWidth.value * 0.08).sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Green
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Account Statistics",
                                fontSize = (screenWidth.value * 0.06).sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )

                        }
                        Spacer(Modifier.height(18.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .clip(RoundedCornerShape(12.dp)) // Clip first
                                .background(Color(0xFFA4EFA8))   // Then apply background
                                .padding(16.dp),                // Adjust padding for nice spacing
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = if (res.is_Approved!!.toInt() == 1) "Active" else "Inactive",
                                    fontSize = (screenWidth.value * 0.08).sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF074D0B)
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = "Account Status",
                                    fontSize = (screenWidth.value * 0.04).sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF19601C)
                                )
                            }
                        }

                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            // User ID
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp)) // Clip first
                                    .background(Color(0xFF2783D3))   // Then background
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "${res.id}",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF0B3860)
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = "User ID",
                                    fontSize = (screenWidth.value * 0.04).sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF0A4477)
                                )
                            }

                            Spacer(Modifier.width(10.dp))

                            // Pin Code
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp)) // Clip first
                                    .background(Color(0xFFB39AE7))   // Then background
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "${res.pin_code}",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF4A18A6)
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = "Pin Code",
                                    fontSize = (screenWidth.value * 0.04).sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF5A16E5)
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
                        navHostController.navigate(
                            Routes.UpdateScreenRoutes(
                                address = res.address,
                                block = res.block,
                                date_of_account_creation = res.date_of_account_creation,
                                email = res.email,
                                id = res.id,
                                is_Approved = res.is_Approved.toString(),
                                name = res.name,
                                password = res.password,
                                phone_number = res.phone_number,
                                pin_code = res.pin_code,
                                shope_name = res.shope_name,
                                user_id = res.user_id
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
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
                            imageVector = Icons.Outlined.Block,
                            contentDescription = null,
                            modifier = Modifier.size((screenWidth.value * 0.05).dp),
                            tint = Color.Black
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = "Edit User",
                            fontSize = (screenWidth.value * 0.05).sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }
                }
            }
            item {
                Spacer(Modifier.height(10.dp))
            }
            item {
                ElevatedButton(
                    onClick = {
                        viewModels.deleteSpecificUser(userId = res.user_id.toString())
                        navHostController.popBackStack()
                        Toast.makeText(context, "User Deleted", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
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
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = null,
                            modifier = Modifier.size((screenWidth.value * 0.05).dp),
                            tint = Color.White
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = "Delete User",
                            fontSize = (screenWidth.value * 0.05).sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            }
            item {
                Spacer(Modifier.height(10.dp))
            }
            if (res.is_Approved!!.toInt() == 1) {
                item {
                    ElevatedButton(
                        onClick = {
                            viewModels.updateUserDetails(
                                userId = res.user_id.toString(),
                                is_Approved = 0
                            )
                            Toast.makeText(context, "User Block Successfully ", Toast.LENGTH_SHORT)
                                .show()
                            navHostController.popBackStack()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 10.dp,
                            pressedElevation = 5.dp
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Block,
                                contentDescription = null,
                                modifier = Modifier.size((screenWidth.value * 0.05).dp),
                                tint = Color.White
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Block User",
                                fontSize = (screenWidth.value * 0.05).sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }
                }
            } else {
                item {
                    ElevatedButton(
                        onClick = {
                            viewModels.updateUserDetails(
                                userId = res.user_id.toString(),
                                is_Approved = 1
                            )
                            Toast.makeText(
                                context,
                                "User Approved Successfully ",
                                Toast.LENGTH_SHORT
                            ).show()
                            navHostController.popBackStack()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF08574F),
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 10.dp,
                            pressedElevation = 5.dp
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier.size((screenWidth.value * 0.05).dp),
                                tint = Color.White
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Approve User",
                                fontSize = (screenWidth.value * 0.05).sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            item {
                Spacer(Modifier.height(30.dp))
            }

        }
    }

}







