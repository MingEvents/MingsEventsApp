package com.example.mingseventsapp.layouts.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
                model = "https://www.iconpacks.net/icons/2/free-user-icon-3296-thumb.png", // reemplaza con el campo real de URL de imagen
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .padding(bottom = 20.dp),
                contentScale = ContentScale.Fit
                      )
            TextField(
                value = UserLogged.user.email,
                onValueChange = { UserLogged.user.email = it },
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
                value = UserLogged.user.name,
                onValueChange = { UserLogged.user.name = it },
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
                value = UserLogged.user.second_name,
                onValueChange = { UserLogged.user.second_name = it },
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
                value = UserLogged.user.phone.toString(),
                onValueChange = { UserLogged.user.phone = it.toInt() },
                label = { Text("Phone") },
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
                value = UserLogged.user.password,
                onValueChange = { UserLogged.user.password = it },
                label = { Text("Password") },
                singleLine = true,
                modifier = Modifier
                    .width(350.dp)
                    .background(Color.Transparent)
                    .padding(bottom = 20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                                                 )
                     )

            Button(
                onClick = { /*actualizar perfil*/ },
                modifier = Modifier
                    .width(screenWidth - 40.dp)
                    .padding(top = 20 .dp),
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