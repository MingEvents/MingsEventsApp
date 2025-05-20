package com.example.mingseventsapp.model

data class ReserveTicket(
    val armchair_id: Int=0,
    var user_id: Int=0,
    var event_id: Int=0,
    val reservation_date: String="" // Formato esperado: "YYYY-MM-DDTHH:mm:ss"
                        )