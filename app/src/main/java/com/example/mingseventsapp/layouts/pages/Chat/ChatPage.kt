package com.example.mingseventsapp.layouts.pages.Chat

import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.mingseventsapp.Routes
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.model.chat.Chat
import com.example.mingseventsapp.model.user.User
import com.example.mingseventsapp.model.user.UsersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.log

import java.io.PrintWriter
import java.net.Socket

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatPage(navController: NavHostController) {
    UserLogged.selectedUserChat = User()
    UserLogged.selectedChat = Chat()
    val chatViewModel = ChatViewModel()
    val userViewModel = UsersViewModel()

    withContext(Dispatchers.IO) {
        val chats: MutableList<Chat> = chatViewModel.getChatsById()
    }

    val userList: MutableList<User> = userViewModel.getAllUsers()


    var searchString by remember { mutableStateOf("") }
    var filteredChats by remember { mutableStateOf(chats.toMutableList()) }

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
                text = "Chat",
                fontSize = 40.sp,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
            Divider(color = Color.Black, thickness = 1.dp)
        }
    }

    Box(
        modifier = Modifier
            .padding(top = 130.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
       ) {
    SearchBar(query = searchString,
              onQueryChange = { newQuery ->
                  searchString = newQuery
                  filteredChats = chats.filter { chat ->
                      val user2 = userList.find { it.user_id == chat.user2_id }
                      val fullName = user2?.name + " " + user2?.second_name
                      fullName.contains(newQuery, ignoreCase = true)
                  }.toMutableList()
              },
              onSearch = {},
              active = false,
              onActiveChange = {},
              shape = RoundedCornerShape(12.dp),
              modifier = Modifier.fillMaxWidth(),
              placeholder = { Text("Search chats") }) {}
    }

    LazyColumn(
        modifier = Modifier
            .padding(top = 230.dp)
            .fillMaxHeight()
              ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        items(filteredChats) { chat ->

            ChatItem(chat, userList, navController)
        }

        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun ChatItem(chat: Chat, usersList: List<User>, navController: NavHostController) {
    val user = usersList.find { it.user_id == chat.user2_id }!!

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color(0xFFD7E9FC)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF7796CB)
                                        )
        ) {
        Row (
            modifier = Modifier.fillMaxWidth()
            ) {
            Column(modifier = Modifier
                .padding(16.dp)
                .weight(1f)
                  ) {
                Text(
                    text = user.name + " " + user.second_name,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 3.dp, bottom = 15.dp)
                    )

                Text(
                    text = "Last time conected: 12h ago",
                    fontSize = 14.sp,
                    color = Color.White
                    )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                   ) {
                    Button(
                        onClick = {
                            UserLogged.selectedUserChat = user
                            UserLogged.selectedChat = chat
                            navController.navigate(Routes.CHATCONV)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF14296F),
                            contentColor = Color.White
                                                            ),
                        modifier = Modifier
                            .height(40.dp)
                          ) {
                        Text(
                            text = "Open chat"
                            )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(end = 6.dp)
                    .size(130.dp)
                    .align(Alignment.CenterVertically)
               ) {
                AsyncImage(
                    model = user.photo,
                    contentDescription = "Imagen del evento",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape)
                          )
            }
        }
    }
}



