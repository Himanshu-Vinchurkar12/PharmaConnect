package com.example.medicaladminapp.screens.TabScreenForProduct

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medicaladminapp.R
import com.example.medicaladminapp.models.getAllProductsResponse
import com.example.medicaladminapp.viewModel.MyViewModels

@Composable
fun SyrupsScreens(navHostController: NavHostController, viewModels: MyViewModels = hiltViewModel()) {

    val state = viewModels.getAllproducts.collectAsState()
    val text = remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit){
        viewModels.getAllproducts
    }

    when {
        state.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.value.isError != null -> {
            Column(
                modifier = Modifier.fillMaxSize().padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = state.value.isError.toString())
            }
        }
        state.value.isdata != null -> {
            Column(
                modifier = Modifier.fillMaxSize().background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.White, Color(0xFF5EE5DA))
                    )).padding(top = 5.dp)
            ) {

                Text("All Syrups", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(top = 50.dp).align(Alignment.CenterHorizontally))
                Spacer(Modifier.height(30.dp))

                OutlinedTextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    placeholder = { Text(text = " Search Syrups..") },
                    singleLine = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Color.DarkGray
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.DarkGray,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(Modifier.height(16.dp))


                val getSyrup = state.value.isdata
                if(getSyrup != null) {

                    val filteredSyrup = getSyrup.filter {
                        it.product_name.contains(text.value.trim(), ignoreCase = true)
                    }

                    if (filteredSyrup.isEmpty()){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.tablet_not_found),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(200.dp).clip(shape = CircleShape).shadow(elevation = 2.dp)
                            )
                            Spacer(Modifier.height(25.dp))
                            Text(
                                text = "No Syrup Found",
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    else{
                        AnimatedContent(
                            targetState = filteredSyrup,
                            transitionSpec = {
                                fadeIn(animationSpec = tween(300))+
                                        slideInVertically(initialOffsetY = { it }) togetherWith
                                        fadeOut(animationSpec = tween(300)) +
                                        slideOutVertically(targetOffsetY = { it })
                            },
                            label = "SyrupListAnimation"
                        ) { targetList ->
                            LazyColumn{
                                items(targetList){
                                    AllSyrup(res = it , navHostController = navHostController, viewModels = viewModels)
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
fun AllSyrup(res: getAllProductsResponse, navHostController: NavHostController, viewModels: MyViewModels){
    if(res.category.toString() == "Syrup"){

        Card(
            modifier = Modifier.fillMaxWidth().padding(15.dp).clickable{},
            colors = CardDefaults.cardColors(containerColor = Color(0xFFDFF6F4))
        ) {

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(start = 10.dp)
                ){
                    Spacer(Modifier.height(10.dp))
                    Text("Product Name : ${res.product_name}" , color = Color.Black , fontWeight = FontWeight.Bold, fontSize = 19.sp)
                    Spacer(Modifier.height(5.dp))
                    Text("Category: ${res.category}", color = Color.Black , fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
                    Spacer(Modifier.height(5.dp))
                    Text("Price: ₹ ${res.price}", color = Color.Black , fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
                    Spacer(Modifier.height(5.dp))
                    if (res.stock >= 5){
                        Text("Quantity: ${res.stock}", color = Color.Green , fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                    }
                    else{
                        Text("Quantity: ${res.stock}", color = Color.Red , fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                    }
                    Spacer(Modifier.height(10.dp))


                }

                Box (
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    val context = LocalContext.current
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 10.dp)
                            .clickable {
                                Toast.makeText(context, "${res.product_name} Deleted Successfully",Toast.LENGTH_SHORT).show()
                                viewModels.deleteSpecificProduct(
                                    productId = res.product_id.toString()
                                )
                            }
                    )

                }



            }

        }

    }

}