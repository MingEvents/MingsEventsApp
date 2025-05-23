package com.example.mingseventsapp.layouts.pages.Profile

import LoginViewModel
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mingseventsapp.R
import com.example.mingseventsapp.Routes
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.model.user.User

@Composable
fun ProfilePage(navController: NavHostController) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val context = LocalContext.current

    // Estados locales para los campos del formulario
    var emailState by remember { mutableStateOf(UserLogged.user.email) }
    var nameState by remember { mutableStateOf(UserLogged.user.name) }
    var secondNameState by remember { mutableStateOf(UserLogged.user.second_name) }
    var phoneState by remember { mutableStateOf(UserLogged.user.phone.toString()) }
    var passwordState by remember { mutableStateOf(UserLogged.user.password) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd7e9fc)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 40.dp, horizontal = 0.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Profile",
                fontSize = 40.sp,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
            Divider(color = Color.Black, thickness = 1.dp)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 150.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                AsyncImage(
                    model = "https://www.iconpacks.net/icons/2/free-user-icon-3296-thumb.png ",
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .offset(x = 15.dp, y = (-15).dp)
                        .clip(CircleShape)
                        .shadow(elevation = 6.dp)
                        .background(Color(0xFF14296F))
                        .padding(8.dp)
                        .clickable {
                            UserLogged.user = User()
                            navController.navigate(Routes.LOGIN)
                            Toast.makeText(context, "Login closed", Toast.LENGTH_SHORT).show()
                        }
                ) {
                    AsyncImage(
                        model = "https://cdn-icons-png.flaticon.com/512/1828/1828491.png ",
                        contentDescription = "Logout",
                        modifier = Modifier.size(28.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            // Campos del formulario (sin cambios)
            TextField(
                value = emailState,
                onValueChange = { emailState = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier
                    .width(350.dp)
                    .background(Color.Transparent)
                    .padding(bottom = 20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                )
            )

            TextField(
                value = nameState,
                onValueChange = { nameState = it },
                label = { Text("Name") },
                singleLine = true,
                modifier = Modifier
                    .width(350.dp)
                    .background(Color.Transparent)
                    .padding(bottom = 20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                )
            )

            TextField(
                value = secondNameState,
                onValueChange = { secondNameState = it },
                label = { Text("Second name") },
                singleLine = true,
                modifier = Modifier
                    .width(350.dp)
                    .background(Color.Transparent)
                    .padding(bottom = 20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                )
            )

            TextField(
                value = phoneState,
                onValueChange = { phoneState = it },
                label = { Text("Phone") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .width(350.dp)
                    .background(Color.Transparent)
                    .padding(bottom = 20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                )
            )

            TextField(
                value = passwordState,
                onValueChange = { passwordState = it },
                label = { Text("Password") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .width(350.dp)
                    .background(Color.Transparent)
                    .padding(bottom = 20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                )
            )

            Button(
                onClick = {
                    // Aquí actualizamos UserLogged.user solo cuando se hace clic en Save
                    UserLogged.user = UserLogged.user.copy(
                        email = emailState,
                        name = nameState,
                        second_name = secondNameState,
                        phone = phoneState.toIntOrNull() ?: UserLogged.user.phone,
                        password = passwordState
                    )
                    val userViewModel = UserViewModel()
                    userViewModel.updateUser(UserLogged.user.copy())
                    Toast.makeText(context, "Profile updated", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .width(screenWidth - 40.dp)
                    .padding(top = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF14296F),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Save")
            }
        }
    }
}
