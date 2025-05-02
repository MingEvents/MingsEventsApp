package com.example.mingseventsapp
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mingseventsapp.login.loginScreen


@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
       // composable(Routes.LOGIN) { loginScreen(...)}
       // composable(Routes.REGISTER) { registerScreen (...) }
        //  composable(Routes.MENU) { MenuScreen() }
    }
}