package com.example.medicalstoreapp.screen.TabScreenForProduct

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medicalstoreapp.R
import com.example.medicalstoreapp.screen.nav.Routes
import com.example.medicalstoreapp.viewModel.MyViewModel
import kotlinx.coroutines.launch

@Composable
fun TabScreenForProduct(
    navHostController: NavHostController = rememberNavController(),
    viewModel: MyViewModel = hiltViewModel(),
    userId: String
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getSpecificUsers(user_id = userId)
    }

    val getSpecificUsers = viewModel.getSpecificUsers.collectAsState()

    when {
        getSpecificUsers.value.isLoading -> {
            CircularProgressIndicator()
        }

        getSpecificUsers.value.isError != null -> {
            Log.d("TAG", "GetUser: ${getSpecificUsers.value.isError}")
            Toast.makeText(
                LocalContext.current,
                getSpecificUsers.value.isError.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }

        getSpecificUsers.value.isData != null -> {
            val user = getSpecificUsers.value.isData

            Log.d("TAG", "GetUser: $user")


        }

    }
    val tabs = listOf(
        TabItemsP(
            title = "All Tablets",
            icon = Icons.Default.Medication,
            filledicon = Icons.Filled.Medication
        ),
        TabItemsP(
            title = "All Syrup",
            icon = Icons.Default.MedicalServices,
            filledicon = Icons.Filled.MedicalServices
        ),
        TabItemsP(
            title = "All Antiseptic",
            icon = Icons.Default.MedicalInformation,
            filledicon = Icons.Filled.MedicalInformation
        ),
    )

    val PagerState = rememberPagerState(pageCount = { tabs.size })

    val scope = rememberCoroutineScope()

    Scaffold(
    )
    {
        it
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.White, Color(0xFF5EE5DA))
                    )
                )
        )
        {
            Spacer(Modifier.height(60.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back icon at the start
                Image(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(start = 15.dp)
                        .clickable {
                            navHostController.navigate(
                                Routes.HomeScreenRoutes(user_id = userId)
                            )
                        }
                )

                // This Box takes up the rest of the space and centers the text inside it
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Medicines",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Spacer to balance the layout, same width as the back icon + padding
                Spacer(modifier = Modifier.width(65.dp))
            }


            Spacer(Modifier.height(10.dp))

            ScrollableTabRow(
                selectedTabIndex = PagerState.currentPage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 10.dp)
                    .clip(shape = RoundedCornerShape(20.dp)),
                containerColor = Color(0xFFFFFFFF),
            ) {
                tabs.forEachIndexed { index, TabItemsP ->
                    Tab(
                        modifier = Modifier.fillMaxWidth(),
                        selected = PagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                PagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Row(
                                modifier = Modifier.padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Absolute.SpaceBetween
                            ) {
                                Icon(
                                    if (PagerState.currentPage == index) {
                                        TabItemsP.filledicon

                                    } else {
                                        TabItemsP.icon

                                    },
                                    contentDescription = null
                                )
                                Spacer(Modifier.width(5.dp))
                                Text(TabItemsP.title, modifier = Modifier.padding(end = 10.dp))
                            }
                        },
                        selectedContentColor = Color(0xFF0A423B),
                        unselectedContentColor = Color.Black
                    )

                }
            }

            HorizontalPager(state = PagerState) {
                when (it) {
                    0 -> TabletsScreens(navHostController = navHostController, userId = userId)
                    1 -> SyrupsScreens(navHostController = navHostController, userId = userId)
                    2 -> AntisepticScreens(navHostController = navHostController, userId = userId)

                }
            }
        }

    }


}


data class TabItemsP(
    val title: String,
    val icon: ImageVector,
    val filledicon: ImageVector
)