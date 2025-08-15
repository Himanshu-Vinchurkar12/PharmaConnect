package com.example.medicalstoreapp.screen.ProfileSection

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.outlined.Construction
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medicalstoreapp.screen.nav.Routes
import com.example.medicalstoreapp.viewModel.MyViewModel

@Composable
fun PaymentScreen(
    navHostController: NavHostController,
    viewModel: MyViewModel= hiltViewModel(),
    userId : String
){


    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.White, Color(0xFF77EADE))
                )
            )
            .padding(horizontal = 6.dp),
        verticalArrangement = Arrangement.Center
    )
    {

        Card(
            modifier = Modifier.padding(horizontal = (screenWidth.value * 0.02).dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(screenHeight * 0.01f))
                Box(
                    modifier = Modifier
                        .size(screenWidth * 0.2f)
                        .clip(
                            CircleShape
                        )
                        .background(Color(0xFF046C62)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Construction,
                        contentDescription = null,
                        modifier = Modifier.size(screenWidth * 0.15f),
                        tint = Color.White
                    )
                }

                Spacer(Modifier.height(screenHeight * 0.03f))

                Text(
                    text = "🚧 Feature Under Development",
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = (screenWidth.value * 0.055).sp
                )

                Spacer(Modifier.height(screenHeight * 0.01f))

                Text(
                    text = "This functionality is not implemented yet.",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = (screenWidth.value * 0.032).sp
                )

                Spacer(Modifier.height(screenHeight * 0.02f))

                Text(
                    text = "We truly appreciate your patience and support.",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = (screenWidth.value * 0.035).sp
                )

                Spacer(Modifier.height(screenHeight * 0.01f))

                val styledText = AnnotatedString.Builder().apply {
                    append("Developer  is working hard to bring the ")
                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                    append("Payment Method")
                    pop() // End bold styling
                    append(".")
                }.toAnnotatedString()

                Text(
                    text = styledText,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = (screenWidth.value * 0.033).sp
                )

                Spacer(Modifier.height(screenHeight * 0.008f))

                Text(
                    text = "feature to you very soon.",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = (screenWidth.value * 0.035).sp
                )


                Spacer(Modifier.height(screenHeight * 0.025f))

                Text(
                    text = "Thank you for being with us!",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = (screenWidth.value * 0.038).sp
                )

                Spacer(Modifier.height(screenHeight * 0.02f))

                Button(
                    onClick = {
                        navHostController.navigate(
                            Routes.ProfileScreenRoutes(
                                user_id = userId
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFF0A8C7F)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(horizontal = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier
                                .size((screenWidth * 0.06f))
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Back to Home",
                            fontWeight = FontWeight.Medium,
                            fontSize = (screenWidth.value * 0.04).sp,
                            color = Color.White
                        )
                    }

                }


                Spacer(Modifier.height(screenHeight * 0.03f))

            }

        }


    }

}
