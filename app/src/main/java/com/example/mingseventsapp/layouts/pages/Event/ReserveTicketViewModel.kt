package com.example.mingseventsapp.layouts.pages.Event

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mingseventsapp.model.Armchair
import com.example.mingseventsapp.model.ReserveTicket
import com.example.mingseventsapp.services.tickets.ReserveTicketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReserveTicketViewModel: ViewModel() {
    val reserveTicketRepository = ReserveTicketRepository()

    fun createReserveTicket(ticket: ReserveTicket) {

        val _reserveTicketState = MutableStateFlow<ReserveTicket?>(null)
        viewModelScope.launch {
            try {
                val response = reserveTicketRepository.createReserveTicket(ticket)
                if (response.isSuccessful && response.body() != null) {
                    _reserveTicketState.value = response.body()
                } else {
                    _reserveTicketState.value = null
                    Log.e("createReserveTicket", "Response not successful")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _reserveTicketState.value = null
            }
        }
    }

    fun getReserveadSeats(eventId: Int): List<Armchair> {
        val reservedSeatsState = MutableStateFlow<List<Armchair>?>(null)

        viewModelScope.launch {
            try {
                val response = reserveTicketRepository.getReservedSeatsByEvent(eventId)
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    reservedSeatsState.value = response.body()
                } else {
                    Log.e("SeatViewModel", "No reserved seats found")
                    reservedSeatsState.value = emptyList()
                }
            } catch (e: Exception) {
                Log.e("SeatViewModel", "Error fetching reserved seats: ${e.message}")
                reservedSeatsState.value = emptyList()
            }
        }
        return  reservedSeatsState.value ?: emptyList()
    }

}