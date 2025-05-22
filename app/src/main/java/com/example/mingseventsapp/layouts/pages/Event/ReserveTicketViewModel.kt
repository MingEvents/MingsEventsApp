package com.example.mingseventsapp.layouts.pages.Event

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mingseventsapp.model.Armchair
import com.example.mingseventsapp.model.ReserveTicket
import com.example.mingseventsapp.services.tickets.ReserveTicketRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReserveTicketViewModel: ViewModel() {
    val reserveTicketRepository = ReserveTicketRepository()

    suspend fun createReserveTicket(ticket: ReserveTicket): ReserveTicket? {
        return withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            try {
                val json = Gson().toJson(ticket)
                println(json)
                val response = reserveTicketRepository.createReserveTicket(ticket)
                response.body()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun getReservedSeats(eventId: Int): List<Armchair> {
        return withContext(Dispatchers.IO) {
            try {
                val response = reserveTicketRepository.getReservedSeatsByEvent(eventId)
                    response.body()!!

            } catch (e: Exception) {
                Log.e("SeatViewModel", "Error fetching reserved seats: ${e.message}")
                emptyList()
            }
        }
    }

}