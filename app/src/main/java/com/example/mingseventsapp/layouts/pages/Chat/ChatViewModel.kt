package com.example.mingseventsapp.layouts.pages.Chat

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.UserRepository
import com.example.mingseventsapp.model.chat.Chat
import com.example.mingseventsapp.model.user.User
import com.example.mingseventsapp.services.chats.ChatRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatViewModel  : ViewModel() {

    suspend fun getChatsByUserId(): List<Chat> {
        val chatRepository = ChatRepository()
        return try {
            val response = chatRepository.getChatsByUserId(UserLogged.user.user_id)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return body
                } else {
                    Log.e("NULL_BODY", "Response was successful but body is null")
                    return emptyList()
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}