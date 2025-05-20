package com.example.mingseventsapp.layouts.pages.Event

import android.provider.CalendarContract.Events
import android.util.Log
import androidx.compose.runtime.Composable
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.model.chat.Chat
import com.example.mingseventsapp.model.event.Event
import com.example.mingseventsapp.services.chats.ChatRepository
import com.example.mingseventsapp.services.events.EventRepository
class EventViewModel {

    suspend fun getAllEvents(): List<Event> {
        val chatRepository = EventRepository()
        return try {
            val response = chatRepository.getAllEvents()
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