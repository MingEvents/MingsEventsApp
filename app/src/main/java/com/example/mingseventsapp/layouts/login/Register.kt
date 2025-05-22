package com.example.mingseventsapp.layouts.login

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.mingseventsapp.R
import com.example.mingseventsapp.Routes
import coil.compose.rememberAsyncImagePainter
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = viewModel()
                  ) {
    val state by viewModel.formState.collectAsState()
    val scope = rememberCoroutineScope()

    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.navigateToMenu.collect {
            navController.navigate(Routes.MENU + "/0") {
                popUpTo(Routes.LOGIN) { inclusive = true }
                isLoading = false
            }
        }
    }

    if (isLoading) {
        showLoadingDialog(isLoading = remember { mutableStateOf(true) })
    } else {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
                                                    ) { uri: Uri? ->
        profileImageUri = uri
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color(0xFFD7E9FC))
            .fillMaxSize()
          ) {
        Image(
            painter = painterResource(id = R.drawable.login_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
             )

        Text(
            text = "Registro",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 16.dp)
            )

        // Campo Nombre
        TextField(
            value = state.name,
            onValueChange = { viewModel.setName(it) },
            label = { Text("Nombre") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
                                             )
                 )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Apellido
        TextField(
            value = state.lastName,
            onValueChange = { viewModel.setLastName(it) },
            label = { Text("Apellido") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
                                             )
                 )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Email
        TextField(
            value = state.email,
            onValueChange = { viewModel.setEmail(it) },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
                                             )
                 )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.phone,
            onValueChange = { viewModel.setPhone(it) },
            label = { Text("Numero") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
                                             )
                 )

        Spacer(modifier = Modifier.height(16.dp))

        // Contraseña
        TextField(
            value = state.password,
            onValueChange = { viewModel.setPassword(it) },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
                                             )
                 )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirmar contraseña
        TextField(
            value = state.confirmPassword,
            onValueChange = { viewModel.setConfirmPassword(it) },
            label = { Text("Confirmar contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
                                             )
                 )

        Spacer(modifier = Modifier.height(16.dp))

        // Foto de perfil
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable {
                    launcher.launch("image/*")
                }
                .border(2.dp, Color.Gray, CircleShape)
                .padding(4.dp),
            contentAlignment = Alignment.Center
           ) {
            if (profileImageUri == null) {
                Text(text = "Foto", fontSize = 20.sp)
            } else {
                Image(
                    painter = rememberAsyncImagePainter(profileImageUri),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                     )

                // profileImageUri?.let { ImageBitmap.uriPainter(it) },
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                isLoading = true
                viewModel.register()
            },
            modifier = Modifier
                .width(250.dp)
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF14296F),
                contentColor = Color.White
                                                )
              ) {
            Text(text = "Registrarse")
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "¿Ya tienes cuenta? Iniciar sesión",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                navController.navigate(Routes.LOGIN)
            }
            )
    }

    }

    if (state.isLoading) {
        Dialog(onDismissRequest = {}) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFF14296F))
            }
        }
    }

    if (state.error != null) {
        RegisterErrorDialog(errorMessage = state.error!!, onDismiss = { viewModel.clearError() })
    }
}

@Composable
fun RegisterErrorDialog(
    errorMessage: String,
    onDismiss: () -> Unit
                       ) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Registro - Error") },
        text = { Text(text = errorMessage) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Aceptar")
            }
        }
               )
}

@Composable
fun showLoadingDialog(isLoading: MutableState<Boolean>) {
    Dialog(
        onDismissRequest = { isLoading.value = false }
          ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
           ) {
            CircularProgressIndicator(
                color = Color(0xFF14296F)
                                     )
        }
    }
}