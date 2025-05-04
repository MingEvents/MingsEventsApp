package com.example.mingseventsapp
import LoginViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mingseventsapp.layouts.login.LoginScreen
import com.example.mingseventsapp.layouts.login.PutBackground
import com.example.mingseventsapp.layouts.menu.HomeMenuContent


@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            val viewModel: LoginViewModel = viewModel()

            PutBackground {
                LoginScreen(
                    viewModel = viewModel,
                    navController = navController,
                    onNavigateToRegister = {
                        navController.navigate(Routes.REGISTER)
                    }
                )
            }
        }
        composable(Routes.MENU) {
            HomeMenuContent()
        }
        composable(Routes.REGISTER) {
            // Aquí puedes agregar tu RegisterScreen o lo que desees mostrar en esa ruta
        }
    }
}
