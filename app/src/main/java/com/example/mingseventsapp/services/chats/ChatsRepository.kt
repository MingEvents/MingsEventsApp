// ChatRepository.kt
package com.example.mingseventsapp.services.chats

import com.example.mingseventsapp.model.chat.Chat
import com.example.togethernotes.Retrofit
import retrofit2.Response

class ChatRepository {

    private val chatService: ChatService by lazy {
        Retrofit.createService(ChatService::class.java)
    }

    suspend fun getAllChats(): Response<List<Chat>> {
        return chatService.getAllChats()
    }

    suspend fun getChatById(id: Int): Response<Chat> {
        return chatService.getChatById(id)
    }

    suspend fun getChatsByUserId(userId: Int): Response<List<Chat>> {
        return chatService.getChatsByUserId(userId)
    }

    suspend fun createChat(chat: Chat): Response<Chat> {
        return chatService.createChat(chat)
    }

    suspend fun updateChat(id: Int, chat: Chat): Response<Any> {
        return chatService.updateChat(id, chat)
    }

    suspend fun deleteChat(id: Int): Response<Chat> {
        return chatService.deleteChat(id)
    }
}