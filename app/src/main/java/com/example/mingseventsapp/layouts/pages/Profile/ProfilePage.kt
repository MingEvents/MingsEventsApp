package com.example.mingseventsapp.layouts.pages.Profile

import LoginViewModel
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.model.user.User

@Composable
fun ProfilePage() {
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
            horizontalAlignment = Alignment.Start,
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

            AsyncImage(
                model = "https://www.iconpacks.net/icons/2/free-user-icon-3296-thumb.png",
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .padding(bottom = 20.dp),
                contentScale = ContentScale.Fit
                      )

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

                    userViewModel.updateUser(UserLogged.user.copy())                     // Opcional: mostrar mensaje de éxito
                    Toast.makeText(context, "Perfil actualizado", Toast.LENGTH_SHORT).show()


                    // Aquí puedes llamar a tu API para guardar los cambios
                    // Ejemplo: updateUserToServer(UserLogged.user)
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
