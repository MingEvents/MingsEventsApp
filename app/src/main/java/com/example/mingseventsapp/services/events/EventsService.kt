// EventService.kt
package com.example.mingseventsapp.services.events

import com.example.mingseventsapp.model.event.Event
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface EventService {

    // GET: api/Event - Obtener todos los eventos
    @GET("api/Event")
    suspend fun getAllEvents(): Response<List<Event>>

    // GET: api/Event/{id} - Obtener evento por ID
    @GET("api/Event/{id}")
    suspend fun getEventById(@Path("id") id: Int): Response<Event>

    // POST: api/Event - Crear nuevo evento
    @POST("api/Event")
    suspend fun createEvent(@Body event: Event): Response<Event>

    // PUT: api/Event/{id} - Actualizar evento
    @PUT("api/Event/{id}")
    suspend fun updateEvent(@Path("id") id: Int, @Body event: Event): Response<Any>

    // DELETE: api/Event/{id} - Eliminar evento
    @DELETE("api/Event/{id}")
    suspend fun deleteEvent(@Path("id") id: Int): Response<Event>

    // POST: api/Event/{id}/UploadPhoto - Subir foto de evento
    @Multipart
    @POST("api/Event/{id}/UploadPhoto")
    suspend fun uploadEventPhoto(
        @Path("id") id: Int,
        @Part("file\"; filename=\"photo.jpg\"") file: MultipartBody.Part
                                ): Response<Any>

    // GET: api/Event/{id}/Photo - Descargar foto del evento
    @GET("api/Event/{id}/Photo")
    suspend fun getEventPhoto(@Path("id") id: Int): Response<okhttp3.ResponseBody>
}