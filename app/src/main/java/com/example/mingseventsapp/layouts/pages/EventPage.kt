package com.example.mingseventsapp.layouts.pages

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.mingseventsapp.model.event.Event


@Preview(showBackground = true)
@Composable
fun EventPagePreview() {
    EventPage()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventPage() {
    var events = listOf(
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
        )
    )
    var searchString by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD7E9FC)),
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
            onQueryChange = { searchString = it },
            onSearch = {},
            active = false,
            onActiveChange = {},
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("Search events") }
        ) {


        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(top = 230.dp)
            .fillMaxHeight()
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        items(events) { event ->
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
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = event.name, fontSize = 20.sp, color = Color.Black)
            Text(text = "Price: $${event.price}", fontSize = 16.sp, color = Color.DarkGray)
            Text(
                text = "Starts at ${event.start_date} - ${event.end_date}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /* Abrir modal de comprar */ }) {
                    Text("Comprar")
                }
                OutlinedButton(onClick = { /*abrir un modal con mas info*/}) {
                    Text("Más info")
                }
            }
        }
    }
}