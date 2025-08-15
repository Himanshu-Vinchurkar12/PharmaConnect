package com.example.medicalstoreapp.screen.LogIn_SignUP_Screen

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalstoreapp.screen.nav.Routes
import com.example.medicalstoreapp.viewModel.MyViewModel

@Composable
fun SignUpScreen( viewModel: MyViewModel = hiltViewModel() , navController: NavController ) {

    val state = viewModel.createUser.collectAsState()

    val userName = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val  email= remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val pin_code = remember { mutableStateOf("") }
    val shope_name = remember { mutableStateOf("") }


    val context = LocalContext.current






    when{
        state.value.isLoading == true -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.value.error != null ->{
            Text(text = state.value.error.toString())
        }
        state.value.data != null -> {

            if (state.value.data != null) {
                LaunchedEffect(state.value.data) {
                    state.value.data?.let {
                        Toast.makeText(context, "Sign Up Successfully", Toast.LENGTH_SHORT).show()
                        navController.navigate(Routes.WaitingScreenRoutes)
                    }
                }

            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(brush = Brush.linearGradient(
            colors = listOf(Color.White, Color(0xFF77EADE)),
        )),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(start = 10.dp, top = 40.dp),
        ) {
            Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null, modifier = Modifier.padding(start = 5.dp).size(35.dp).clickable{
                navController.navigate(Routes.StartScreenRoutes)
            })
               Spacer(Modifier.width(70.dp))

           Column(){
               Text("SignUp", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(bottom = 5.dp))
               Text("Create your Account", modifier = Modifier.padding(bottom = 20.dp))

           }


        }




        Spacer(Modifier.height(5.dp))

      OutlinedTextField(
          value = userName.value,
          onValueChange = {
              userName.value = it
          },
          label = {
              Row {
                  Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
                  Spacer(Modifier.width(5.dp))
                  Text(text ="User Name", fontWeight = FontWeight.Bold )
              }
             },
          singleLine = true,
          shape = RoundedCornerShape(20.dp)
      )

        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value =email.value,
            onValueChange = {
                email.value = it
            },
            label = {
                Row {
                    Icon(imageVector = Icons.Rounded.Email, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text(text ="E-mail",fontWeight = FontWeight.Bold )
                }},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            shape = RoundedCornerShape(20.dp)
        )
        Spacer(Modifier.height(10.dp))
        var passwordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.Lock, contentDescription = null)
                    Spacer(Modifier.width(5.dp))
                    Text(text = "Password", fontWeight = FontWeight.Bold)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(0.8f),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = description)
                }
            }
        )
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = address.value,
            onValueChange = {
                address.value = it
            },
            label = {
                Row {
                    Icon(imageVector = Icons.Rounded.Phone, contentDescription = null)
                    Spacer(Modifier.width(5.dp))
                    Text(text = "Address", fontWeight = FontWeight.Bold)
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(20.dp)
        )
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = phoneNumber.value,
            onValueChange = {
                phoneNumber.value = it
            },
            label = {
                Row {
                    Icon(imageVector = Icons.Rounded.Phone, contentDescription = null)
                    Spacer(Modifier.width(5.dp))
                    Text(text ="Phone Number",fontWeight = FontWeight.Bold )
                }

                    },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            shape = RoundedCornerShape(20.dp)
        )
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = pin_code.value,
            onValueChange = {
                pin_code.value = it
            },
            label = {
                Row {
                    Icon(imageVector = Icons.Rounded.Place, contentDescription = null)
                    Spacer(Modifier.width(5.dp))
                    Text(text ="Pin Code" ,fontWeight = FontWeight.Bold )
                }

                    },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            shape = RoundedCornerShape(20.dp)
        )
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = shope_name.value,
            onValueChange = {
                shope_name.value = it
            },
            label = {
                Row {
                    Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null)
                    Spacer(Modifier.width(5.dp))
                    Text(text ="Shoppe Name" ,fontWeight = FontWeight.Bold )

                }

                    },
            singleLine = true,
            shape = RoundedCornerShape(20.dp)
        )
        Spacer(Modifier.height(25.dp))
        ElevatedButton(
            onClick = {
                if(userName.value.isEmpty() || password.value.isEmpty() || email.value.isEmpty() || address.value.isEmpty() || phoneNumber.value.isEmpty()  ){
                    Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                    return@ElevatedButton
                }
                else{

                    viewModel.createUser(
                        name = userName.value,
                        password = password.value,
                        email = email.value,
                        address = address.value,
                        phoneNumber = phoneNumber.value,
                        pin_code = pin_code.value,
                        shope_name = shope_name.value,)

                }

           },
            modifier = Modifier.size(width = 170.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(Color.Red),
            elevation = ButtonDefaults.buttonElevation(8.dp),
        )
        {

           Text("SignUp", fontSize = 18.sp)
            Spacer(Modifier.width(5.dp))

        }

        Spacer(Modifier.height(5.dp))
        Text(text = "OR", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)

        Spacer(Modifier.height(1.dp))
        Text(text = "LogIn.." , fontSize = 20.sp , color = Color.Blue, fontWeight = FontWeight.SemiBold , modifier = Modifier.clickable{
            navController.navigate(Routes.LogInScreenRoutes)
        })






    }

}