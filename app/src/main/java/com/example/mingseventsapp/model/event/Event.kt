package com.example.mingseventsapp.model.event

data class Event(
    val event_id: Int = 0,
    val name: String = "",
    val price: Int = 0,
    val reserved_places: Int = 0,
    val photo: String = "",
    val start_date: String = "",
    val end_date: String = "",
    val seating: Boolean = false,
    val descripcion: String = "",
    val establish_id: Int = 0
)
