// UserRepository.kt
package com.example.mingseventsapp

import com.example.mingseventsapp.model.user.User
import com.example.togethernotes.Retrofit
import retrofit2.Response

class UserRepository {

    private val userService: UserService by lazy {
        Retrofit.createService(UserService::class.java)
    }

    suspend fun getAllUsers(): Response<List<User>> {
        return userService.getAllUsers()
    }

    suspend fun getUserById(id: Int): Response<User> {
        return userService.getUserById(id)
    }

    suspend fun createUser(user: User): Response<User> {
        return userService.createUser(user)
    }

    suspend fun updateUser(id: Int, user: User): Response<Any> {
        return userService.updateUser(id, user)
    }

    suspend fun deleteUser(id: Int): Response<Any> {
        return userService.deleteUser(id)
    }

    suspend fun login(email: String, password: String): Response<User> {
        return userService.login(email, password)
    }
}