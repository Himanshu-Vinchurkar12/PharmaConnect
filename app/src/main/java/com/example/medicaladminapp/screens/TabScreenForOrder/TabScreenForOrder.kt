package com.example.medicaladminapp.screens.TabScreenForOrder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medicaladminapp.screens.TabScreenForUsers.ApprovedUserScreen
import com.example.medicaladminapp.screens.TabScreenForUsers.PendingUserScreen
import com.example.medicaladminapp.screens.nav.Routes
import com.example.medicaladminapp.viewModel.MyViewModels
import kotlinx.coroutines.launch

@Composable
fun TabScreenForOrder(
    viewModels: MyViewModels = hiltViewModel(),
    navHostController: NavHostController
){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val orderlist = listOf(
        TabItems(
            title = "Approved Order",
            icon = Icons.Outlined.CheckCircle,
            filledicon = Icons.Filled.CheckCircle
        ),

        TabItems(
            title = "Pending Order",
            icon = Icons.Outlined.Schedule,
            filledicon = Icons.Filled.Schedule
        )
    )

    val PagerState = rememberPagerState(pageCount = { orderlist.size })

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.White, Color(0xFF5EE5DA))
                )
            )
    ) {

        Spacer(Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth().height(75.dp)
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        navHostController.navigate(Routes.HomeScreenRoutes)
                    }
                    .size(24.dp)
            )
            Spacer(Modifier.width(15.dp))
            Text(
                text = "Order Tab",
                fontSize = (screenWidth.value * 0.08).sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
        TabRow(
            selectedTabIndex = PagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.White, Color(0xFFA1DAD5))
                    )
                )
                .padding(top = 20.dp)
        ) {
            orderlist.forEachIndexed { index, tabItems ->
                Tab(
                    modifier = Modifier.fillMaxWidth().background(color = Color.White),
                    selected = PagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            PagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                if (PagerState.currentPage == index) {
                                    tabItems.filledicon
                                } else {
                                    tabItems.icon
                                },
                                contentDescription = null,
                                modifier = Modifier.size(35.dp)
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(tabItems.title, fontSize = 18.sp)
                        }
                    },
                    selectedContentColor = Color(0xFF0A7A6A),
                    unselectedContentColor = Color.Gray
                )

            }
        }
        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)


        HorizontalPager(state = PagerState) {
            when (it) {
                0 -> ApprovedOrderScreen(navHostController = navHostController)
                1 -> PendingOrderScreen(navHostController = navHostController)

            }
        }

    }
}


data class TabItems(
    val title: String,
    val icon: ImageVector,
    val filledicon: ImageVector
)