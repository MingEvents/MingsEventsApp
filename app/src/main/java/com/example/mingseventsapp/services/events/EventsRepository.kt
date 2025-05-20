// EventRepository.kt
package com.example.mingseventsapp.services.events

import com.example.mingseventsapp.model.event.Event
import com.example.mingseventsapp.Retrofit
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class EventRepository {

    private val eventService: EventService by lazy {
        Retrofit.createService(EventService::class.java)
    }

    suspend fun getAllEvents(): Response<List<Event>> {
        return eventService.getAllEvents()
    }

    suspend fun getEventById(id: Int): Response<Event> {
        return eventService.getEventById(id)
    }

    suspend fun createEvent(event: Event): Response<Event> {
        return eventService.createEvent(event)
    }

    suspend fun updateEvent(id: Int, event: Event): Response<Any> {
        return eventService.updateEvent(id, event)
    }

    suspend fun deleteEvent(id: Int): Response<Event> {
        return eventService.deleteEvent(id)
    }

    suspend fun uploadEventPhoto(id: Int, file: MultipartBody.Part): Response<Any> {
        return eventService.uploadEventPhoto(id, file)
    }

    suspend fun getEventPhoto(id: Int): Response<okhttp3.ResponseBody> {
        return eventService.getEventPhoto(id)
    }
}