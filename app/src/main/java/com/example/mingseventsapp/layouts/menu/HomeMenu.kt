package com.example.mingseventsapp.layouts.menu
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mingseventsapp.layouts.pages.Cart.CartPage
import com.example.mingseventsapp.layouts.pages.Chat.ChatPage
import com.example.mingseventsapp.layouts.pages.Event.EventPage
import com.example.mingseventsapp.layouts.pages.Profile.ProfilePage
import com.example.mingseventsapp.model.NavItem

@Composable
fun HomeMenuContent(modifier: Modifier = Modifier, navController: NavHostController, startIndex: Int = 0) {
    var selectedIndex by remember { mutableStateOf(0) }
    selectedIndex = startIndex

    val navItemList = listOf(
        NavItem("Events", Icons.Default.Star),
        NavItem("Chat", Icons.Default.Email),
        NavItem("Cart", Icons.Default.ShoppingCart),
        NavItem("Profile", Icons.Default.Settings)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
               navItemList.forEachIndexed { index, navItem ->
                   NavigationBarItem(
                       selected = selectedIndex ==  index,
                       onClick = {
                           selectedIndex = index
                       },
                       icon = {
                           Icon(
                               imageVector = navItem.icon,
                               contentDescription = navItem.label,
                               tint = Color.Black
                           )
                       },
                       label = {
                           Text(text = navItem.label)
                       }
                   )
               }
            }
        }
    ) {
        innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex, navController)
    }
}

@Composable
fun ContentScreen(modifier: Modifier, selectedIndex: Int, navController: NavHostController) {
    when(selectedIndex) {
        0 -> EventPage(navController)
        1 -> ChatPage(navController)
        2 -> CartPage()
        3 -> ProfilePage()
    }

}


