package com.example.medicaladminapp.screens.TabScreenForUsers

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.rounded.Sort
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.example.medicaladminapp.models.UsersModelsItem
import com.example.medicaladminapp.screens.nav.Routes
import com.example.medicaladminapp.viewModel.MyViewModels

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ApprovedUserScreen(
    navHostController: NavHostController,
    viewModels: MyViewModels = hiltViewModel()
) {

    val state = viewModels.getAllUsers.collectAsState()
    var text = remember { mutableStateOf("") }
    var isSortByName = remember { mutableStateOf(true) }
    val dropDown = remember { mutableStateOf(false) }

    var context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModels.getAllUsers()
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = state.value.isError.toString())
            }
        }

        state.value.isdata != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color.White, Color(0xFF5EE5DA))
                        )
                    )
                    .padding(top = 5.dp)
            ) {

                Spacer(Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                {
                    OutlinedTextField(
                        value = text.value,
                        onValueChange = { text.value = it },
                        placeholder = { Text(text = "Search user..") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.List,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        dropDown.value = true
                                    }
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.DarkGray,
                            unfocusedBorderColor = Color.LightGray
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )
                }

                // Box to Wrap  DropdownMenu for proper position
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    contentAlignment = Alignment.TopStart
                )
                {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .wrapContentWidth()
                    ) {
                        // DropdownMenu Positioned Properly
                        AnimatedVisibility(
                            visible = dropDown.value,
                            enter = scaleIn(tween(300)) + slideInVertically(
                                initialOffsetY = { -40 },
                                animationSpec = tween(300)
                            ),
                            exit = scaleOut(tween(300)) + slideOutVertically(
                                targetOffsetY = { -40 },
                                animationSpec = tween(300)
                            )
                        )
                        {
                            // DropdownMenu Beside Icon
                            DropdownMenu(
                                expanded = dropDown.value,
                                onDismissRequest = { dropDown.value = false },
                                modifier = Modifier
                                    .width(150.dp)
                                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                                    .padding(0.dp) // No extra padding for Dropdown
                            ) {

                                Column(modifier = Modifier.padding(vertical = 4.dp))
                                {
                                    // Sort by Name
                                    DropdownMenuItem(
                                        text = { Text("Sort by Name") },
                                        onClick = {
                                            dropDown.value = false
                                            isSortByName.value = true
                                            Toast.makeText(
                                                context,
                                                "Sorted by Name",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                        },
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )

                                    // Divider for separation
                                    HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

                                    // Sort by Date
                                    DropdownMenuItem(
                                        text = { Text("Sort by Date") },
                                        onClick = {
                                            dropDown.value = false
                                            isSortByName.value = false
                                            Toast.makeText(
                                                context,
                                                "Sorted by Date",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                        },
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )
                                }
                            }
                        }
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                val getApprovedUsers = state.value.isdata

                if (getApprovedUsers != null) {
                    val filteredUser = getApprovedUsers.filter {
                        it.name.contains(text.value.trim(), ignoreCase = true)
                    }.let { list ->
                        if (isSortByName.value) list.sortedBy { it.name }
                        else list.sortedBy { it.date_of_account_creation }
                    }
                    if (filteredUser.isNotEmpty()){
                        Text(
                            if (isSortByName.value) "Sorted by Name" else "Sorted by Date",
                            color = Color.Gray,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                        )
                    }
                    if (filteredUser.isEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.no_contact_found),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(160.dp)
                                    .clip(shape = CircleShape)
                                    .shadow(elevation = 2.dp)
                            )
                            Spacer(Modifier.height(25.dp))
                            Text(
                                text = "No User Found",
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    } else {
                        AnimatedContent(
                            targetState = filteredUser,
                            transitionSpec = {
                                fadeIn(animationSpec = tween(300)) + slideInVertically(
                                    initialOffsetY = { it }) togetherWith
                                        fadeOut(animationSpec = tween(300)) + slideOutVertically(
                                    targetOffsetY = { it })
                            },
                            label = "UserListAnimation"
                        )
                        { targetList ->
                            LazyColumn {
                                items(targetList) {
                                    AllapprovedUser(
                                        res = it,
                                        navHostController = navHostController,
                                        viewModels = viewModels
                                    )
                                }

                                item {
                                    Spacer(Modifier.height(100.dp))
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
fun AllapprovedUser(
    res: UsersModelsItem,
    navHostController: NavHostController,
    viewModels: MyViewModels
) {

    if (res.is_Approved.toInt() == 1) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clickable {
                    navHostController.navigate(
                        Routes.DetailScreenRoutes(
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
                    viewModels.getAllUsers()
                },
            colors = CardDefaults.cardColors(containerColor = Color(0xFFDFF6F4))
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 15.dp)
                ) {
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "User Name : ${res.name}",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Email: ${res.email}",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Shoppe Name: ${res.shope_name}",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                    Spacer(Modifier.height(6.dp))
                    if (res.is_Approved == 0) {
                        Text(
                            "Approve status: Block",
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    } else {
                        Text(
                            "Approve status: Approved",
                            color = Color.Green,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }

                    Spacer(Modifier.height(6.dp))

                }
                Spacer(Modifier.height(10.dp))


            }

        }

    }

}