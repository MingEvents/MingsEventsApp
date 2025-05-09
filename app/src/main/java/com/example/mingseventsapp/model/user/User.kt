package com.example.mingseventsapp.model.user

data class User(
    val user_id: Int = 0,
    var name: String = "Bruno",
    var second_name: String = "Convalia Rejas",
    var phone: Int = 664321234,
    var password: String = "1234",
    val photo: String = "",
    var email: String = "bruno@gmail.com",
    val role_id: Int = 1
               )
