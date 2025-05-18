package com.example.mingseventsapp.model.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.UserRepository
import com.example.mingseventsapp.model.chat.Chat
import com.example.mingseventsapp.services.chats.ChatRepository
import kotlinx.coroutines.launch

class UsersViewModel: ViewModel(){

    suspend fun getAllUsers(): List<User> {
        val appRepository = UserRepository()

        return try {
            val response = appRepository.getAllUsers()

            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}