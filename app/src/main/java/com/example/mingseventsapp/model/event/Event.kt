package com.example.mingseventsapp.model.event

data class Event(
    val event_id: Int,
    val name: String,
    val price: Int,
    val reserved_places: Int,
    val photo: String,
    val start_date: String,
    val end_date: String,
    val seating: Int,
    val descripcion: String,
    val establish_id: Int
)
