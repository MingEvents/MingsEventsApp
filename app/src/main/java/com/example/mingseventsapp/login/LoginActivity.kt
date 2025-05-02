package com.example.mingseventsapp.login

import LoginViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mingseventsapp.R


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           putBackground {
               loginScreen(
                   onNavigateToRegister = {
                       // Logearse nuevo usuario
                   }
               )
           }
        }
    }
}

@Composable
fun putBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD7E9FC))
    ) {
        content()
    }
}

@Composable
fun loginScreen(viewModel: LoginViewModel = viewModel(),
                onNavigateToRegister: () -> Unit) {
    val state by viewModel.formState.collectAsState()

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Color(0xFF14296F)
            )
        }
    } else {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(400.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = state.email,
            onValueChange = { viewModel.setEmail(it) },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.width(350.dp).background(Color.Transparent),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = state.password,
            onValueChange = { viewModel.setPassword(it) },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.width(350.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            )
        )

        Button(
            onClick = { viewModel.login() },
            modifier = Modifier
                .width(250.dp)
                .padding(top = 100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF14296F),
                contentColor = Color.White
            )
        ) {
            Text(text = "Sign in")
        }

        Text(
            text = "¿You dont have account? Login",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable {
                    onNavigateToRegister()
                }
        )
    }
  }
}
