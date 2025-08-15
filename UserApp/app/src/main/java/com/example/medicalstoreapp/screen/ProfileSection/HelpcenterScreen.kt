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
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medicalstoreapp.screen.nav.Routes
import com.example.medicalstoreapp.viewModel.MyViewModel

@Composable
fun HelpcenterScreen(
    navHostController: NavHostController,
    viewModel: MyViewModel = hiltViewModel(),
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
                        .padding(horizontal = 16.dp)
                ) {

                    Spacer(modifier = Modifier.height(32.dp))
                    if (user.isNullOrEmpty()) {
                        Text(text = "No user data available", color = Color.Red)
                    } else {
                        for (i in user) {
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
                                    text = "Help Center",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = (screenWidth.value * 0.07).sp,
                                    color = Color.Black
                                )
                            }

                            val cardPadding = 16.dp

                            Spacer(Modifier.height(16.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = cardPadding),
                                horizontalArrangement = Arrangement.spacedBy(
                                    cardPadding,
                                    Alignment.End
                                )
                            ) {
                                // Call Us Card
                                Card(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable{
                                            navHostController.navigate(Routes.ContactUsScreenRoutes(
                                                user_id = userId
                                            ))
                                        },
                                    shape = RoundedCornerShape(16.dp),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                                    colors = CardDefaults.cardColors(Color.White)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(screenWidth * 0.1f)
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

                                        Text(
                                            text = "Call Us",
                                            fontWeight = FontWeight.Medium,
                                            fontSize = (screenWidth.value * 0.05f).sp,
                                            color = Color.Black
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(cardPadding))
                                // Email Card
                                Card(
                                    modifier = Modifier
                                        .weight(1f),
                                    shape = RoundedCornerShape(16.dp),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                                    colors = CardDefaults.cardColors(Color.White)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(screenWidth * 0.1f)
                                                .background(
                                                    color = Color(0xFFDB8FEE),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Email,
                                                modifier = Modifier.size(screenWidth * 0.07f),
                                                contentDescription = null,
                                                tint = Color(0xFF8713A6)
                                            )
                                        }

                                        Text(
                                            text = "Mail Us",
                                            fontWeight = FontWeight.Medium,
                                            fontSize = (screenWidth.value * 0.05f).sp,
                                            color = Color.Black
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Frequently Asked Questions",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = (screenWidth.value * 0.05f).sp,
                                    color = Color.Black
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            val questions = listOf(
                                Pair("How do I place an order?", "You can place an order by browsing our medical products section. Simply click on your desired product, fill in the required information, and submit your order."),
                                Pair("What payment methods do you accept?", "We accept all major credit/debit cards, UPI payments, net banking, and digital wallets like Paytm, PhonePe, and Google Pay"),
                                Pair("How long does delivery take?", "Standard delivery takes 24-48 hours within city limits. Express delivery is available within 2-4 hours for urgent medicines."),
                                Pair("Can I return medicines?", "Due to safety regulations, medicines cannot be returned once delivered. However, if you receive damaged or wrong items, please contact our support team immediately."),
                                Pair("Do I need a prescription for all medicines?", "Prescription medicines require a valid doctor's prescription. Over-the-counter medicines can be purchased without a prescription."),
                                )

                            Card(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                                colors = CardDefaults.cardColors(Color.White)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    questions.forEachIndexed { index, question ->
                                        FAQItem(question.first, question.second)
                                        if((index < questions.size - 1))
                                        HorizontalDivider(
                                            color = Color.LightGray,
                                            thickness = 1.dp,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }

                            }

                            Spacer(Modifier.height(21.dp))

                            Card(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                                colors = CardDefaults.cardColors( containerColor = Color(0xFFFFFFFF))
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Still need help?",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = (screenWidth.value * 0.05f).sp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Our support team is available 24/7 to assist you",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = (screenWidth.value * 0.035f).sp,
                                        color = Color.DarkGray
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))

                                    Button(
                                        onClick = {
                                            navHostController.navigate(Routes.ContactUsScreenRoutes(
                                                user_id = userId
                                            ))
                                        },
                                        shape = RoundedCornerShape(12.dp),
                                        colors = ButtonDefaults.buttonColors(Color(0xFF046C62)),
                                        modifier = Modifier.padding(horizontal = 16.dp)
                                    ) {
                                        Text(
                                            text = "Contact Us",
                                            fontWeight = FontWeight.Medium,
                                            fontSize = (screenWidth.value * 0.04f).sp,
                                            color = Color.White
                                        )
                                    }

                                }

                            }
                        }

                    }

                }
            }
        }
    }
}
@Composable
fun FAQItem(question: String, answer: String) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    var expanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded.value = !expanded.value }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = question,
                fontWeight = FontWeight.SemiBold,
                textDecoration =  if (expanded.value) TextDecoration.Underline else TextDecoration.None,
                fontSize = (screenWidth.value * 0.04).sp,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = if (expanded.value) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .size(screenWidth * 0.06f)
            )
        }

        if (expanded.value) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = answer,
                fontWeight = FontWeight.Normal,
                fontSize = (screenWidth.value * 0.035).sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}

