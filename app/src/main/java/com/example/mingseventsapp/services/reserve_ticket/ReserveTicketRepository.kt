// ReserveTicketRepository.kt
package com.example.mingseventsapp.services.tickets

import com.example.mingseventsapp.Retrofit
import com.example.mingseventsapp.model.Armchair
import com.example.mingseventsapp.model.ReserveTicket
import retrofit2.Response

class ReserveTicketRepository {

    private val reserveTicketService: ReserveTicketService by lazy {
        Retrofit.createService(ReserveTicketService::class.java)
    }

    // Obtener todas las reservas
    suspend fun getAllReserveTickets(): Response<List<ReserveTicket>> {
        return reserveTicketService.getAllReserveTickets()
    }

    // Obtener reserva por IDs
    suspend fun getReserveTicket(
        armchairId: Int,
        userId: Int,
        eventId: Int
                                ): Response<ReserveTicket> {
        return reserveTicketService.getReserveTicket(armchairId, userId, eventId)
    }

    // Obtener reservas por usuario
    suspend fun getReserveTicketsByUserId(userId: Int): Response<List<ReserveTicket>> {
        return reserveTicketService.getReserveTicketsByUserId(userId)
    }

    // Crear una nueva reserva
    suspend fun createReserveTicket(ticket: ReserveTicket): Response<ReserveTicket> {
        return reserveTicketService.createReserveTicket(ticket)
    }

    // Actualizar una reserva existente
    suspend fun updateReserveTicket(
        armchairId: Int,
        userId: Int,
        eventId: Int,
        ticket: ReserveTicket
                                   ): Response<Any> {
        return reserveTicketService.updateReserveTicket(armchairId, userId, eventId, ticket)
    }

    // Eliminar una reserva
    suspend fun deleteReserveTicket(
        armchairId: Int,
        userId: Int,
        eventId: Int
                                   ): Response<ReserveTicket> {
        return reserveTicketService.deleteReserveTicket(armchairId, userId, eventId)
    }
    suspend fun getReservedSeatsByEvent(eventId: Int): Response<List<Armchair>> {
        return reserveTicketService.getReservedSeatsByEvent(eventId)
    }
}