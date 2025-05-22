// ReserveTicketService.kt
package com.example.mingseventsapp.services.tickets


import com.example.mingseventsapp.model.Armchair
import com.example.mingseventsapp.model.ReserveTicket
import retrofit2.Response
import retrofit2.http.*

public interface ReserveTicketService {

    // GET: api/ReserveTicket - Obtener todas las reservas
    @GET("api/ReserveTicket")
    suspend fun getAllReserveTickets(): Response<List<ReserveTicket>>

    // GET: api/ReserveTicket/{armchair_id}/{user_id}/{event_id} - Obtener por IDs
    @GET("api/ReserveTicket/{armchair_id}/{user_id}/{event_id}")
    suspend fun getReserveTicket(
        @Path("armchair_id") armchairId: Int,
        @Path("user_id") userId: Int,
        @Path("event_id") eventId: Int
                                ): Response<ReserveTicket>

    // GET: api/ReserveTicket/user/{user_id} - Obtener por usuario
    @GET("api/ReserveTicket/user/{user_id}")
    suspend fun getReserveTicketsByUserId(@Path("user_id") userId: Int): Response<List<ReserveTicket>>

    // POST: api/ReserveTicket - Crear nueva reserva
    @POST("api/ReserveTicket")
    suspend fun createReserveTicket(@Body ticket: ReserveTicket): Response<ReserveTicket>

    // PUT: api/ReserveTicket/{armchair_id}/{user_id}/{event_id} - Actualizar reserva
    @PUT("api/ReserveTicket/{armchair_id}/{user_id}/{event_id}")
    suspend fun updateReserveTicket(
        @Path("armchair_id") armchairId: Int,
        @Path("user_id") userId: Int,
        @Path("event_id") eventId: Int,
        @Body ticket: ReserveTicket
                                   ): Response<Any>

    // DELETE: api/ReserveTicket/{armchair_id}/{user_id}/{event_id} - Eliminar reserva
    @DELETE("api/ReserveTicket/{armchair_id}/{user_id}/{event_id}")
    suspend fun deleteReserveTicket(
        @Path("armchair_id") armchairId: Int,
        @Path("user_id") userId: Int,
        @Path("event_id") eventId: Int
                                   ): Response<ReserveTicket>

    // GET: api/ReserveTicket/ReservedSeatsByEvent/{event_id}
    @GET("api/ReserveTicket/ReservedSeatsByEvent/{event_id}")
    suspend fun getReservedSeatsByEvent(@Path("event_id") eventId: Int): Response<List<Armchair>>
}