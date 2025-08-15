package com.example.medicalstoreapp.screen


import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.medicalstoreapp.R
import com.example.medicalstoreapp.screen.nav.Routes
import com.example.medicalstoreapp.viewModel.MyViewModel
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    user_id: String? = null,
    navController: NavHostController,
    viewModel: MyViewModel = hiltViewModel()
) {

    val user = viewModel.getSpecificUsers.collectAsState()

    LaunchedEffect(user_id) {
        user_id?.let {
            viewModel.getSpecificUsers(it)
            Log.d("TAG", "HomeScreen 1: $user_id")
        }
    }

    Log.d("TAG", "HomeScreen 2: ${user.value.isData}")

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp



    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        it
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.White, Color(0xFF77EADE))
                    )
                )
                .padding(it)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        "Hello,",
                        fontSize = (screenWidth.value * 0.07).sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp)
                    )
                    Text(
                        "Costumer",
                        fontSize = (screenWidth.value * 0.08).sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 20.dp, top = 5.dp)
                    )

                }

                Spacer(Modifier.width(50.dp))
                Image(
                    painter = painterResource(R.drawable.ic_notification),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.profile1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(95.dp)
                        .align(Alignment.CenterVertically)
                        .clip(shape = RoundedCornerShape(100.dp))
                        .clickable {
                            Log.d("TAG", "HomeScreen 3: $user_id")
                            navController.navigate(Routes.ProfileScreenRoutes(user_id = user_id))
                        }
                )

            }


            val pagerimage = listOf(
                R.drawable.discount,
                R.drawable.discount1,
                R.drawable.monsoon_scale3
            )
            val pagerState = rememberPagerState(pageCount = {
                pagerimage.size
            })

            LaunchedEffect(pagerState) {
                while (true) {
                    delay(1000L)
                    if (pagerState.currentPage == pagerimage.size - 1) {
                        pagerState.animateScrollToPage(0)
                    } else {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }

            Spacer(Modifier.height(25.dp))

            Text(
                text = "Latest Discount", modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                style = TextStyle(
                    fontSize = (screenWidth.value * 0.08).sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    letterSpacing = 2.sp,
                    lineHeight = 20.sp
                )
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .width(screenWidth * 0.95f) // 90% of screen width
                    .height(screenHeight * 0.25f) // 25% of screen height
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)

            ) { it ->
                val pageOffset =
                    (pagerState.currentPage - it) + pagerState.currentPageOffsetFraction
                val pagerimagesize by animateFloatAsState(
                    targetValue = if (pageOffset != 0.0f) {
                        0.75f
                    } else 1f,
                    animationSpec = tween(durationMillis = 300), label = ""
                )
                Image(
                    painter = painterResource(id = pagerimage[it]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(25.dp))
                        .graphicsLayer {
                            scaleX = pagerimagesize
                            scaleY = pagerimagesize
                        },
                    contentScale = ContentScale.Crop
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentHeight()
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val isSelected = pagerState.currentPage == iteration

                    // Animated size and color for the indicator
                    val width by animateDpAsState(
                        targetValue = if (isSelected) screenWidth * 0.08f else screenWidth * 0.025f,
                        label = "indicatorWidth"
                    )

                    val height: Dp by animateDpAsState(
                        targetValue = screenHeight * 0.012f,
                        label = "indicatorHeight"
                    )

                    val color by animateColorAsState(
                        targetValue = if (isSelected) Color(0xFF0D5248) else Color.LightGray,
                        label = "indicatorColor"
                    )

                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .clip(if (isSelected) RoundedCornerShape(30.dp) else CircleShape)
                            .background(color)
                            .size(width = width, height = height)
                    )
                }
            }

            Spacer(Modifier.height(50.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "What u Need ?",
                    style = TextStyle(
                        fontSize = (screenWidth.value * 0.08).sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        letterSpacing = 2.sp,
                        lineHeight = 20.sp
                    )
                )

            }

            Spacer(Modifier.height(20.dp))

            val categoryimage = remember {
                listOf(
                    R.drawable.medicineicon,
                    R.drawable.equipmenticon

                )
            }
            val categoryname = remember {
                listOf(
                    "Medicines", "Equipments"
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp),
                contentPadding = PaddingValues(10.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(2) { index ->

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f) // Makes it a perfect square
                                .clickable {
                                    if (index == 0) {
                                        navController.navigate(Routes.TabScreenForProduct(user_id = user_id))
                                    } else {
                                        navController.navigate(Routes.EquipmentScreenRoutes(user_id = user_id))
                                    }
                                },
                            shape = RoundedCornerShape(20.dp),
                            border = BorderStroke(4.dp, color = Color.White),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF0E6559)),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = categoryimage[index]),
                                    contentDescription = null,
                                    modifier = Modifier.size(80.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp)) // space between card and text

                        Text(
                            text = categoryname[index],
                            fontSize = (screenWidth.value * 0.05).sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color.Black
                        )
                    }
                }
            }


        }


    }

}