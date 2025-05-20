package com.example.mingseventsapp.layouts.pages.Event

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mingseventsapp.model.ReserveTicket
import com.example.mingseventsapp.services.tickets.ReserveTicketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReserveTicketViewModel: ViewModel() {

    fun createReserveTicket(ticket: ReserveTicket) {
        val reserveTicketRepository = ReserveTicketRepository()

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
}