package com.example.mingseventsapp.layouts.pages.Cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.mingseventsapp.Routes
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.layouts.pages.Event.ArmchairViewModel
import com.example.mingseventsapp.layouts.pages.Event.EventViewModel
import com.example.mingseventsapp.layouts.pages.Event.ReserveTicketViewModel
import com.example.mingseventsapp.model.Armchair
import com.example.mingseventsapp.model.ReserveTicket
import com.example.mingseventsapp.model.event.Event
import com.example.mingseventsapp.services.events.EventRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun CartPage() {
    val coroutineScope = rememberCoroutineScope()
    val reserveTicketViewModel = ReserveTicketViewModel()
    val armchairViewModel = ArmchairViewModel()
    val eventViewModel = EventViewModel()

    var isLoading by remember { mutableStateOf(true) }
    var reserveTickets by remember { mutableStateOf<List<ReserveTicket>>(emptyList()) }
    var events by remember { mutableStateOf<List<Event>>(emptyList()) }
    var armchairs by remember { mutableStateOf<List<Armchair>>(emptyList()) }

    var upcomingTickets by remember { mutableStateOf<List<TicketWithDetails>>(emptyList()) }

    if (isLoading) {
        showLoadingDialog()
    }

    LaunchedEffect(Unit) {
        isLoading = true
        try {
            val userId = UserLogged.user.user_id

            reserveTickets = reserveTicketViewModel.getReserveTicketsByUserId(userId)
            events = eventViewModel.getAllEvents()

            val filtered = mutableListOf<TicketWithDetails>()

            for (ticket in reserveTickets) {
                var first: Boolean = true
                val event = events.find { it.event_id == ticket.event_id }

                if (first && event != null) {
                    armchairs = armchairViewModel.loadArmchairsByEstablishment(event.establish_id)
                    first = false
                }
                val seat = armchairs.find { it.armchair_id == ticket.armchair_id }

                if (event != null && seat != null) {
                    filtered.add(TicketWithDetails(ticket, event, seat))
                }
            }

            upcomingTickets = filtered
        } catch (e: Exception) {
            upcomingTickets = emptyList()
        } finally {
            isLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFf8fafc)),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 24.dp)
            ) {
                Text(
                    text = "Your Reservations",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF14296F)
                )
                Text(
                    text = "Here you can view your purchased tickets.",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            if (upcomingTickets.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text("You have no purchased tickets.", color = Color.Gray)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                ) {
                    items(upcomingTickets.size) { item ->
                        val ticketDetails = upcomingTickets[item]
                        TicketCard(ticketDetails)
                    }

                    item {
                        Spacer(modifier = Modifier.height(110.dp)) // Ajusta la altura según necesites
                    }
                }
            }
        }
    }
}

@Composable
fun TicketCard(ticket: TicketWithDetails) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F6FF))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Status: Confirmed
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Confirmed Ticket",
                    tint = Color.Green,
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = "Confirmed",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Green,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Event Name
            Text(
                text = ticket.event.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF14296F)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Date and Location
            Text(
                text = "Date: ${ticket.event.start_date}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Seat: Row ${ticket.seat.rows}, Col ${ticket.seat.columns}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

data class TicketWithDetails(
    val ticket: ReserveTicket,
    val event: Event,
    val seat: Armchair
)

@Composable
fun showLoadingDialog() {
    Dialog(onDismissRequest = { /* No se puede cerrar manualmente */ }) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xFF14296F))
        }
    }
}