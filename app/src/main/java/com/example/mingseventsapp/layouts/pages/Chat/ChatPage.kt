package com.example.mingseventsapp.layouts.pages.Chat

import androidx.compose.foundation.background
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
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.mingseventsapp.Routes
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.UserRepository
import com.example.mingseventsapp.model.chat.Chat
import com.example.mingseventsapp.model.user.User
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ChatPage(navController: NavHostController) {
    val chats = mutableListOf(
        Chat(1, "2024-03-10 08:45", 101, 102),
        Chat(2, "2024-03-10 09:12", 101, 103),
        Chat(3, "2024-03-10 09:30", 101, 104),
        Chat(4, "2024-03-10 10:05", 101, 105),
        Chat(5, "2024-03-10 10:45", 101, 201),
        Chat(6, "2024-03-10 11:20", 101, 202),
        Chat(7, "2024-03-10 11:50", 101, 203),
        Chat(8, "2024-03-10 12:10", 101, 204),
        Chat(9, "2024-03-10 13:00", 101, 205),
        Chat(10, "2024-03-10 13:45", 101, 206),
        Chat(11, "2024-03-10 14:20", 101, 207),
        Chat(12, "2024-03-10 15:00", 101, 208),
        Chat(13, "2024-03-10 15:40", 101, 209),
        Chat(14, "2024-03-10 16:15", 101, 210),
                             )
    val userList = listOf(
        User(
            101,
            "Juan",
            "Pérez",
            123456789,
            "passJuan123",
            "https://example.com/photos/juan.jpg",
            "juan.perez@example.com",
            1
            ),
        User(
            102,
            "María",
            "García",
            987654321,
            "passMaria456",
            "https://example.com/photos/maria.jpg",
            "maria.garcia@example.com",
            2
            ),
        User(
            103,
            "Carlos",
            "López",
            555555555,
            "carlosL789",
            "https://example.com/photos/carlos.jpg",
            "carlos.lopez@example.com",
            1
            ),
        User(
            104,
            "Ana",
            "Martínez",
            444444444,
            "anaM123!",
            "https://example.com/photos/ana.jpg",
            "ana.martinez@example.com",
            3
            ),
        User(
            105,
            "Luis",
            "Rodríguez",
            777777777,
            "luisR456",
            "https://example.com/photos/luis.jpg",
            "luis.rodriguez@example.com",
            2
            ),
        User(
            201,
            "Sofía",
            "Hernández",
            666666666,
            "sofiaH789",
            "https://example.com/photos/sofia.jpg",
            "sofia.hernandez@example.com",
            1
            ),
        User(
            202,
            "Andrés",
            "Díaz",
            333333333,
            "andresD123",
            "https://example.com/photos/andres.jpg",
            "andres.diaz@example.com",
            2
            ),
        User(
            203,
            "Lucía",
            "Vargas",
            222222222,
            "luciaV456",
            "https://example.com/photos/lucia.jpg",
            "lucia.vargas@example.com",
            3
            ),
        User(
            204,
            "Javier",
            "Silva",
            111111111,
            "javierS789",
            "https://example.com/photos/javier.jpg",
            "javier.silva@example.com",
            1
            ),
        User(
            205,
            "Valentina",
            "Torres",
            888888888,
            "valenT123",
            "https://example.com/photos/valentina.jpg",
            "valentina.torres@example.com",
            2
            ),
        User(
            206,
            "Diego",
            "Castro",
            999999999,
            "diegoC456",
            "https://example.com/photos/diego.jpg",
            "diego.castro@example.com",
            1
            ),
        User(
            207,
            "Camila",
            "Flores",
            101010101,
            "camilaF789",
            "https://example.com/photos/camila.jpg",
            "camila.flores@example.com",
            3
            ),
        User(
            208,
            "Gabriel",
            "Romero",
            112233445,
            "gabrielR123",
            "https://example.com/photos/gabriel.jpg",
            "gabriel.romero@example.com",
            2
            ),
        User(
            209,
            "Isabella",
            "Ruiz",
            554433221,
            "isabellaU456",
            "https://example.com/photos/isabella.jpg",
            "isabella.ruiz@example.com",
            1
            ),
        User(
            210,
            "Mateo",
            "Jiménez",
            778899001,
            "mateoJ789",
            "https://example.com/photos/mateo.jpg",
            "mateo.jimenez@example.com",
            2
            )
                         )


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
                    model = "https://www.iconpacks.net/icons/2/free-user-icon-3296-thumb.png",
                    contentDescription = "Imagen del evento",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                          )
            }
        }
    }
}

