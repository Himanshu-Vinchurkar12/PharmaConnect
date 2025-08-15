package com.example.medicaladminapp.screens.TabScreenForProduct

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medicaladminapp.screens.nav.Routes
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun TabScreenForProduct(navHostController: NavHostController = rememberNavController()) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val dynamicFontSize = (screenWidth.value * 0.05).sp

    val tabs = listOf(
        TabItemsP(
            title = "All Tablets",
            icon = Icons.Default.Medication,
            filledicon = Icons.Filled.Medication
        ),
        TabItemsP(
            title = "All Syrups",
            icon = Icons.Default.MedicalServices,
            filledicon = Icons.Filled.MedicalServices
        ),
        TabItemsP(
            title = "All Antiseptic",
            icon = Icons.Default.MedicalInformation,
            filledicon = Icons.Filled.MedicalInformation
        ),
        TabItemsP(
            title = "All Equipments",
            icon = Icons.Default.Engineering,
            filledicon = Icons.Filled.Engineering
        )
    )

    val PagerState = rememberPagerState(pageCount = { tabs.size })

    val scope = rememberCoroutineScope()


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Routes.AddProductScreenRoutes)
                },
                modifier = Modifier.padding(bottom = 50.dp),
                containerColor = Color.White,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
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
                    text = "Products",
                    fontSize = (screenWidth.value * 0.08).sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

            ScrollableTabRow(
                selectedTabIndex = PagerState.currentPage,
                edgePadding = 0.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.White, Color(0xFFA1DAD5))
                        )
                    )
                    .padding(top = 10.dp),
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        modifier = Modifier
                            .background(Color.White)
                                .height(70.dp),
                        selected = PagerState.currentPage == index,
                        onClick = {
                            scope.launch { PagerState.animateScrollToPage(index) }
                        },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
                            ) {
                                Icon(
                                    imageVector = if (PagerState.currentPage == index) tab.filledicon else tab.icon,
                                    contentDescription = tab.title,
                                    modifier = Modifier.size(screenWidth * 0.05f)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    text = tab.title,
                                    fontSize = (screenWidth.value * 0.05).sp
                                )
                            }
                        },
                        selectedContentColor = Color(0xFF185E4E),
                        unselectedContentColor = MaterialTheme.colorScheme.onSurface
                    )
                }
            }


            HorizontalPager(state = PagerState) {
                when (it) {
                    0 -> TabletsScreens(navHostController = navHostController)
                    1 -> SyrupsScreens(navHostController = navHostController)
                    2 -> AntisepticScreens(navHostController = navHostController)
                    3 -> EquipmentsScreens(navHostController = navHostController)

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