package com.example.medicaladminapp.screens.TabScreenForOrder

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
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
import com.example.medicaladminapp.screens.nav.Routes.DetailScreenForOrderRoutes
import com.example.medicaladminapp.viewModel.MyViewModels
import java.nio.file.WatchEvent

@Composable
fun DetailScreenForOrder(
    res: DetailScreenForOrderRoutes,
    viewModels: MyViewModels = hiltViewModel(),
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.White, Color(0xFF9AD9D4))
                )
            )
    ) {
        Spacer(Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier
                    .size((screenWidth.value * 0.07).dp)
                    .clickable { navHostController.popBackStack() }
            )
            Spacer(Modifier.width(15.dp))
            Column(
                modifier = Modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "Order Details",
                    fontSize = (screenWidth.value * 0.08).sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "#" + res.order_id!!.take(7) + "...",
                    fontSize = (screenWidth.value * 0.05).sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.LightGray
                )

            }

        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(), color = Color.LightGray, thickness = 1.dp
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(12.dp))
                .padding(start = 16.dp)
                .background(
                   color = if(res.is_Approved == 1) Color(0xFF78FFAD) else Color(0xFFE3C9C9),
                    shape = RoundedCornerShape(12.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(16.dp))
            Text(
                text = if (res.is_Approved == 1) "Approved" else "Pending",
                fontSize = (screenWidth.value * 0.05).sp,
                fontWeight = FontWeight.SemiBold,
                color = if (res.is_Approved == 1) Color(0xFF043F3A) else Color.Red
            )
            Spacer(Modifier.width(16.dp))
        }

        Spacer(Modifier.height(20.dp))

        LazyColumn{

            item{
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(screenHeight * 0.07f)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFF6AE351),
                                            Color(0xFF075248)
                                        ) ),),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Text(
                                text = "Product Information",
                                fontSize = (screenWidth.value * 0.06).sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Product Name:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            Text(
                                text = "${res.product_name}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )

                        }
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Category:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            Text(
                                text = "${res.category}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )

                        }
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Unit Price:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            Text(
                                text = " ₹ ${res.price}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )

                        }
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Quantity:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            Text(
                                text = "${res.quantity} units",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )

                        }
                        Spacer(Modifier.height(8.dp))
                        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray, thickness = 1.dp)
                        Spacer(Modifier.height(8.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Product ID:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            Text(
                                text = "${res.product_id?.take(7)} ... ",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )

                        }
                        Spacer(Modifier.height(8.dp))
                    }

                }
            }
            item{
                Spacer(Modifier.height(20.dp))
            }
            item{
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(screenHeight * 0.07f)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFF00C6FF),
                                            Color(0xFF0072FF)
                                        ) ),),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Text(
                                text = "Order Information",
                                fontSize = (screenWidth.value * 0.06).sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Order Date:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            Text(
                                text = "${res.date_of_order_creation}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )

                        }
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Order Status:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            Row(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(12.dp))
                                    .padding(start = 16.dp)
                                    .background(
                                        color = if(res.is_Approved == 1) Color(0xFF78FFAD) else Color(0xFFE8A7A7),
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Spacer(Modifier.width(16.dp))
                                Text(
                                    text = if (res.is_Approved == 1) "Approved" else "Pending",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (res.is_Approved == 1) Color(0xFF043F3A) else Color.Red
                                )
                                Spacer(Modifier.width(16.dp))
                            }


                        }
                        Spacer(Modifier.height(8.dp))
                        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray, thickness = 1.dp)
                        Spacer(Modifier.height(8.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Product ID:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                            )
                            Spacer(Modifier.height(1.dp))
                            Text(
                                text = "${res.product_id}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                            )

                        }
                        Spacer(Modifier.height(8.dp))
                        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray, thickness = 1.dp)
                        Spacer(Modifier.height(8.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Order Notes:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                            )
                            Spacer(Modifier.height(1.dp))
                            Text(
                                text = "${res.massege}",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                            )

                        }
                        Spacer(Modifier.height(8.dp))
                    }

                }
            }
            item{
                Spacer(Modifier.height(20.dp))
            }
            item{
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(screenHeight * 0.07f)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFFC0469F),
                                            Color(0xFF0A5088)
                                        ) ),),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Text(
                                text = "Customer Information",
                                fontSize = (screenWidth.value * 0.06).sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Customer Name:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            Text(
                                text = "${res.user_name}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )

                        }
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Shop Name:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            Text(
                                text = "${res.shope_name}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )


                        }
                        Spacer(Modifier.height(8.dp))
                        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray, thickness = 1.dp)
                        Spacer(Modifier.height(8.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Customer ID:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                            )
                            Spacer(Modifier.height(1.dp))
                            Text(
                                text = "${res.user_id}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                            )

                        }
                        Spacer(Modifier.height(8.dp))
                    }

                }
            }
            item{
                Spacer(Modifier.height(20.dp))
            }
            item{
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(screenHeight * 0.07f)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFF0ECC53),
                                            Color(0xFF06796F)
                                        ) ),),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))
                            Text(
                                text = "Order Summary",
                                fontSize = (screenWidth.value * 0.06).sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Unit Price:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            Text(
                                text = "₹ ${res.price}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )

                        }
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Quantity:",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            Text(
                                text = "${res.quantity}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )

                        }
                        Spacer(Modifier.height(8.dp))
                        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray, thickness = 1.dp)
                        Spacer(Modifier.height(8.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        )
                        {
                            Text(
                                text = "Total Amount:",
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            Text(
                                text = "₹ ${res.total_price}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF03675D),
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )

                        }
                        Spacer(Modifier.height(10.dp))
                    }

                }
            }
            item{
                Spacer(Modifier.height(20.dp))
            }
            item{
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    )
                ){
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Quick Actions ",
                                fontSize = (screenWidth.value * 0.05).sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        }

                        Spacer(Modifier.height(20.dp))

//                        ElevatedButton(
//                            onClick = {},
//                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
//                            colors = ButtonDefaults.elevatedButtonColors(
//                                containerColor = Color(0xFF03675D),
//                            ),
//                            elevation = ButtonDefaults.elevatedButtonElevation(
//                                defaultElevation = 5.dp,
//                                pressedElevation = 10.dp
//                            )
//                        ) {
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.Center
//                            ) {
//                                Text(
//                                    text = "Edit Order",
//                                    fontSize = (screenWidth.value * 0.05).sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = Color.White
//                                    )
//                            }
//                        }


                        ElevatedButton(
                            onClick = {
                                if (res.is_Approved == 1){
                                    viewModels.approveOrder(
                                        orderId = res.order_id,
                                        is_Approved = 0
                                    )
                                    navHostController.popBackStack()
                                    Toast.makeText(context, " Order Rejected Successfully", Toast.LENGTH_SHORT).show()
                                }
                                else{
                                    viewModels.approveOrder(
                                        orderId = res.order_id,
                                        is_Approved = 1
                                    )
                                    navHostController.popBackStack()
                                    Toast.makeText(context, " Order Approved Successfully", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = if(res.is_Approved == 1) Color(0xFFD31919) else   Color(0xFF03675D),
                            ),
                            elevation = ButtonDefaults.elevatedButtonElevation(
                                defaultElevation = 5.dp,
                                pressedElevation = 10.dp
                            )
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = if(res.is_Approved == 1) "Reject Order" else "Approved Order",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }

                        Spacer(Modifier.height(8.dp))

                        ElevatedButton(
                            onClick = {
                                Toast
                                    .makeText(
                                        context,
                                        "${res.shope_name}'s Order Deleted Successfully",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                                viewModels.deleteSpecificOrder(
                                    orderId = res.order_id.toString()
                                )
                                navHostController.popBackStack()
                            },
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = Color(0xFFD31919),
                            ),
                            elevation = ButtonDefaults.elevatedButtonElevation(
                                defaultElevation = 5.dp,
                                pressedElevation = 10.dp
                            )
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Delete Order",
                                    fontSize = (screenWidth.value * 0.05).sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }

                        Spacer(Modifier.height(10.dp))

                    }
                }
            }

            item{
                Spacer(Modifier.height(20.dp))
            }


        }



    }

}