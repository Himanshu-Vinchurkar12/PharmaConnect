package com.example.medicalstoreapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medicalstoreapp.R
import com.example.medicalstoreapp.screen.nav.Routes


@Composable
fun StartScreen(navController: NavController ) {


    Column(
        modifier = Modifier.fillMaxSize().background(brush = Brush.linearGradient(
            colors = listOf(Color.White, Color(0xFF77EADE)))),

    ) {
        Text(
            text = "Get Started",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(start =  20.dp, top = 120.dp)
        )
        Text(
            text = "Get start with signUp or LogIn",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 20.dp, top = 10.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 65.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Image(painter = painterResource(R.drawable.medicart), contentDescription = null, modifier = Modifier.shadow(elevation = 10.dp, shape = RoundedCornerShape(120.dp)).padding(end = 10.dp).clip(
                RoundedCornerShape(120.dp)
            ))
            Spacer(Modifier.height(70.dp))

            ElevatedButton(
                onClick = {
                    navController.navigate(Routes.LogInScreenRoutes)

                },
                modifier = Modifier.size(width = 170.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(Color.Red),
                elevation = ButtonDefaults.buttonElevation(8.dp),
            )
            {

                Text("LogIn", fontSize = 18.sp)
            }
            Spacer(Modifier.size(20.dp))
            ElevatedButton(
                onClick = {
                    navController.navigate(Routes.SignUpScreenRoutes)

                },
                modifier = Modifier.size(width = 170.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(Color.Red),
                elevation = ButtonDefaults.buttonElevation(8.dp),
            )
            {
                Text("SignUp", fontSize = 18.sp)
            }
        }

    }

}