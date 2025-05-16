package com.example.mingseventsapp.model.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.UserRepository
import com.example.mingseventsapp.model.chat.Chat
import com.example.mingseventsapp.services.chats.ChatRepository
import kotlinx.coroutines.launch

class UsersViewModel: ViewModel(){

    fun getAllUsers(): MutableList<User> {
        val appRepository = UserRepository()
        var users = mutableListOf<User>()
        viewModelScope.launch{
            try {
                val response = appRepository.getAllUsers()

                when {
                    response.isSuccessful -> {
                        response.body()?.let { userResponse ->
                            users = userResponse.toMutableList()
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
        return users
    }

}