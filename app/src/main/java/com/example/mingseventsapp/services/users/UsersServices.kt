// UserService.kt
package com.example.mingseventsapp

import com.example.mingseventsapp.model.user.User
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    // GET: api/Users
    @GET("api/Users")
    suspend fun getAllUsers(): Response<List<User>>

    // GET: api/Users/{id}
    @GET("api/Users/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<User>

    // POST: api/Users
    @POST("api/Users")
    suspend fun createUser(@Body user: User): Response<User>

    // PUT: api/Users/{id}
    @PUT("api/Users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User): Response<Any>

    // DELETE: api/Users/{id}
    @DELETE("api/Users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Any>

    // POST: api/Users/login?email=...&password=...
    @POST("api/Users/login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
                     ): Response<User>
}