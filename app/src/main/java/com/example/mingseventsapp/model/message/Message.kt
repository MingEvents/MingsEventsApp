package com.example.mingseventsapp.model.message

data class Message(
    val message_id: Int,
    val sender_id: Int,
    var content: String,
    val send_at: String,
    val isRead: Int,
    val chat_id: Int
                  )
