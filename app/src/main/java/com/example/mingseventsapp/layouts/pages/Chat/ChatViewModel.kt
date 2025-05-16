package com.example.mingseventsapp.layouts.pages.Chat

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.UserRepository
import com.example.mingseventsapp.model.chat.Chat
import com.example.mingseventsapp.model.user.User
import com.example.mingseventsapp.services.chats.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatViewModel  : ViewModel() {

 fun getChatsById(): MutableList<Chat> {
         viewModelScope.launch {
            val appRepository = ChatRepository()
            val response = appRepository.getChatsByUserId(UserLogged.user.user_id)

            if (response.isSuccessful) {
                return@launch response.body()?.toMutableList() ?: mutableListOf()
            } else {
                return mutableListOf()
            }
        }
    }

}