package com.example.mingseventsapp.layouts.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.mingseventsapp.model.event.Event


@Preview(showBackground = true)
@Composable
fun EventPagePreview() {
    EventPage()
}


@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventPage() {
    var events = mutableListOf(
        Event(
            event_id = 1,
            name = "Festival de Música",
            price = 50,
            reserved_places = 120,
            photo = "",
            start_date = "2025-06-10",
            end_date = "2025-06-12",
            seating = 300,
            descripcion = "Disfruta de los mejores artistas en vivo.",
            establish_id = 1
        ),
        Event(
            event_id = 2,
            name = "Feria de Libros",
            price = 0,
            reserved_places = 80,
            photo = "",
            start_date = "2025-07-01",
            end_date = "2025-07-03",
            seating = 200,
            descripcion = "Encuentra las mejores editoriales y autores.",
            establish_id = 2
        ),
        Event(
            event_id = 3,
            name = "Conferencia de Tecnología",
            price = 150,
            reserved_places = 250,
            photo = "",
            start_date = "2025-08-20",
            end_date = "2025-08-21",
            seating = 400,
            descripcion = "Innovación, IA, y futuro digital.",
            establish_id = 3
        ),
        Event(
            event_id = 4,
            name = "Final champions Barça vs Arsenal",
            price = 1050,
            reserved_places = 1250,
            photo = "",
            start_date = "2025-08-20",
            end_date = "2025-08-21",
            seating = 400,
            descripcion = "Innovación, IA, y futuro digital.",
            establish_id = 3
        ),
        Event(
            event_id = 5,
            name = "Concierto Trueno y BadGyal",
            price = 350,
            reserved_places = 650,
            photo = "",
            start_date = "2025-08-20",
            end_date = "2025-08-21",
            seating = 400,
            descripcion = "Innovación, IA, y futuro digital.",
            establish_id = 3
        ),
    )

    var filteredEvents by remember { mutableStateOf(events.toMutableList()) }

    var searchString by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd7e9fc)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 40.dp, horizontal = 0.dp),
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
        modifier = Modifier
            .padding(top = 130.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        SearchBar (
            query = searchString,
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
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("Search events") }
        ) {}
    }

    LazyColumn(
        modifier = Modifier
            .padding(top = 230.dp)
            .fillMaxHeight()
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        items(filteredEvents) { event ->
            EventItem(event)
        }

        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun EventItem(event: Event) {
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
                    onClick = {/* Abrir pagina comprar entrada*/},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF14296F),
                        contentColor = Color.White
                                                        ),
                    modifier = Modifier
                        .height(40.dp)
                      ) {
                    Text(
                        text = "Buy"
                        )
                }
                Button(

                    onClick = {/* Abrir pagina mas info*/},
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
                    model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnS_M_rDC7mdZq0b-dRC_pWRHkxxGrbv85MA&s",
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


