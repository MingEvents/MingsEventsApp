package com.example.mingseventsapp

import com.example.mingseventsapp.layouts.pages.Event.EventPage
import com.example.mingseventsapp.model.chat.Chat
import com.example.mingseventsapp.model.event.Event
import com.example.mingseventsapp.model.user.User


object UserLogged {
    var user: User = User()
    var selectedUserChat: User = User()
    var selectedChat: Chat = Chat()
    var isConected: Boolean = false
    var selectedEvent: Event = Event()
}