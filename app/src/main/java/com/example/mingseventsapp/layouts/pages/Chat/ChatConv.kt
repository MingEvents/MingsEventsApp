package com.example.mingseventsapp.layouts.pages.Chat

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.mingseventsapp.Utilities.CryptoUtil
import com.example.mingseventsapp.layouts.pages.Chat.ChatServer.Companion.SERVER_IP
import com.example.mingseventsapp.layouts.pages.Chat.ChatServer.Companion.SERVER_PORT
import com.example.mingseventsapp.layouts.pages.Chat.ChatServer.Companion.TAG_SOCKET
import com.example.mingseventsapp.model.chat.Chat
import com.example.mingseventsapp.model.message.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import kotlin.concurrent.thread
import kotlin.math.log

class ChatServer {
    companion object {
        const val DEFAULT_CHAT_ID = -1
        const val DEFAULT_USER_ID = -1
        const val DEFAULT_RECEIVER_NAME = "Desconocido"
        const val SERVER_IP = "10.0.3.51"
        const val SERVER_PORT = 5000
        const val TAG_SOCKET = "SOCKET"
    }
}

private lateinit var socket: Socket
private lateinit var outputStream: PrintWriter
private lateinit var inputStream: BufferedReader

@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatConv(navController: NavHostController) {
    var messageText by remember { mutableStateOf("") }
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val messageList = remember { mutableStateListOf<Message>() }

    DisposableEffect(Unit) {
        onDispose {
            closeConnection()
        }
    }

    coroutineScope.launch {
        connectToServer(lazyListState, coroutineScope, messageList)
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
                           closeConnection()
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
                        sendMessage(UserLogged.selectedUserChat.user_id, messageText, 0, messageList)
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


private suspend fun connectToServer(
    lazyListState: LazyListState,
    coroutineScope: CoroutineScope,
    messageList: MutableList<Message>
                                   ) = withContext(Dispatchers.IO) {

    try {
        if (UserLogged.isConected == false) {
            socket = Socket(SERVER_IP, SERVER_PORT)
            outputStream = PrintWriter(socket.getOutputStream(), true)
            inputStream = BufferedReader(InputStreamReader(socket.getInputStream()))
            UserLogged.isConected = true;

            Log.e(TAG_SOCKET, "Conectado al servidor")

            val authJson = JSONObject().apply {
                put("type", "auth")
                put("userId", UserLogged.user.user_id)
                put("chatId", UserLogged.selectedChat.chat_id)
            }

            outputStream.println(authJson.toString())
            receiveMessages(lazyListState, coroutineScope, messageList)

        } else {
            //lol
        }
    } catch (e: Exception) {
        Log.e(TAG_SOCKET, "Error al conectar: ${e.message}", e)
    }
}

private fun receiveMessages( lazyListState: LazyListState,
                             coroutineScope: CoroutineScope,
                             messageList: MutableList<Message>) {
    try {
        val buffer = CharArray(1024)
        val stringBuilder = StringBuilder()

        while (true) {
            val bytesRead = inputStream?.read(buffer)
            if (bytesRead != -1) {
                stringBuilder.append(String(buffer, 0, bytesRead!!))
                val fullMessage = stringBuilder.toString()

                if (fullMessage.contains("\n")) {
                    val newMessages = fullMessage.split("\n")
                    for (messageStr in newMessages) {
                        if (messageStr.isNotBlank()) processIncomingMessage(messageStr, lazyListState, coroutineScope, messageList)
                    }
                    stringBuilder.clear()
                }
            } else {
                Log.e(TAG_SOCKET, "Conexión cerrada por el servidor")
                break
            }
        }
    } catch (e: IOException) {
        Log.e(TAG_SOCKET, "Error al recibir mensaje: ${e.message}")
    }
}

@SuppressLint("NewApi")
private fun processIncomingMessage(messageStr: String,
                                   lazyListState: LazyListState,
                                   coroutineScope: CoroutineScope,
                                   messageList: MutableList<Message>
                                  ) {
    try {
        val json = JSONObject(messageStr)

        val decryptedText = CryptoUtil.decrypt(json.getString("content"))

        val newMessage = Message(
            message_id = json.getInt("message_id"),
            sender_id = json.getInt("from"),
            content = decryptedText,
            send_at = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()).toString(),
            isRead = if (json.optBoolean("is_read", false)) 1 else 0,
            chat_id = UserLogged.selectedChat.chat_id
                                )


        if (newMessage.chat_id == UserLogged.selectedChat.chat_id) {
            messageList.add(newMessage)
            coroutineScope.launch {
                delay(50)
                lazyListState.animateScrollToItem(messageList.size - 1)
            }

        }

       /*     if (!newMessage.isRead && newMessage.senderId != actualApp.id) {
                markMessageAsRead(newMessage)
            }*/
    } catch (e: Exception) {
        Log.e(TAG_SOCKET, "Error procesando mensaje: ${e.message}")
    }
}

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
private fun sendMessage(receiverId: Int, messageText: String, messageId: Int, messageList: MutableList<Message>) {
    if (messageText.isEmpty()) return

    val encryptedText = CryptoUtil.encrypt(messageText)

    val messageJson = JSONObject().apply {
        put("sender_id", UserLogged.user.user_id)
        put("receiver_id", receiverId)
        put("content", encryptedText)
    }

    thread {
        try {
            outputStream?.println(messageJson.toString())

            val newMessage = Message(
                message_id = messageId,
                sender_id = UserLogged.user.user_id,
                content = messageText,
                send_at = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()).toString(),
                isRead = 0,
                chat_id = UserLogged.selectedChat.chat_id
                                    )
                messageList.add(newMessage)

        } catch (e: Exception) {
            Log.e(TAG_SOCKET, "Error enviando mensaje: ${e.message}")
        }
    }
}

fun closeConnection() {
    try {
        inputStream?.close()
        outputStream?.println("disconnect")
        outputStream?.close()
        socket?.close()
        UserLogged.isConected = false
    } catch (e: IOException) {
        Log.e(TAG_SOCKET, "Error al cerrar conexión: ${e.message}")
    } finally {
        socket.close()
        UserLogged.isConected = false
    }
}

/*override fun onDestroy() {
    super.onDestroy()
    try {
        socket.close()
    } catch (e: IOException) {
       // log(TAG_SOCKET, "Error al cerrar socket: ${e.message}")
    }
}*/
