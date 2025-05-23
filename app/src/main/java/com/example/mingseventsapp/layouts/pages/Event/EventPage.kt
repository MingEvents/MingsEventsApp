package com.example.mingseventsapp.layouts.pages.Event

import android.annotation.SuppressLint
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mingseventsapp.R
import com.example.mingseventsapp.Retrofit
import com.example.mingseventsapp.Routes
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.model.ReserveTicket
import com.example.mingseventsapp.model.event.Event
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import kotlin.math.log

@SuppressLint("MutableCollectionMutableState", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventPage(navController: NavHostController) {
    UserLogged.selectedEvent = Event()
    val eventViewModel = EventViewModel()
    var isLoading by remember { mutableStateOf(true) }
    var events by remember { mutableStateOf<List<Event>>(emptyList()) }

    LaunchedEffect(Unit) {
        events = eventViewModel.getAllEvents()
        isLoading = false
    }

    if (isLoading) {
        showLoadingDialog(isLoading = remember { mutableStateOf(true) })
    } else {
        var filteredEvents by remember { mutableStateOf(events.toMutableList()) }
        LaunchedEffect(events) {
            filteredEvents = events.toMutableList()
        }
        var searchString by remember { mutableStateOf("") }
        Box(
            modifier = Modifier.fillMaxSize().background(Color(0xFFd7e9fc)),
            contentAlignment = Alignment.Center
           ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(vertical = 40.dp, horizontal = 0.dp),
                horizontalAlignment = Alignment.Start,
                  ) {
                Text(
                    text = "Events",
                    fontSize = 40.sp,
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                    )
                Divider(color = Color.Black, thickness = 1.dp)
            }
        }

        Box(
            modifier = Modifier.padding(top = 130.dp, start = 16.dp, end = 16.dp).fillMaxWidth()
                .wrapContentHeight()
           ) {
            SearchBar(query = searchString,
                      onQueryChange = {
                          searchString = it
                          filteredEvents = events.filter { e ->
                              e.name.contains(it, ignoreCase = true)
                          }.toMutableList()
                      },
                      onSearch = {},
                      active = false,
                      onActiveChange = {},
                      shape = RoundedCornerShape(12.dp),
                      modifier = Modifier.fillMaxWidth(),
                      placeholder = { Text("Search events") }) {}
        }

        LazyColumn(
            modifier = Modifier.padding(top = 230.dp).fillMaxHeight()
                  ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(filteredEvents) { event ->
                EventItem(event, navController)
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun EventItem(event: Event, navController: NavHostController) {
    var showModal by remember { mutableStateOf(false) }

    if (showModal) {
        ReadMoreModal(onClose = { showModal = false }, event)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color(0xFFD7E9FC)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF7796CB)
                                        )
        ) {
        Row (
            modifier = Modifier.fillMaxWidth()
            ) {
            Column(modifier = Modifier
            .padding(16.dp)
            .weight(1f)
                  ) {
            Text(
                text = event.name,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 3.dp, bottom = 15.dp)
                )
            Text(
                text = "Price: $${event.price}",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                )

            Text(
                text = "Starts at ${event.start_date} - ${event.end_date}",
                fontSize = 14.sp,
                color = Color.White
                )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
               ) {
                Button(
                    onClick = {
                        UserLogged.selectedEvent = event
                        navController.navigate(Routes.BUYTICKET)

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF14296F),
                        contentColor = Color.White
                                                        ),
                    modifier = Modifier
                        .height(40.dp)
                      ) {
                    Text(
                        text = "Buy",
                        )
                }
                Button(

                    onClick = {
                        showModal = true
                              },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF14296F),
                        contentColor = Color.White
                                                        ),
                    modifier = Modifier
                        .height(40.dp)
                        .padding(start = 5.dp)
                      ) {
                    Text(
                        text = "Read more"
                        )
                }
            }
        }
            Box(
                modifier = Modifier
                        .padding(top = 15.dp, end = 6.dp)
                        .size(130.dp)
                ) {
                AsyncImage(
                    model = R.drawable.event,
                    contentDescription = "Imagen del evento",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.LightGray)
                          )
            }
         }
    }
}
@Composable
fun ReadMoreModal(onClose: () -> Unit, event: Event) {
    Dialog(onDismissRequest = { onClose() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(Color(0xFFD7E9FC))
                .padding(16.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.LightGray)
            ) {
                AsyncImage(
                    model = R.drawable.event,
                    contentDescription = "Imagen del evento",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF7796CB))
                    .padding(16.dp)
            ) {
                Text(
                    text = event.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${event.start_date} - ${event.end_date}",
                    fontSize = 15.sp,
                    color = Color.White
                )

                Text(
                    text = "España, Barcelona",
                    fontSize = 15.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF7796CB))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Description",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = event.descripcion,
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun showLoadingDialog(isLoading: MutableState<Boolean>) {
    Dialog(
        onDismissRequest = { isLoading.value = false }
          ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
           ) {
            CircularProgressIndicator(
                color = Color(0xFF14296F)
                                     )
        }
    }
}



