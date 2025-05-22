package com.example.mingseventsapp.layouts.pages.Event

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mingseventsapp.Routes
import com.example.mingseventsapp.UserLogged
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mingseventsapp.model.Armchair
import com.example.mingseventsapp.model.ReserveTicket
import androidx.compose.runtime.LaunchedEffect

@Composable
fun BuyTicket(navController: NavHostController) {
    var isLoading by remember { mutableStateOf(false) }

    val reserveTicketViewModel = ReserveTicketViewModel()
    val armchairViewModel = ArmchairViewModel()
    val event = UserLogged.selectedEvent
    var availableSeats = 0
    var ticketCount by remember { mutableStateOf(1) }
    var selectedSeats = remember { mutableStateListOf<Armchair>() }
    var reservedSeats: List<Armchair> = emptyList()
    var totalArmchairs: List<Armchair> = emptyList()

    LaunchedEffect(Unit) {
        isLoading = true
        reservedSeats = reserveTicketViewModel.getReservedSeats(UserLogged.selectedEvent.event_id)
        totalArmchairs = armchairViewModel.loadArmchairsByEstablishment(UserLogged.selectedEvent.event_id)
        availableSeats = totalArmchairs.size - reservedSeats.size
        isLoading = false
    }

    if (isLoading) {
        showLoadingDialog()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd7e9fc))
            .padding(16.dp)
       ) {
        Button(
            onClick = { navController.navigate(Routes.MENU + "/0") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF14296F),
                contentColor = Color.White
                                                ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .align(Alignment.TopStart)
                .shadow(4.dp, RoundedCornerShape(50))
              ) {
            Text("Back")
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
              ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF7796CB))
                    .padding(horizontal = 32.dp, vertical = 20.dp)
               ) {
                Text(
                    text = event.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                    )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Select number of tickets", fontWeight = FontWeight.Bold)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 12.dp)
               ) {
                Button(
                    onClick = {
                        if (ticketCount > 1) {
                            ticketCount--
                            if (selectedSeats.size > ticketCount) {
                                selectedSeats.removeAt(selectedSeats.lastIndex)
                            }
                        }
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF405A94))
                      ) {
                    Text("-")
                }

                Text(
                    text = "$ticketCount / $availableSeats",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    style = MaterialTheme.typography.titleMedium
                    )

                Button(
                    onClick = {
                        if (ticketCount < availableSeats) ticketCount++
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF405A94))
                      ) {
                    Text("+")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Select your seats:", fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .background(Color.White)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                    .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(12.dp))
                    .padding(8.dp)
               ) {
                val scrollVertical = rememberScrollState()
                val scrollHorizontal = rememberScrollState()

                Column(
                    modifier = Modifier
                        .verticalScroll(scrollVertical)
                        .horizontalScroll(scrollHorizontal)
                      ) {
                    for (row in 0 until 10) {
                        Row {
                            for (col in 0 until 10) {
                                val seat = Armchair(0,row, col)
                                val isReserved = reservedSeats.any { it.row == seat.row && it.column == seat.column }
                                val isSelected = selectedSeats.any { it.row == seat.row && it.column == seat.column }

                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(
                                            when {
                                                isReserved -> Color.Red
                                                isSelected -> Color.Green
                                                else -> Color.LightGray
                                            }
                                                   )
                                        .then(
                                            if (isReserved) Modifier
                                            else Modifier.clickable {
                                                if (isSelected) {
                                                    selectedSeats.remove(seat)
                                                } else if (selectedSeats.size < ticketCount) {
                                                    selectedSeats.add(seat)
                                                }
                                            }
                                             )
                                   )
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .border(1.dp, Color.LightGray.copy(alpha = 0.5f))
                   )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Price: ${event.price * ticketCount}$",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
                )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    isLoading = true
                    for (armchair in selectedSeats) {
                        val ticket = ReserveTicket()
                        ticket.event_id = UserLogged.selectedEvent.event_id
                        ticket.user_id = UserLogged.user.user_id
                        ticket.armchair_id = armchair.row_id
                        ticket.reservation_date = UserLogged.selectedEvent.start_date
                        val reserveTicketViewModel = ReserveTicketViewModel()
                        reserveTicketViewModel.createReserveTicket(ticket)
                        isLoading = false
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF14296F)),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
                  ) {
                Text("Buy Tickets", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

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