package com.example.mingseventsapp
import LoginViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mingseventsapp.layouts.login.LoginScreen
import com.example.mingseventsapp.layouts.login.PutBackground
import com.example.mingseventsapp.layouts.menu.HomeMenuContent
import com.example.mingseventsapp.layouts.pages.Chat.ChatConv
import com.example.mingseventsapp.layouts.pages.Chat.ChatPage
import com.example.mingseventsapp.layouts.register.RegisterScreen
import com.example.mingseventsapp.model.user.User


@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            val viewModel: LoginViewModel = viewModel()

            PutBackground {
                LoginScreen(
                    viewModel = viewModel,
                    navController = navController,
                )
            }
        }
        composable(Routes.MENU) {
            HomeMenuContent(navController = navController)
        }
        composable(Routes.REGISTER) {
            RegisterScreen()
        }

        composable(Routes.CHAT) {
            ChatPage(navController = navController)
        }

        composable(Routes.CHATCONV) {
            ChatConv(navController = navController)
        }
    }
}


