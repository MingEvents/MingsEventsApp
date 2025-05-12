package com.example.mingseventsapp.layouts.pages.Chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.UserRepository
import com.example.mingseventsapp.model.chat.Chat
import com.example.mingseventsapp.model.user.User
import com.example.mingseventsapp.services.chats.ChatRepository
import kotlinx.coroutines.launch

class ChatViewModel  : ViewModel() {

    fun getChatsById(): List<Chat> {
        val appRepository = ChatRepository()
        var chats = mutableListOf<Chat>()
        viewModelScope.launch {
            try {
                val response = appRepository.getChatsByUserId(UserLogged.user.user_id)

                when {
                    response.isSuccessful -> {
                        response.body()?.let { chatsResponse ->
                            chats = chatsResponse.toMutableList()
                        } ?: run {}
                    }

                    response.code() == 404 -> {

                    }

                    else -> {
                    }
                }
            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
        return chats
    }

}