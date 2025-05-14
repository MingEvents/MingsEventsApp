package com.example.mingseventsapp.layouts.pages.Chat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mingseventsapp.Routes
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.model.chat.Chat
import com.example.mingseventsapp.model.message.Message

@Composable
fun ChatConv(navController: NavHostController) {
    var messageText by remember { mutableStateOf("") }

    val messageList = remember {
        mutableStateListOf  (
            Message(message_id = 1, sender_id = 3, content = "Hola, ¿cómo estás?", send_at = "2024-11-01T10:30:15", isRead = 1, chat_id = 1),
            Message(message_id = 2, sender_id = 5, content = "Bien, gracias. ¿Y tú?", send_at = "2024-11-01T10:31:00", isRead = 1, chat_id = 1),
            Message(message_id = 3, sender_id = 3, content = "Todo bien, ¿ya viste el documento que te mandé?", send_at = "2024-11-01T10:32:45", isRead = 1, chat_id = 1),
            Message(message_id = 4, sender_id = 5, content = "Sí, lo revisé. Está muy claro.", send_at = "2024-11-01T10:34:20", isRead = 1, chat_id = 1),
            Message(message_id = 5, sender_id = 3, content = "¿Vamos a hacer la reunión hoy?", send_at = "2024-11-01T10:36:00", isRead = 1, chat_id = 1),
            Message(message_id = 6, sender_id = 5, content = "Claro, a las 3pm en la sala virtual.", send_at = "2024-11-01T10:37:10", isRead = 1, chat_id = 1),
            Message(message_id = 7, sender_id = 3, content = "Perfecto, nos vemos allá 👍", send_at = "2024-11-01T10:38:30", isRead = 1, chat_id = 1),
            Message(message_id = 8, sender_id = 5, content = "¿Traes los datos listos?", send_at = "2024-11-01T10:39:45", isRead = 0, chat_id = 1),
            Message(message_id = 9, sender_id = 3, content = "Sí, ya los tengo. ¿Tú?", send_at = "2024-11-01T10:40:20", isRead = 0, chat_id = 1),
            Message(message_id = 10, sender_id = 5, content = "Casi termino, te aviso cuando esté listo.", send_at = "2024-11-01T10:41:15", isRead = 0, chat_id = 1),

        )
    }

    Box(
        modifier = Modifier
            .background(Color(0xFFd7e9fc))
            .fillMaxSize(),
    ) {
       Row (
           modifier = Modifier
               .fillMaxWidth()
               .height(80.dp)
               .background(Color(0xFFFFFFFF))
       ) {
           Icon(
               imageVector = Icons.Default.KeyboardArrowLeft,
               contentDescription = "Volver atrás",
               modifier = Modifier
                   .size(30.dp)
                   .align(Alignment.CenterVertically)
                   .pointerInput(Unit) {
                       detectTapGestures(onTap = {
                           navController.navigate(Routes.MENU + "/1")
                       })
                   },
               tint = Color(0xFF063970)
               )
           Box(
               modifier = Modifier
                   .align(Alignment.CenterVertically)
                   .fillMaxWidth()
                   .clip(CircleShape)
                   .padding(start = 40.dp)
              ) {
               AsyncImage(
                   model = UserLogged.selectedUserChat.photo,
                   contentDescription = "Imagen del evento",
                   contentScale = ContentScale.Crop,
                   modifier = Modifier
                       .clip(CircleShape)
                       .size(50.dp)
               )

               Text(
                   text = UserLogged.selectedUserChat.name + " " + UserLogged.selectedUserChat.second_name,
                   modifier = Modifier
                       .align(Alignment.CenterEnd)
                       .padding(end = 150.dp),
                   style = TextStyle(
                       fontSize = 20.sp,
                       color = Color.Black,
                       fontWeight = FontWeight.Bold
                                    )
                   )

           }
       }

        LazyColumn(
            modifier = Modifier
                .padding(top = 80.dp, start = 10.dp, end = 10.dp)
                  ) {
            items(messageList) { message ->
                MessageItem(message)
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .clip(RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray),
            verticalAlignment = Alignment.CenterVertically
           ) {
            TextField(
                value = messageText,
                onValueChange = {
                    messageText = it
                },
                placeholder = { Text("Mensaje...") },
                modifier = Modifier
                    .weight(1f),
                maxLines = 2,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                                                 ),
                textStyle = LocalTextStyle.current.copy(fontSize = 14.sp)
                     )

            if (messageText.isNotBlank()) {
                IconButton(
                    onClick = {
                        var message: Message = Message(0, UserLogged.user.user_id, "", "", 0, 3)
                        message.content = messageText
                        messageList.add(message)
                        messageText = ""
                    },
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(36.dp)
                        .background(Color(0xFF0000FF), shape = CircleShape)
                          ) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Enviar mensaje",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                        )
                }
            } else {

                IconButton(
                    onClick = {  },
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(36.dp)
                        .background(Color(0xFF0000FF), shape = CircleShape)
                          ) {
                    Icon(
                        imageVector = Icons.Filled.Mic,
                        contentDescription = "Enviar audio",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                        )
                }
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 10.dp),
        horizontalArrangement = if (message.sender_id == UserLogged.user.user_id) {
            Arrangement.Start
        } else {
            Arrangement.End
        }
       ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (message.sender_id == UserLogged.user.user_id) Color(0xFF7796CB)
                    else Color(0xFF576490)
                           )
                .padding(12.dp)
                .widthIn(max = 230.dp)
           ) {
            Column {
                Text(
                    text = message.content,
                    color = Color.White,
                    fontSize = 18.sp,
                    lineHeight = 22.sp
                    )

                if (message.sender_id == UserLogged.user.user_id && message.isRead == 1) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 4.dp),
                       ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Leído",
                            tint = Color.Blue,
                            modifier = Modifier
                                .size(18.dp)
                            )
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Leído",
                            tint = Color.Blue,
                            modifier = Modifier
                                .size(18.dp)
                                .offset(x = (-6).dp)
                            )
                    }
                } else if (message.sender_id == UserLogged.user.user_id && message.isRead == 0){
                    Row(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 4.dp),
                       ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Leído",
                            tint = Color.DarkGray,
                            modifier = Modifier.size(18.dp)
                            )
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Leído",
                            tint = Color.DarkGray,
                            modifier = Modifier
                                .size(18.dp)
                                .offset(x = (-6).dp)
                            )
                    }
                }
            }
        }
    }
}
