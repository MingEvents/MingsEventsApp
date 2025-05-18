// ChatService.kt
package com.example.mingseventsapp.services.chats

import com.example.mingseventsapp.model.chat.Chat
import com.example.mingseventsapp.model.chat.ChatContainer
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.Response

interface ChatService {

    // GET: api/Chat - Obtener todos los chats
    @GET("api/Chat")
    suspend fun getAllChats(): Response<List<Chat>>

    // GET: api/Chat/{id} - Obtener chat por ID
    @GET("api/Chat/{id}")
    suspend fun getChatById(@Path("id") id: Int): Response<List<Chat>>

    // GET: api/Chat/user/{id} - Obtener chats asociados a un usuario
    @GET("api/Chat/user/{id}")
    suspend fun getChatsByUserId(@Path("id") userId: Int): Response<List<Chat>>

    // POST: api/Chat - Crear nuevo chat
    @POST("api/Chat")
    suspend fun createChat(@Body chat: Chat): Response<Chat>

    // PUT: api/Chat/{id} - Actualizar chat
    @PUT("api/Chat/{id}")
    suspend fun updateChat(@Path("id") id: Int, @Body chat: Chat): Response<Any>

    // DELETE: api/Chat/{id} - Eliminar chat
    @DELETE("api/Chat/{id}")
    suspend fun deleteChat(@Path("id") id: Int): Response<Chat>
}